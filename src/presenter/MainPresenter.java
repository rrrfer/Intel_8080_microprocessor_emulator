package presenter;

import emulator.EmulatorIntel8080;
import emulator.IEmulator;
import emulator.InputOutputSystem;
import kernel.IMicroprocessorAdapterForPresenter;
import kernel.Intel8080Flags;
import kernel.Intel8080Registers;
import view.IMainView;
import view.MainWindow;

import java.io.IOException;
import java.util.ArrayList;

public class MainPresenter implements IMainPresenter_View, IMainPresenter_Model {

    public static final int DEFAULT_ACTION_MODE = 0;
    public static final int RUN_ACTION_MODE = 1;
    public static final int IO_ACTION_MODE = 2;

    private int currentActionMode;

    private String openedFilePath;

    private String dataSourceForConsoleOutputPanel;

    private String[][] dataSourceForMemoryTable;
    private String[][] dataSourceForRegistersTable;

    private int[][] dataSourceForPixelScreen;
    private int[][] dataSourceForCharacterScreen_Color;
    private int[][] dataSourceForCharacterScreen_Character;

    private IMainView mainView;
    private IEmulator emulator;

    private Thread commandRunThread;
    private Thread programRunThread;

    public MainPresenter() {
        emulator = new EmulatorIntel8080(new InputOutputSystem(this));

        dataSourceForMemoryTable = new String[65536][3];
        dataSourceForRegistersTable = new String[13][4];

        getDataSourceForMemoryTable(emulator, dataSourceForMemoryTable);
        getDataSourceForRegistersTable(emulator, dataSourceForRegistersTable);
        dataSourceForConsoleOutputPanel = "";

        mainView = new MainWindow(this, dataSourceForMemoryTable, dataSourceForRegistersTable,
                dataSourceForPixelScreen, dataSourceForCharacterScreen_Color,
                dataSourceForCharacterScreen_Character);

        currentActionMode = DEFAULT_ACTION_MODE;
        mainView.setPermissionForActions(MainPresenter.DEFAULT_ACTION_MODE);
    }

    // For View
    @Override
    public void translation(String program) {
        emulator.resetRegisters();
        emulator.resetMemory();
        emulator.translation(program);

        getDataSourceForMemoryTable(emulator, dataSourceForMemoryTable);
        getDataSourceForRegistersTable(emulator, dataSourceForRegistersTable);
        String dataSourceForTranslateResultPanel = emulator.getTranslationResult();

        mainView.memoryTableUpdate();
        mainView.registersTableUpdate();
        mainView.setTranslationResult(dataSourceForTranslateResultPanel, true);
        mainView.setProgramCounterPosition(0);
    }

    @Override
    public void run() {
        if (programRunThread == null || !programRunThread.isAlive()) {
            programRunThread = new Thread(() -> {
                emulator.run();

                getDataSourceForRegistersTable(emulator, dataSourceForRegistersTable);
                getDataSourceForMemoryTable(emulator, dataSourceForMemoryTable);
                int PC = emulator.getMicroprocessor().getValueFromRegister(Intel8080Registers.PC);

                mainView.memoryTableUpdate();
                mainView.registersTableUpdate();
                mainView.setProgramCounterPosition(PC);

                mainView.setPermissionForActions(MainPresenter.DEFAULT_ACTION_MODE);
                currentActionMode = DEFAULT_ACTION_MODE;
            });
            currentActionMode = RUN_ACTION_MODE;
            mainView.setPermissionForActions(MainPresenter.RUN_ACTION_MODE);
            programRunThread.start();
        }
    }

    @Override
    public void step() {
        if (commandRunThread == null || !commandRunThread.isAlive()) {
            commandRunThread = new Thread(() -> {
                emulator.step();

                getDataSourceForMemoryTable(emulator, dataSourceForMemoryTable);
                getDataSourceForRegistersTable(emulator, dataSourceForRegistersTable);
                int PC = emulator.getMicroprocessor().getValueFromRegister(Intel8080Registers.PC);

                mainView.memoryTableUpdate();
                mainView.registersTableUpdate();
                mainView.setProgramCounterPosition(PC);
            });
            commandRunThread.start();
        }
    }

