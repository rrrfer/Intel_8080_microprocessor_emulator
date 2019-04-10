package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_JNC extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JNC() {
        super();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.C) == 0) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "JNC " + arg;
    }
}
