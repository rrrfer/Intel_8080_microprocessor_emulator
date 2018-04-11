package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Registers;

public class CMD_Intel8080_LDA implements ICommand {

    private String arg;

    public CMD_Intel8080_LDA() {
        this.arg = "0x0000";
    }

    @Override
    public void setArgument(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int address = Integer.valueOf(arg, 16);
        int value = executeListener.requestOnGetValueFromMemoryByAddress(address);
        executeListener.requestOnSetValueInRegister(Registers.A, value);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "LDA " + arg;
    }
}