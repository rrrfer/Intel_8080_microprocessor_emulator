package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Registers;
import model.kernel._DByte;

public class CMD_Intel8080_SHLD implements ICommand {

    private String arg;

    public CMD_Intel8080_SHLD(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int address = Integer.valueOf(arg, 16);
        int value = executeListener.requestOnGetValueFromRegister(Registers.L);
        executeListener.requestOnSetValueInMemoryByAddress(address, value);
        value = executeListener.requestOnGetValueFromRegister(Registers.H);
        address = _DByte.getRoundedValue(address + 1);
        executeListener.requestOnSetValueInMemoryByAddress(address, value);
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