    @Override
    public void stop() {
        if (programRunThread != null && programRunThread.isAlive()) {
            programRunThread.stop();

            getDataSourceForRegistersTable(emulator, dataSourceForRegistersTable);
            getDataSourceForMemoryTable(emulator, dataSourceForMemoryTable);
            int PC = emulator.getMicroprocessor().getValueFromRegister(Intel8080Registers.PC);

            mainView.memoryTableUpdate();
            mainView.registersTableUpdate();
            mainView.setProgramCounterPosition(PC);
            mainView.clearInputConsole();

            currentActionMode = DEFAULT_ACTION_MODE;
            mainView.setPermissionForActions(MainPresenter.DEFAULT_ACTION_MODE);
            System.gc();
        }
    }

    @Override
    public void resetRegisters() {
        emulator.resetRegisters();
        emulator.clearScreen();

        getDataSourceForRegistersTable(emulator, dataSourceForRegistersTable);
        dataSourceForConsoleOutputPanel = "";

        mainView.registersTableUpdate();
        mainView.setConsoleOutputData(dataSourceForConsoleOutputPanel);
        mainView.setProgramCounterPosition(0);
    }

    @Override
    public void resetMemory() {
        emulator.resetMemory();
        getDataSourceForMemoryTable(emulator, dataSourceForMemoryTable);
        mainView.memoryTableUpdate();
        mainView.setProgramCounterPosition(0);
    }

    @Override
    public void setBreakpoint(int address) {
        emulator.setBreakpoint(address);
    }

    @Override
    public void removeAllBreakpoints() {
        emulator.removeAllBreakpoints();
        ArrayList<Integer> breakpoints = emulator.getBreakpoints();
        int PC = emulator.getMicroprocessor().getValueFromRegister(Intel8080Registers.PC);
        mainView.setBreakpoints(breakpoints);
        mainView.setProgramCounterPosition(PC);
    }

    @Override
    public void setProgramCounter(int address) {
        emulator.setProgramCounter(address);
        getDataSourceForRegistersTable(emulator, dataSourceForRegistersTable);
        mainView.registersTableUpdate();
    }

    @Override
    public void loadProgramFromFile(String path) throws IOException {
        openedFilePath = path;

        String dataSourceForCodeEditorPanel = emulator.loadProgramFromFile(path);

        mainView.setTranslationResult("", false);
        mainView.setProgramText(dataSourceForCodeEditorPanel);
        mainView.setViewTitle(path);
    }

    @Override
    public void saveAsProgramInFile(String path, String programText) throws IOException {
        if (!path.endsWith(".i8080")) {
            path = path + ".i8080";
        }
        emulator.saveProgramInFile(path, programText);
        mainView.setViewTitle(path);
    }

    @Override
    public boolean saveProgramInFile(String programText) throws IOException {
        if (openedFilePath == null) {
            return false;
        } else {
            if (!openedFilePath.endsWith(".i8080")) {
                openedFilePath = openedFilePath + ".i8080";
            }
            emulator.saveProgramInFile(openedFilePath, programText);
            mainView.setViewTitle(openedFilePath);
            return true;
        }
    }

    // For Model
    @Override
    public void consoleOut(int value) {
        dataSourceForConsoleOutputPanel
                = dataSourceForConsoleOutputPanel + " " + String.valueOf(value);
        mainView.setConsoleOutputData(dataSourceForConsoleOutputPanel);
    }

    @Override
    public int requestOfInput() {
        mainView.setPermissionForActions(MainPresenter.IO_ACTION_MODE);
        int value = mainView.requestOfInput();
        mainView.setPermissionForActions(currentActionMode);
        mainView.clearInputConsole();
        return value;
    }

    @Override
    public void setPixelScreenData(int[][] memory) {
        dataSourceForPixelScreen = memory;
    }

    @Override
    public void setCharacterScreenData(int[][] colorMemory, int[][] characterMemory) {
        dataSourceForCharacterScreen_Color = colorMemory;
        dataSourceForCharacterScreen_Character = characterMemory;
    }

    @Override
    public void pixelScreenUpdate() {
        mainView.pixelScreenUpdate();
    }

    @Override
    public void characterScreenUpdate() {
        mainView.characterScreenUpdate();
    }

