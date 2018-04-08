package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.Flags;

public class CMD_Intel8080_CNC  extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CNC(String arg) {
        super(arg);
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.C) == 0) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "CNC " + arg;
    }
}