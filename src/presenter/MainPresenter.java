package presenter;

import emulator.EmulatorIntel8080;
import emulator.IEmulator;
import emulator.IOSystem;
import kernel.IReadOnlyMicroprocessor;
import view.IMainView;
import view.MainWindow;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainPresenter implements IMainPresenter {

    public static final int DEFAULT_MODE = 0;
    public static final int RUN_MODE = 1;
    public static final int IO_MODE = 2;

    private String currentFilePath;

    private String dataSourceForCodeEditorPanel;
    private String dataSourceForTranslateResultPanel;
    private String dataSourceForConsoleOutputPanel;

    private String[][] dataSourceForMemoryTable;
    private String[][] dataSourceForRegistersAndFlagsTable;

    private int actionMode;

    private IMainView mainView;
    private IEmulator emulator;

    private Thread commandRunThread;
    private Thread programRunThread;

    public MainPresenter() {
        emulator = new EmulatorIntel8080(new IOSystem(this));
        dataSourceForMemoryTable = getDataSourceForMemoryTable(emulator);
        dataSourceForRegistersAndFlagsTable = getDataSourceForRegistersAndFlagsTable(emulator);
        dataSourceForConsoleOutputPanel = "";
        actionMode = DEFAULT_MODE;

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainView = new MainWindow(MainPresenter.this,
                        dataSourceForMemoryTable, dataSourceForRegistersAndFlagsTable);
                mainView.setPermissionForActions(MainPresenter.DEFAULT_MODE);
            }
        });
    }

    @Override
    public void translation(String program) {
        emulator.resetRegisters();
        emulator.resetMemory();
        emulator.translation(program);

        dataSourceForMemoryTable = getDataSourceForMemoryTable(emulator);
        dataSourceForRegistersAndFlagsTable = getDataSourceForRegistersAndFlagsTable(emulator);
        dataSourceForTranslateResultPanel = emulator.getTranslationResult();

        mainView.setMemoryDataTable(dataSourceForMemoryTable);
        mainView.setRegistersAndFlagsDataTable(dataSourceForRegistersAndFlagsTable);
        mainView.setTranslationResultText(dataSourceForTranslateResultPanel);
        mainView.setProgramCounterPosition(0);
    }

    @Override
    public void run() {
        if (programRunThread == null || !programRunThread.isAlive()) {
            programRunThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    emulator.run();

                    dataSourceForRegistersAndFlagsTable
                            = getDataSourceForRegistersAndFlagsTable(emulator);
                    dataSourceForMemoryTable = getDataSourceForMemoryTable(emulator);
                    int PC = emulator.getViewInterface().getValueByRegisterName("PC");

                    mainView.setMemoryDataTable(dataSourceForMemoryTable);
                    mainView.setRegistersAndFlagsDataTable(dataSourceForRegistersAndFlagsTable);
                    mainView.setProgramCounterPosition(PC);

                    mainView.setPermissionForActions(MainPresenter.DEFAULT_MODE);
                    actionMode = DEFAULT_MODE;
                }
            });
            mainView.setPermissionForActions(MainPresenter.RUN_MODE);
            actionMode = RUN_MODE;
            programRunThread.start();
        }
    }

    @Override
    public void step() {
        if (commandRunThread == null || !commandRunThread.isAlive()) {
            commandRunThread = new Thread(() -> {
                emulator.step();

                dataSourceForMemoryTable = getDataSourceForMemoryTable(emulator);
                dataSourceForRegistersAndFlagsTable = getDataSourceForRegistersAndFlagsTable(emulator);
                int PC = emulator.getViewInterface().getValueByRegisterName("PC");

                mainView.setMemoryDataTable(dataSourceForMemoryTable);
                mainView.setRegistersAndFlagsDataTable(dataSourceForRegistersAndFlagsTable);
                mainView.setProgramCounterPosition(PC);
            });
            commandRunThread.start();
        }

        try {
            Thread.sleep(25);
        } catch (InterruptedException ignored) {}
    }

    @Override
    public void stop() {
        if (programRunThread != null && programRunThread.isAlive()) {
            programRunThread.interrupt();

            try {
                programRunThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            dataSourceForRegistersAndFlagsTable
                    = getDataSourceForRegistersAndFlagsTable(emulator);
            dataSourceForMemoryTable = getDataSourceForMemoryTable(emulator);
            int PC = emulator.getViewInterface().getValueByRegisterName("PC");

            mainView.setMemoryDataTable(dataSourceForMemoryTable);
            mainView.setRegistersAndFlagsDataTable(dataSourceForRegistersAndFlagsTable);
            mainView.setProgramCounterPosition(PC);

            mainView.setPermissionForActions(MainPresenter.DEFAULT_MODE);
            actionMode = DEFAULT_MODE;
        }
    }

    @Override
    public void resetRegisters() {
        emulator.resetRegisters();
        dataSourceForRegistersAndFlagsTable = getDataSourceForRegistersAndFlagsTable(emulator);
        dataSourceForConsoleOutputPanel = "";
        mainView.setRegistersAndFlagsDataTable(dataSourceForRegistersAndFlagsTable);
        mainView.setConsoleOutData(dataSourceForConsoleOutputPanel);
        mainView.setProgramCounterPosition(0);
    }

    @Override
    public void resetMemory() {
        emulator.resetMemory();
        dataSourceForMemoryTable = getDataSourceForMemoryTable(emulator);
        mainView.setMemoryDataTable(dataSourceForMemoryTable);
        mainView.setProgramCounterPosition(0);
    }

    @Override
    public void clearScreens() {

    }

    @Override
    public void setBreakpoint(int address) {
        emulator.setBreakpoint(address);
    }

    @Override
    public void removeAllBreakpoints() {
        emulator.removeAllBreakpoints();
        ArrayList<Integer> breakpoints = emulator.getBreakpoints();
        int PC = emulator.getViewInterface().getValueByRegisterName("PC");
        mainView.setBreakpointsData(breakpoints);
        mainView.setProgramCounterPosition(PC);
    }

    @Override
    public void setProgramCounter(int address) {
        emulator.setProgramCounter(address);
        dataSourceForRegistersAndFlagsTable = getDataSourceForRegistersAndFlagsTable(emulator);
        mainView.setRegistersAndFlagsDataTable(dataSourceForRegistersAndFlagsTable);
    }

    @Override
    public void consoleOut(int value) {
        dataSourceForConsoleOutputPanel
                = dataSourceForConsoleOutputPanel + " " + String.valueOf(value);
        mainView.setConsoleOutData(dataSourceForConsoleOutputPanel);
    }

    @Override
    public int requestOfInput() {
        mainView.setPermissionForActions(MainPresenter.IO_MODE);
        int value = mainView.requestOfInput();
        mainView.setPermissionForActions(actionMode);
        mainView.setConsoleInData("");
        return value;
    }

    @Override
    public void loadProgramFromFile(String path) throws IOException {
        currentFilePath = path;
        dataSourceForCodeEditorPanel = emulator.loadProgramFromFile(path);
        mainView.setProgramText(dataSourceForCodeEditorPanel);
        mainView.setEditableFileTitle(path);
    }

    @Override
    public void saveAsProgramInFile(String path, String programText) throws IOException {
        if (!path.endsWith(".i8080")) {
            path = path + ".i8080";
        }
        emulator.saveProgramInFile(path, programText);
        mainView.setEditableFileTitle(path);
    }

    @Override
    public boolean saveProgramInFile(String programText) throws IOException {
        if (currentFilePath == null) {
            return false;
        } else {
            if (!currentFilePath.endsWith(".i8080")) {
                currentFilePath = currentFilePath + ".i8080";
            }
            emulator.saveProgramInFile(currentFilePath, programText);
            return true;
        }
    }

    private String[][] getDataSourceForMemoryTable(IEmulator emulator) {
        String[] commandsInMemory = emulator.getCommandsList();
        int length = emulator.getViewInterface().getReadOnlyMemory().getSize();
        String[][] dataSource = new String[length][3];
        for (int i = 0; i < length; ++i) {
            StringBuilder address = new StringBuilder(Integer.toString(i, 16));
            while (address.length() != 4) {
                address.insert(0, "0");
            }
            dataSource[i][0] = address.toString();
            dataSource[i][1] = commandsInMemory[i];
            dataSource[i][2] =
                    Integer.toString(emulator.getViewInterface().getReadOnlyMemory().getValueByIndex(i));
        }
        return dataSource;
    }

    private String[][] getDataSourceForRegistersAndFlagsTable(IEmulator emulator) {
        String[][] dataSourceForRegistersAndFlagsTable = new String[13][4];
        IReadOnlyMicroprocessor microprocessor = emulator.getViewInterface();

        dataSourceForRegistersAndFlagsTable[0][0] = "Register A";
        dataSourceForRegistersAndFlagsTable[0][1]
                = createString(microprocessor.getValueByRegisterName("A"), 16);
        dataSourceForRegistersAndFlagsTable[0][2]
                = createString(microprocessor.getValueByRegisterName("A"), 2);
        dataSourceForRegistersAndFlagsTable[0][3]
                = Integer.toString(microprocessor.getValueByRegisterName("A"));

        dataSourceForRegistersAndFlagsTable[1][0] = "Register B";
        dataSourceForRegistersAndFlagsTable[1][1]
                = createString(microprocessor.getValueByRegisterName("B"), 16);
        dataSourceForRegistersAndFlagsTable[1][2]
                = createString(microprocessor.getValueByRegisterName("B"), 2);
        dataSourceForRegistersAndFlagsTable[1][3]
                = Integer.toString(microprocessor.getValueByRegisterName("B"));

        dataSourceForRegistersAndFlagsTable[2][0] = "Register C";
        dataSourceForRegistersAndFlagsTable[2][1]
                = createString(microprocessor.getValueByRegisterName("C"), 16);
        dataSourceForRegistersAndFlagsTable[2][2]
                = createString(microprocessor.getValueByRegisterName("C"), 2);
        dataSourceForRegistersAndFlagsTable[2][3]
                = Integer.toString(microprocessor.getValueByRegisterName("C"));

        dataSourceForRegistersAndFlagsTable[3][0] = "Register D";
        dataSourceForRegistersAndFlagsTable[3][1]
                = createString(microprocessor.getValueByRegisterName("D"), 16);
        dataSourceForRegistersAndFlagsTable[3][2]
                = createString(microprocessor.getValueByRegisterName("D"), 2);
        dataSourceForRegistersAndFlagsTable[3][3]
                = Integer.toString(microprocessor.getValueByRegisterName("D"));

        dataSourceForRegistersAndFlagsTable[4][0] = "Register E";
        dataSourceForRegistersAndFlagsTable[4][1]
                = createString(microprocessor.getValueByRegisterName("E"), 16);
        dataSourceForRegistersAndFlagsTable[4][2]
                = createString(microprocessor.getValueByRegisterName("E"), 2);
        dataSourceForRegistersAndFlagsTable[4][3]
                = Integer.toString(microprocessor.getValueByRegisterName("E"));

        dataSourceForRegistersAndFlagsTable[5][0] = "Register H";
        dataSourceForRegistersAndFlagsTable[5][1]
                = createString(microprocessor.getValueByRegisterName("H"), 16);
        dataSourceForRegistersAndFlagsTable[5][2]
                = createString(microprocessor.getValueByRegisterName("H"), 2);
        dataSourceForRegistersAndFlagsTable[5][3]
                = Integer.toString(microprocessor.getValueByRegisterName("H"));

        dataSourceForRegistersAndFlagsTable[6][0] = "Register L";
        dataSourceForRegistersAndFlagsTable[6][1]
                = createString(microprocessor.getValueByRegisterName("L"), 16);
        dataSourceForRegistersAndFlagsTable[6][2]
                = createString(microprocessor.getValueByRegisterName("L"), 2);
        dataSourceForRegistersAndFlagsTable[6][3]
                = Integer.toString(microprocessor.getValueByRegisterName("L"));

        dataSourceForRegistersAndFlagsTable[7][0] = "Register PC";
        dataSourceForRegistersAndFlagsTable[7][1]
                = createString(microprocessor.getValueByRegisterName("PC"), 16);

        dataSourceForRegistersAndFlagsTable[8][0] = "Register SP";
        dataSourceForRegistersAndFlagsTable[8][1]
                = createString(microprocessor.getValueByRegisterName("SP"), 16);

        dataSourceForRegistersAndFlagsTable[9][0] = "Flag Z";
        dataSourceForRegistersAndFlagsTable[9][1]
                = createString(microprocessor.getValueByFlagName("Z"), 16);

        dataSourceForRegistersAndFlagsTable[10][0] = "Flag C";
        dataSourceForRegistersAndFlagsTable[10][1]
                = createString(microprocessor.getValueByFlagName("C"), 16);

        dataSourceForRegistersAndFlagsTable[11][0] = "Flag S";
        dataSourceForRegistersAndFlagsTable[11][1]
                = createString(microprocessor.getValueByFlagName("S"), 16);

        dataSourceForRegistersAndFlagsTable[12][0] = "Flag P";
        dataSourceForRegistersAndFlagsTable[12][1]
                = createString(microprocessor.getValueByFlagName("P"), 16);

        return dataSourceForRegistersAndFlagsTable;
    }

    private String createString(int value, int radix) {
        StringBuilder outputString = new StringBuilder(Integer.toString(value, radix));
        switch (radix) {
            case 2: {
                while (outputString.length() < 8) {
                    outputString.insert(0, "0");
                }
                break;
            }

            case 16: {
                while (outputString.length() < 2) {
                    outputString.insert(0, "0");
                }
                break;
            }
        }
        return outputString.toString();
    }
}