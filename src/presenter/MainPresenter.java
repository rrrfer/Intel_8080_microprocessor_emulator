package presenter;

import emulator.EmulatorIntel8080;
import emulator.IEmulator;
import emulator.IOSystem;
import kernel.IReadOnlyMicroprocessor;
import view.IMainView;
import view.MainWindow;

public class MainPresenter implements IMainPresenter {

    private IMainView mainView;
    private IEmulator emulator;

    private Thread runThread;

    private String[][] dataSourceForMemoryTable;
    private String[][] dataSourceForRegistersAndFlagsTable;

    public MainPresenter() {

        emulator = new EmulatorIntel8080(new IOSystem(this));

        dataSourceForMemoryTable = getDataSourceForMemoryTable(emulator);
        dataSourceForRegistersAndFlagsTable = getDataSourceForRegistersAndFlagsTable(emulator);

        mainView = new MainWindow(MainPresenter.this);
        mainView.updateMemoryTable(dataSourceForMemoryTable, 0, true);
        mainView.updateRegistersAndFlagsTable(dataSourceForRegistersAndFlagsTable);
        mainView.create();
    }

    @Override
    public void loadProgram(String program) {
        emulator.resetMemory();
        emulator.resetRegisters();

        boolean hasErrors = !emulator.loadProgram(program);

        dataSourceForMemoryTable = getDataSourceForMemoryTable(emulator);
        dataSourceForRegistersAndFlagsTable = getDataSourceForRegistersAndFlagsTable(emulator);

        mainView.updateMemoryTable(dataSourceForMemoryTable, 0, true);
        mainView.updateRegistersAndFlagsTable(dataSourceForRegistersAndFlagsTable);
        mainView.updateCodeEditor(emulator.getTranslationResult(), hasErrors);
    }

    @Override
    public void run() {
        if (runThread == null || !runThread.isAlive()) {
            runThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    emulator.run();
                    dataSourceForRegistersAndFlagsTable
                            = getDataSourceForRegistersAndFlagsTable(emulator);
                    mainView.updateRegistersAndFlagsTable(dataSourceForRegistersAndFlagsTable);
                    dataSourceForMemoryTable = getDataSourceForMemoryTable(emulator);
                    int PC = emulator.getViewInterface().getValueByRegisterName("PC");
                    mainView.updateMemoryTable(dataSourceForMemoryTable, PC, true);
                    mainView.setRunningMode(false);
                }
            });
            mainView.setRunningMode(true);
            runThread.start();
        }
    }

    @Override
    public void step() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                emulator.step();
                dataSourceForRegistersAndFlagsTable = getDataSourceForRegistersAndFlagsTable(emulator);
                mainView.updateRegistersAndFlagsTable(dataSourceForRegistersAndFlagsTable);
                int PC = emulator.getViewInterface().getValueByRegisterName("PC");
                dataSourceForMemoryTable = getDataSourceForMemoryTable(emulator);
                mainView.updateMemoryTable(dataSourceForMemoryTable, PC, true);
            }
        }).start();
    }

    @Override
    public void stop() {
        if (runThread != null && runThread.isAlive()) {
            runThread.interrupt();
            mainView.setRunningMode(false);
        }
    }

    @Override
    public void resetRegisters() {
        emulator.resetRegisters();
        dataSourceForRegistersAndFlagsTable = getDataSourceForRegistersAndFlagsTable(emulator);
        mainView.updateRegistersAndFlagsTable(dataSourceForRegistersAndFlagsTable);
    }

    @Override
    public void resetMemory() {
        emulator.resetMemory();
        dataSourceForMemoryTable = getDataSourceForMemoryTable(emulator);
        mainView.updateMemoryTable(dataSourceForMemoryTable, 0, true);
    }

    @Override
    public void clearScreens() {

    }

    @Override
    public void setBreakpoint(int address) {
        emulator.setBreakpoint(address);
        mainView.updateMemoryTable(dataSourceForMemoryTable, address, false);
    }

    @Override
    public void setProgramCounter(int address) {
        emulator.setProgramCounter(address);
        dataSourceForRegistersAndFlagsTable = getDataSourceForRegistersAndFlagsTable(emulator);
        mainView.updateRegistersAndFlagsTable(dataSourceForRegistersAndFlagsTable);
    }

    @Override
    public void consoleOut(int value) {
        mainView.consoleOut(value);
    }

    @Override
    public int consoleIn() {
        return mainView.consoleIn();
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