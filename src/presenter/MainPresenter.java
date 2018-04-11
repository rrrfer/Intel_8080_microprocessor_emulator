package presenter;

import model.emulator.*;
import model.kernel.Flags;
import model.kernel.Registers;
import view.IMainView;
import view.MainWindow;

import java.io.IOException;
import java.util.ArrayList;

public class MainPresenter implements IMainPresenter, IIntraProgramIOUpdateEventsListener {

    private IMainView mainView;
    private IEmulator emulator;

    public static final int DEFAULT_ACTION_MODE = 0;
    public static final int RUN_ACTION_MODE = 1;
    public static final int IO_ACTION_MODE = 2;

    private int currentActionMode;

    private String openedFilePath;

    private String dataSourceForConsoleOutputPanel;

    private String[][] dataSourceForMemoryTable;
    private String[][] dataSourceForRegistersTable;

    private Thread commandRunThread;
    private Thread programRunThread;

    public MainPresenter() {

        // Экземпляр эмулятора.
        emulator = new EmulatorIntel8080();
        emulator.setIntraProgramIOUpdateEventsListener(this);

        // Источники данных для View
        dataSourceForMemoryTable = new String[65536][3];
        dataSourceForRegistersTable = new String[13][4];
        dataSourceForConsoleOutputPanel = "";

        // Получение данных для View с эмулятора
        getDataSourceForMemoryTable(emulator, dataSourceForMemoryTable);
        getDataSourceForRegistersTable(dataSourceForRegistersTable);
        int[][] dataSourceForPixelScreen = emulator.getPixelScreenMemory();
        int[][] dataSourceForCharacterScreen_Color = emulator.getCharacterScreenColorMemory();
        int[][] dataSourceForCharacterScreen_Character = emulator.getCharacterScreenCharMemory();

        // Создание View
        mainView = new MainWindow(this, dataSourceForMemoryTable, dataSourceForRegistersTable,
                dataSourceForPixelScreen, dataSourceForCharacterScreen_Color,
                dataSourceForCharacterScreen_Character, emulator.getLabel2AddressList());

        // Установка режима работы эмулятора по умолчанию
        currentActionMode = DEFAULT_ACTION_MODE;
        // Установка режима работы эмулятора по умолчанию
        // Режим работы эмулятора влияет на доступные пользователю действия во View
        mainView.setPermissionForActions(MainPresenter.DEFAULT_ACTION_MODE);
    }

    // IMainPresenter
    @Override
    public void translation(String program) {
        emulator.resetRegisters();
        emulator.resetMemory();
        emulator.translation(program);

        getDataSourceForMemoryTable(emulator, dataSourceForMemoryTable);
        getDataSourceForRegistersTable(dataSourceForRegistersTable);
        String dataSourceForTranslateResultPanel = emulator.getErrors();
        boolean hasErrors = emulator.hasTranslationErrors();

        mainView.memoryTableUpdate();
        mainView.registersTableUpdate();
        mainView.label2AddressTableUpdate();
        mainView.setTranslationResult(dataSourceForTranslateResultPanel, hasErrors);
        mainView.setProgramCounterPosition(0, true);
    }

    @Override
    public void run() {
        if (programRunThread == null || !programRunThread.isAlive()) {
            programRunThread = new Thread(() -> {
                emulator.run();

                getDataSourceForRegistersTable(dataSourceForRegistersTable);
                getDataSourceForMemoryTable(emulator, dataSourceForMemoryTable);
                int PC = emulator.getValueFromRegister(Registers.PC);

                mainView.memoryTableUpdate();
                mainView.registersTableUpdate();
                mainView.setProgramCounterPosition(PC, true);

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
                getDataSourceForRegistersTable(dataSourceForRegistersTable);
                int PC = emulator.getValueFromRegister(Registers.PC);

                mainView.memoryTableUpdate();
                mainView.registersTableUpdate();
                mainView.setProgramCounterPosition(PC, true);
            });
            commandRunThread.start();
        }
    }

