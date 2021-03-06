package model.emulator;

import model.kernel.*;
import model.kernel.cmd.CMD_Intel8080_CALL;
import model.kernel.cmd.ICommand;
import presenter.IIntraProgramIOUpdateEventsListener;
import model.translator.ComandsFactory;
import model.translator.Intel8080CommandsCodes;
import model.translator.ITranslator;
import model.translator.Intel8080Translator;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class EmulatorIntel8080 implements IEmulator {

    private IMicroprocessor microprocessor;

    private ITranslator translator;

    private IScreen pixelScreen;
    private IScreen characterScreen;

    private int[] breakpoints;

    private ArrayList<IExternalPeripheral> externalPeripherals;

    public EmulatorIntel8080() {
        this.microprocessor = new Microprocessor(65536);
        this.translator = new Intel8080Translator();
        this.breakpoints = new int[65536];
    }

    @Override
    public void translation(String program) {
        String[] lexemes = translator.getLexemes(program);
        if (lexemes != null) {
            for (String lex : lexemes) {
                int[] code = translator.getCode(lex);
                int address = code[0];
                if (code[2] != Intel8080CommandsCodes.SET) {
                    microprocessor.setValueInMemoryByAddress(address, code[2]);
                    if (code[3] >= 0) {
                        if (code[1] == 2) {
                            microprocessor.setValueInMemoryByAddress(address + 1, code[3]);
                        } else {
                            microprocessor.setValueInMemoryByAddress(address + 1, code[3] / 256);
                            microprocessor.setValueInMemoryByAddress(address + 2, code[3] % 256);
                        }
                    }
                } else {
                    microprocessor.setValueInMemoryByAddress(address, code[3]);
                }
            }
        }
    }

    @Override
    public void run() {
        while (!step()) {
            if (isBreakpoint(microprocessor.getValueFromRegister(Registers.PC))) {
                break;
            }
        }
    }

    @Override
    public boolean step() {
        int address = microprocessor.getValueFromRegister(Registers.PC);
        if (microprocessor.getValueFromMemoryByAddress(address) != Intel8080CommandsCodes.HLT) {
            ICommand command = ComandsFactory.createCommand(microprocessor, address);
            microprocessor.executeCommand(command);
            interrupt();
            return false;
        }
        return true;
    }

    private void interrupt() {
        for (int i = 0; i < externalPeripherals.size(); ++i) {
            if (externalPeripherals.get(i)._isInterrupted()) {
                if (externalPeripherals.get(i)._getPriority() < microprocessor.getExecutionLevel()) {
                    int address = 0x08 * externalPeripherals.get(i)._getPriority();
                    ICommand rst = new CMD_Intel8080_CALL();
                    rst.setArgument(Integer.toString(address, 16));
                    microprocessor.setExecutionLevel(externalPeripherals.get(i)._getPriority());
                    microprocessor.interrupt(rst);
                }
            }
        }
    }

    @Override
    public String getErrors() {
        return new Date().toString()
                + System.lineSeparator()
                + translator.getTranslationStatusString();
    }

    @Override
    public String[] getCommandsList() {
        ArrayList<String> commands = new ArrayList<>();
        int address = 0;
        while (address < 65536) {
            ICommand command
                    = ComandsFactory.createCommand(microprocessor, address);
            commands.add(command.getName());
            for (int i = 1; i < command.getSize(); ++i) {
                address += 1;
                commands.add("***");
            }
            address += 1;
        }
        String[] output = new String[commands.size()];
        for (int i = 0; i < output.length; ++i) {
            output[i] = commands.get(i);
        }
        return output;
    }

    @Override
    public void resetRegisters() {
        microprocessor.resetRegisters();
    }

    @Override
    public void resetMemory() {
        microprocessor.resetMemory();
    }

    @Override
    public void clearScreen() {
        if (pixelScreen != null) {
            pixelScreen.clear();
        }
        if (characterScreen != null) {
            characterScreen.clear();
        }
    }

    @Override
    public void setProgramCounter(int address) {
        microprocessor.setValueInRegister(Registers.PC, address);
    }

    @Override
    public void setBreakpoint(int address) {
        breakpoints[address] = (breakpoints[address] + 1) % 2;
    }

    @Override
    public int[] getBreakpoints() {
        return breakpoints;
    }

    @Override
    public void removeAllBreakpoints() {
        for (int i = 0; i < 65536; ++i) {
            breakpoints[i] = 0;
        }
    }

    @Override
    public String loadProgramFromFile(String path) throws IOException {
        BufferedReader bufferedReader
                = new BufferedReader(new FileReader(new File(path)));
        StringBuilder program = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            program.append(line).append(System.lineSeparator());
        }
        return program.toString();
    }

    @Override
    public void saveProgramInFile(String path, String programText) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(programText);
        fileWriter.close();
    }

    @Override
    public int[][] getPixelScreenMemory() {
        return pixelScreen.getColorMemory();
    }

    @Override
    public int[][] getCharacterScreenColorMemory() {
        return characterScreen.getColorMemory();
    }

    @Override
    public int[][] getCharacterScreenCharMemory() {
        return characterScreen.getCharMemory();
    }

    @Override
    public int getValueFromRegister(Registers register) {
        return microprocessor.getValueFromRegister(register);
    }

    @Override
    public int getValueFromMemoryByAddress(int address) {
        return microprocessor.getValueFromMemoryByAddress(address);
    }

    @Override
    public int getValueFromFlag(Flags flag) {
        return microprocessor.getValueFromFlag(flag);
    }

    @Override
    public int getMemorySize() {
        return microprocessor.getMemorySize();
    }

    @Override
    public void setIntraProgramIOUpdateEventsListener
            (IIntraProgramIOUpdateEventsListener listener) {

        characterScreen = new CharacterScreen(20, 20);
        pixelScreen = new PixelScreen(256, 256);

        this.externalPeripherals = new ArrayList<>();

        IIntraProgramIOEventsListener actionsListener
                = new IOPeripheralSystem(listener, pixelScreen, characterScreen, externalPeripherals);

        microprocessor.setIntraProgramIOEventsListener(actionsListener);
    }

    @Override
    public boolean hasTranslationErrors() {
        return translator.hasTranslationErrors();
    }

    @Override
    public ArrayList<String> getLabel2AddressList() {
        return translator.getLabel2AddressList();
    }

    @Override
    public ArrayList<IExternalPeripheral> getExternalPeripheral() {
        return externalPeripherals;
    }

    private boolean isBreakpoint(int address) {
        return breakpoints[address] == 1;
    }
}