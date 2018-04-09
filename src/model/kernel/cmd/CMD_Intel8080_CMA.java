package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Registers;

public class CMD_Intel8080_CMA implements ICommand {
    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int value = executeListener.requestOnGetValueFromRegister(Registers.A);
        value = 255 - value;
        executeListener.requestOnSetValueInRegister(Registers.A, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "CMA";
    }
}