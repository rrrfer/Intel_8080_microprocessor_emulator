package model.kernel.cmd;

import model.kernel.ICommandExecuteEventsListener;
import model.kernel.Registers;

public class CMD_Intel8080_STA implements ICommand {

    private String arg;

    public CMD_Intel8080_STA(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(ICommandExecuteEventsListener executeListener) {
        int value = executeListener.requestOnGetValueFromRegister(Registers.A);
        int address = Integer.valueOf(arg, 16);
        executeListener.requestOnSetValueInMemoryByAddress(address, value);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "STA " + arg;
    }
}