package model.kernel.cmd;

import model.kernel.ICommandsExecuteListener;

public class CMD_Intel8080_NOP implements ICommand {

    @Override
    public void execute(ICommandsExecuteListener executeListener) {}

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "NOP";
    }
}