package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;

public class CMD_NULL implements ICommand {
    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {

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