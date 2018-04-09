package model.kernel.cmd;

import model.kernel.ICommandExecuteEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_JM extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JM(String arg) {
        super(arg);
    }

    @Override
    public void execute(ICommandExecuteEventsListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.S) == 1) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "JM " + arg;
    }
}