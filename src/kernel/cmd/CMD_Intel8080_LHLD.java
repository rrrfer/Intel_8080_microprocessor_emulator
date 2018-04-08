package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.Registers;

public class CMD_Intel8080_LHLD implements ICommand {

    private String arg;

    public CMD_Intel8080_LHLD(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int address = Integer.valueOf(arg, 16);
        int value = executeListener.requestOnGetValueFromMemoryByAddress(address);
        executeListener.requestOnSetValueInRegister(Registers.L, value);
        // TODO address + 1
        value = executeListener.requestOnGetValueFromMemoryByAddress(address + 1);
        executeListener.requestOnSetValueInRegister(Registers.H, value);
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