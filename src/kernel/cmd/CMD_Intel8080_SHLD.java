package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Registers;

public class CMD_Intel8080_SHLD implements ICommand {

    private String arg;

    public CMD_Intel8080_SHLD(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int address = Integer.valueOf(arg, 16);
        int value = microprocessor.getValueFromRegister(Intel8080Registers.L);
        microprocessor.getMemory().setValueByIndex(address, value);
        value = microprocessor.getValueFromRegister(Intel8080Registers.H);
        // TODO address + 1
        microprocessor.getMemory().setValueByIndex(address + 1, value);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "SHLD " + arg;
    }
}