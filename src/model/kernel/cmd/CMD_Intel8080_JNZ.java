package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_JNZ extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JNZ() {
        super();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.Z) == 0) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "JNZ " + arg;
    }
}