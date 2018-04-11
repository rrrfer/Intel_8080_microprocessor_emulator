package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Registers;

public class CMD_Intel8080_JMP implements ICommand {

    protected String arg;

    public CMD_Intel8080_JMP() {
        this.arg = "0x0000";
    }

    @Override
    public void setArgument(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int address = Integer.valueOf(arg, 16);
        executeListener.requestOnSetValueInRegister(Registers.PC, address);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "JMP " + arg;
    }
}