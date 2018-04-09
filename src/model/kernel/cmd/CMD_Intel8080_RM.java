package model.kernel.cmd;

import model.kernel.ICommandExecuteEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_RM extends CMD_Intel8080_RET {
    @Override
    public void execute(ICommandExecuteEventsListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.S) == 1) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "RM";
    }
}