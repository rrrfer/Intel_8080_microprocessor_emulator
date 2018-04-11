package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Registers;
import model.kernel._DByte;

public class CMD_Intel8080_LHLD implements ICommand {

    private String arg;

    public CMD_Intel8080_LHLD() {
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
        executeListener.requestOnSetValueInRegister(Registers.L, value);
        address = _DByte.getRoundedValue(address + 1);
        value = executeListener.requestOnGetValueFromMemoryByAddress(address);
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