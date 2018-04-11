package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_CPE  extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CPE() {
        super();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.P) == 1) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "CPE " + arg;
    }
}