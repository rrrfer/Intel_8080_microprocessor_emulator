package model.kernel.cmd;

import model.kernel.ICommandExecuteEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_CP  extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CP(String arg) {
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
        return "CP " + arg;
    }
}