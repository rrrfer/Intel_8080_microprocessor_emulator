package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_JC extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JC() {
        super();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.C) == 1) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "JC " + arg;
    }
}