    @Override
    public void stop() {
        if (programRunThread != null && programRunThread.isAlive()) {
            programRunThread.stop();
        }

        if (commandRunThread != null && commandRunThread.isAlive()) {
            commandRunThread.stop();
        }

        getDataSourceForRegistersTable(dataSourceForRegistersTable);
        getDataSourceForMemoryTable(emulator, dataSourceForMemoryTable);
        int PC = emulator.getValueFromRegister(Registers.PC);

        mainView.memoryTableUpdate();
        mainView.registersTableUpdate();
        mainView.setProgramCounterPosition(PC, true);
        mainView.clearInputConsole();

        currentActionMode = DEFAULT_ACTION_MODE;
        mainView.setPermissionForActions(MainPresenter.DEFAULT_ACTION_MODE);
        System.gc();
    }

    @Override
    public void resetRegisters() {
        emulator.resetRegisters();
        emulator.clearScreen();

        getDataSourceForRegistersTable(dataSourceForRegistersTable);
        dataSourceForConsoleOutputPanel = "";

        mainView.registersTableUpdate();
        mainView.pixelScreenUpdate();
        mainView.characterScreenUpdate();
        mainView.setConsoleOutputData(dataSourceForConsoleOutputPanel);
        mainView.setProgramCounterPosition(0, true);
    }

    @Override
    public void resetMemory() {
        emulator.resetMemory();
        getDataSourceForMemoryTable(emulator, dataSourceForMemoryTable);
        mainView.memoryTableUpdate();
        mainView.setProgramCounterPosition(0, true);
    }

    @Override
    public void setBreakpoint(int address) {
        emulator.setBreakpoint(address);
        mainView.setBreakpoints(emulator.getBreakpoints());
    }

    @Override
    public void removeAllBreakpoints() {
        emulator.removeAllBreakpoints();
        int breakpoints[] = emulator.getBreakpoints();
        int PC = emulator.getValueFromRegister(Registers.PC);
        mainView.setBreakpoints(breakpoints);
    }

    @Override
    public void setProgramCounter(int address) {
        emulator.setProgramCounter(address);
        getDataSourceForRegistersTable(dataSourceForRegistersTable);
        mainView.registersTableUpdate();
        mainView.setProgramCounterPosition(address, false);
    }

    @Override
    public void loadProgramFromFile(String path) throws IOException {
        openedFilePath = path;

        String dataSourceForCodeEditorPanel = emulator.loadProgramFromFile(path);

        mainView.setTranslationResult("", false);
        mainView.setProgramText(dataSourceForCodeEditorPanel);
        mainView.setNameEditedFileInTitle(path);
    }

