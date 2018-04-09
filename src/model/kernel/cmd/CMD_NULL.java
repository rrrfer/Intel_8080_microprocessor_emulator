package model.kernel.cmd;

import model.kernel.ICommandExecuteEventsListener;

public class CMD_NULL implements ICommand {
    @Override
    public void execute(ICommandExecuteEventsListener executeListener) {

    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "(no command)";
    }
}