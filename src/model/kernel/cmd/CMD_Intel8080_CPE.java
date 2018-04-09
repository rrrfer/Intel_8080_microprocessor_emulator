package model.kernel.cmd;

import model.kernel.ICommandExecuteEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_CPE  extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CPE(String arg) {
        super(arg);
    }

    @Override
    public void execute(ICommandExecuteEventsListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.P) == 1) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "CPE " + arg;
    }
}