    // Help
    private void getDataSourceForMemoryTable(IEmulator emulator, String[][] dataSourceForMemoryTable) {
        String[] commandsInMemory = emulator.getCommandsList();
        int length = emulator.getMicroprocessor().getReadOnlyMemory().getSize();
        for (int i = 0; i < length; ++i) {
            StringBuilder address = new StringBuilder(Integer.toString(i, 16));
            while (address.length() != 4) {
                address.insert(0, "0");
            }
            dataSourceForMemoryTable[i][0] = address.toString();
            dataSourceForMemoryTable[i][1] = commandsInMemory[i];
            dataSourceForMemoryTable[i][2] =
                    Integer.toString(emulator
                            .getMicroprocessor()
                            .getReadOnlyMemory().getValueByIndex(i), 16);
        }
    }

    private void getDataSourceForRegistersTable(IEmulator emulator, String[][] dataSourceForRegistersTable) {
        IMicroprocessorAdapterForPresenter microprocessor
                = emulator.getMicroprocessor();

        dataSourceForRegistersTable[0][0] = "Register A";
        dataSourceForRegistersTable[0][1]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.A), 16);
        dataSourceForRegistersTable[0][2]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.A), 2);
        dataSourceForRegistersTable[0][3]
                = Integer.toString(microprocessor.getValueFromRegister(Intel8080Registers.A));

        dataSourceForRegistersTable[1][0] = "Register B";
        dataSourceForRegistersTable[1][1]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.B), 16);
        dataSourceForRegistersTable[1][2]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.B), 2);
        dataSourceForRegistersTable[1][3]
                = Integer.toString(microprocessor.getValueFromRegister(Intel8080Registers.B));

        dataSourceForRegistersTable[2][0] = "Register C";
        dataSourceForRegistersTable[2][1]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.C), 16);
        dataSourceForRegistersTable[2][2]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.C), 2);
        dataSourceForRegistersTable[2][3]
                = Integer.toString(microprocessor.getValueFromRegister(Intel8080Registers.C));

        dataSourceForRegistersTable[3][0] = "Register D";
        dataSourceForRegistersTable[3][1]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.D), 16);
        dataSourceForRegistersTable[3][2]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.D), 2);
        dataSourceForRegistersTable[3][3]
                = Integer.toString(microprocessor.getValueFromRegister(Intel8080Registers.D));

        dataSourceForRegistersTable[4][0] = "Register E";
        dataSourceForRegistersTable[4][1]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.E), 16);
        dataSourceForRegistersTable[4][2]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.E), 2);
        dataSourceForRegistersTable[4][3]
                = Integer.toString(microprocessor.getValueFromRegister(Intel8080Registers.E));

        dataSourceForRegistersTable[5][0] = "Register H";
        dataSourceForRegistersTable[5][1]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.H), 16);
        dataSourceForRegistersTable[5][2]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.H), 2);
        dataSourceForRegistersTable[5][3]
                = Integer.toString(microprocessor.getValueFromRegister(Intel8080Registers.H));

        dataSourceForRegistersTable[6][0] = "Register L";
        dataSourceForRegistersTable[6][1]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.L), 16);
        dataSourceForRegistersTable[6][2]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.L), 2);
        dataSourceForRegistersTable[6][3]
                = Integer.toString(microprocessor.getValueFromRegister(Intel8080Registers.L));

        dataSourceForRegistersTable[7][0] = "Register PC";
        dataSourceForRegistersTable[7][1]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.PC), 16);

        dataSourceForRegistersTable[8][0] = "Register SP";
        dataSourceForRegistersTable[8][1]
                = createString(microprocessor.getValueFromRegister(Intel8080Registers.SP), 16);

        dataSourceForRegistersTable[9][0] = "Flag Z";
        dataSourceForRegistersTable[9][1]
                = createString(microprocessor.getValueByFlag(Intel8080Flags.Z), 16);

        dataSourceForRegistersTable[10][0] = "Flag C";
        dataSourceForRegistersTable[10][1]
                = createString(microprocessor.getValueByFlag(Intel8080Flags.C), 16);

        dataSourceForRegistersTable[11][0] = "Flag S";
        dataSourceForRegistersTable[11][1]
                = createString(microprocessor.getValueByFlag(Intel8080Flags.S), 16);

        dataSourceForRegistersTable[12][0] = "Flag P";
        dataSourceForRegistersTable[12][1]
                = createString(microprocessor.getValueByFlag(Intel8080Flags.P), 16);
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