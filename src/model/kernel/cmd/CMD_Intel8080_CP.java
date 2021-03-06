package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_CP  extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CP() {
        super();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.S) == 0) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "CP " + arg;
    }
}