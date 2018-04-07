package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Registers;

public class CMD_Intel8080_LHLD implements ICommand {

    private String arg;

    public CMD_Intel8080_LHLD(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int address = Integer.valueOf(arg, 16);
        int value = microprocessor.getMemory().getValueByIndex(address);
        microprocessor.setValueInRegister(Intel8080Registers.L, value);
        // TODO address + 1
        value = microprocessor.getMemory().getValueByIndex(address + 1);
        microprocessor.setValueInRegister(Intel8080Registers.H, value);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "LHLD " + arg;
    }
}