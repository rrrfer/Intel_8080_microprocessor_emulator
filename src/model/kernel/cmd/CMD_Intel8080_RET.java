package model.kernel.cmd;

import model.kernel.ICommandsExecuteListener;
import model.kernel.Registers;

public class CMD_Intel8080_RET implements ICommand {
    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int address = CMD_Intel8080_POP.pop(executeListener);
        executeListener.requestOnSetValueInRegister(Registers.PC, address);
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