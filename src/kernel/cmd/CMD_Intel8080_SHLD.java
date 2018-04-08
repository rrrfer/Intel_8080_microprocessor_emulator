package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.Registers;

public class CMD_Intel8080_SHLD implements ICommand {

    private String arg;

    public CMD_Intel8080_SHLD(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int address = Integer.valueOf(arg, 16);
        int value = executeListener.requestOnGetValueFromRegister(Registers.L);
        executeListener.requestOnSetValueInMemoryByAddress(address, value);
        value = executeListener.requestOnGetValueFromRegister(Registers.H);
        // TODO address + 1
        executeListener.requestOnSetValueInMemoryByAddress(address + 1, value);
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