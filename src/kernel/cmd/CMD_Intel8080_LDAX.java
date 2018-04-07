package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080RegisterPairs;
import kernel.Intel8080Registers;

public class CMD_Intel8080_LDAX implements ICommand {

    private Intel8080RegisterPairs registePair;

    public CMD_Intel8080_LDAX(Intel8080RegisterPairs registePair) {
        this.registePair = registePair;
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int address = microprocessor.getValueFromRegisterPair(registePair);
        int value = microprocessor.getMemory().getValueByIndex(address);
        microprocessor.setValueInRegister(Intel8080Registers.A, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "LDAX " + registePair;
    }
}