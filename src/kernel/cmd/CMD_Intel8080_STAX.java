package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080RegisterPairs;
import kernel.Intel8080Registers;

public class CMD_Intel8080_STAX implements ICommand {

    private Intel8080RegisterPairs registerPair;

    public CMD_Intel8080_STAX(Intel8080RegisterPairs registerPair) {
        this.registerPair = registerPair;
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value = microprocessor.getValueFromRegister(Intel8080Registers.A);
        int address = microprocessor.getValueFromRegisterPair(registerPair);
        microprocessor.getMemory().setValueByIndex(address, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "STAX " + registerPair;
    }
}