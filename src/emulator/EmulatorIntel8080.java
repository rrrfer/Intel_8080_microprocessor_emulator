package emulator;

import kernel.*;
import kernel.cmd.ICommand;
import translator.CommandsBuilder;
import translator.CommandsCodes;
import translator.ITranslator;
import translator.Intel8080Translator;

import java.util.ArrayList;

public class EmulatorIntel8080 implements IEmulator {

    private IMicroprocessor microprocessor;
    private ITranslator translator;

    public EmulatorIntel8080() {
        this.microprocessor = new Intel8080(new Memory(65536));
        this.translator = new Intel8080Translator();
    }

    @Override
    public void run() {
        while (step()) {
            if (Thread.interrupted()) {
                break;
            }
        }
    }

    @Override
    public boolean step() {
        int address = microprocessor.getValueByRegisterName("PC");
        ICommand command = CommandsBuilder.getCommand(microprocessor.getReadOnlyMemory(), address);
        if (!command.getName().equals("HLT")) {
            microprocessor.executeCommand(command);
            return true;
        }
        return false;
    }

    @Override
    public boolean loadProgram(String program) {
        IMemory memory = microprocessor.getMemory();
        String[] lexemes = translator.getLexemes(program);
        if (lexemes != null) {
            for (String lex : lexemes) {
                int[] code = translator.getCode(lex);
                int address = code[0];
                if (code[2] != CommandsCodes.SET) {
                    memory.setValueByIndex(address, code[2]);
                    if (code[3] >= 0) {
                        if (code[1] == 2) {
                            memory.setValueByIndex(address + 1, code[3]);
                        } else {
                            memory.setValueByIndex(address + 1, code[3] / 256);
                            memory.setValueByIndex(address + 2, code[3] % 256);
                        }
                    }
                } else {
                    memory.setValueByIndex(address, code[3]);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String getTranslationResult() {
        return translator.getStatusString();
    }

    @Override
    public String[] getCommandsList() {
        ArrayList<String> commands = new ArrayList<>();
        int address = 0;
        while (address < 65536) {
            ICommand command
                    = CommandsBuilder.getCommand(microprocessor.getMemory(), address);
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
    public IReadOnlyMicroprocessor getViewInterface() {
        return microprocessor;
    }

    @Override
    public void resetRegisters() {
        microprocessor.resetRegisters();
    }

    @Override
    public void resetMemory() {
        microprocessor.resetMemory();
    }
}