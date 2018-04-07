package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080RegisterPairs;
import kernel.Intel8080Registers;

public class CMD_Intel8080_PCHL implements ICommand {
    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int address = microprocessor.getValueFromRegisterPair(Intel8080RegisterPairs.H);
        microprocessor.setValueInRegister(Intel8080Registers.PC, address);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "PCHL";
    }
}