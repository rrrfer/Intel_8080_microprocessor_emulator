package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_RC extends CMD_Intel8080_RET {
    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.C) == 1) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "RC";
    }
}