    @Override
    public void saveAsProgramInFile(String path, String programText) throws IOException {
        if (!path.endsWith(".i8080")) {
            path = path + ".i8080";
        }
        emulator.saveProgramInFile(path, programText);
        openedFilePath = path;
        mainView.setNameEditedFileInTitle(path);
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
            mainView.setNameEditedFileInTitle(openedFilePath);
            return true;
        }
    }

    // IIntraProgramIOUpdateEventsListener
    @Override
    public void consoleOut(int value) {
        dataSourceForConsoleOutputPanel
                = dataSourceForConsoleOutputPanel + " " + String.valueOf(value);
        mainView.setConsoleOutputData(dataSourceForConsoleOutputPanel);
    }

    @Override
    public int requestOfInput() {
        mainView.setProgramCounterPosition(emulator.getValueFromRegister(Registers.PC) - 2, true);
        mainView.setPermissionForActions(MainPresenter.IO_ACTION_MODE);
        int value = mainView.requestOfInput();
        mainView.setPermissionForActions(currentActionMode);
        mainView.clearInputConsole();
        return value;
    }

    @Override
    public void pixelScreenUpdate() {
        mainView.pixelScreenUpdate();
    }

    @Override
    public void characterScreenUpdate() {
        mainView.characterScreenUpdate();
    }

    // Helps Methods
    private void getDataSourceForMemoryTable(IEmulator emulator, String[][] dataSourceForMemoryTable) {
        String[] commandsInMemory = emulator.getCommandsList();
        int length = emulator.getMemorySize();
        for (int i = 0; i < length; ++i) {
            StringBuilder address = new StringBuilder(Integer.toString(i, 16));
            while (address.length() != 4) {
                address.insert(0, "0");
            }
            dataSourceForMemoryTable[i][0] = address.toString();
            dataSourceForMemoryTable[i][1] = commandsInMemory[i];
            dataSourceForMemoryTable[i][2] =
                    Integer.toString(emulator
                            .getValueFromMemoryByAddress(i), 16);
        }
    }

    private void getDataSourceForRegistersTable(String[][] dataSourceForRegistersTable) {
        dataSourceForRegistersTable[0][0] = "Register A";
        dataSourceForRegistersTable[0][1]
                = createString(emulator.getValueFromRegister(Registers.A), 16);
        dataSourceForRegistersTable[0][2]
                = createString(emulator.getValueFromRegister(Registers.A), 2);
        dataSourceForRegistersTable[0][3]
                = Integer.toString(emulator.getValueFromRegister(Registers.A));

        dataSourceForRegistersTable[1][0] = "Register B";
        dataSourceForRegistersTable[1][1]
                = createString(emulator.getValueFromRegister(Registers.B), 16);
        dataSourceForRegistersTable[1][2]
                = createString(emulator.getValueFromRegister(Registers.B), 2);
        dataSourceForRegistersTable[1][3]
                = Integer.toString(emulator.getValueFromRegister(Registers.B));

        dataSourceForRegistersTable[2][0] = "Register C";
        dataSourceForRegistersTable[2][1]
                = createString(emulator.getValueFromRegister(Registers.C), 16);
        dataSourceForRegistersTable[2][2]
                = createString(emulator.getValueFromRegister(Registers.C), 2);
        dataSourceForRegistersTable[2][3]
                = Integer.toString(emulator.getValueFromRegister(Registers.C));

        dataSourceForRegistersTable[3][0] = "Register D";
        dataSourceForRegistersTable[3][1]
                = createString(emulator.getValueFromRegister(Registers.D), 16);
        dataSourceForRegistersTable[3][2]
                = createString(emulator.getValueFromRegister(Registers.D), 2);
        dataSourceForRegistersTable[3][3]
                = Integer.toString(emulator.getValueFromRegister(Registers.D));

        dataSourceForRegistersTable[4][0] = "Register E";
        dataSourceForRegistersTable[4][1]
                = createString(emulator.getValueFromRegister(Registers.E), 16);
        dataSourceForRegistersTable[4][2]
                = createString(emulator.getValueFromRegister(Registers.E), 2);
        dataSourceForRegistersTable[4][3]
                = Integer.toString(emulator.getValueFromRegister(Registers.E));

        dataSourceForRegistersTable[5][0] = "Register H";
        dataSourceForRegistersTable[5][1]
                = createString(emulator.getValueFromRegister(Registers.H), 16);
        dataSourceForRegistersTable[5][2]
                = createString(emulator.getValueFromRegister(Registers.H), 2);
        dataSourceForRegistersTable[5][3]
                = Integer.toString(emulator.getValueFromRegister(Registers.H));

        dataSourceForRegistersTable[6][0] = "Register L";
        dataSourceForRegistersTable[6][1]
                = createString(emulator.getValueFromRegister(Registers.L), 16);
        dataSourceForRegistersTable[6][2]
                = createString(emulator.getValueFromRegister(Registers.L), 2);
        dataSourceForRegistersTable[6][3]
                = Integer.toString(emulator.getValueFromRegister(Registers.L));

        dataSourceForRegistersTable[7][0] = "Register PC";
        dataSourceForRegistersTable[7][1]
                = createString(emulator.getValueFromRegister(Registers.PC), 16);

        dataSourceForRegistersTable[8][0] = "Register SP";
        dataSourceForRegistersTable[8][1]
                = createString(emulator.getValueFromRegister(Registers.SP), 16);

        dataSourceForRegistersTable[9][0] = "Flag Z";
        dataSourceForRegistersTable[9][1]
                = String.valueOf(emulator.getValueFromFlag(Flags.Z));

        dataSourceForRegistersTable[10][0] = "Flag C";
        dataSourceForRegistersTable[10][1]
                = String.valueOf(emulator.getValueFromFlag(Flags.C));

        dataSourceForRegistersTable[11][0] = "Flag S";
        dataSourceForRegistersTable[11][1]
                = String.valueOf(emulator.getValueFromFlag(Flags.S));

        dataSourceForRegistersTable[12][0] = "Flag P";
        dataSourceForRegistersTable[12][1]
                = String.valueOf(emulator.getValueFromFlag(Flags.P));
    }

    // TODO Подумать над именем функции
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