package model.kernel.cmd;

import model.kernel.ICommandExecuteEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_JP extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JP(String arg) {
        super(arg);
    }

    @Override
    public void execute(ICommandExecuteEventsListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.S) == 0) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "JP " + arg;
    }
}