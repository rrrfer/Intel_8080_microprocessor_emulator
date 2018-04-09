package model.kernel.cmd;

import model.kernel.ICommandExecuteEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_STC implements ICommand {
    @Override
    public void execute(ICommandExecuteEventsListener executeListener) {
        executeListener.requestOnSetValueInFlag(Flags.C, 1);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "STC";
    }
}