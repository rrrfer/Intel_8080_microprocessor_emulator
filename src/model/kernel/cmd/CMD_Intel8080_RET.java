package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Registers;

public class CMD_Intel8080_RET implements ICommand {

    @Override
    public void setArgument(String arg) {}

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int address = CMD_Intel8080_POP.pop(executeListener);
        executeListener.requestOnSetValueInRegister(Registers.PC, address);
        executeListener.returnFromInterrupt();
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "RET";
    }
}