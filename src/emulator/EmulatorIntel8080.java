package emulator;

import kernel.*;
import translator.CommandsCodes;
import translator.ITranslator;
import translator.Intel8080Translator;

class EmulatorIntel8080 implements IEmulator {

    private IMicroprocessor microprocessor;
    private ITranslator translator;

    EmulatorIntel8080() {
        this.microprocessor = new Intel8080(new Memory(65536));
        this.translator = new Intel8080Translator();
    }

    @Override
    public IViewMicroprocessor run() {
        return null;
    }

    @Override
    public IViewMicroprocessor step() {
        return null;
    }

    @Override
    public void loadProgram(String program) {
        IMemory memory = microprocessor.getMemory();
        String[] lexemes = translator.getLexemes(program);
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
            }
        }
    }

    @Override
    public boolean hasErrors() {
        return translator.getStatusFlag();
    }

    @Override
    public String getTranslationsResult() {
        return translator.getStatusString();
    }
}
