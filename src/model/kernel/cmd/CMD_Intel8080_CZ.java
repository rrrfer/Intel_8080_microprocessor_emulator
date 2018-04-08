package model.kernel.cmd;

import model.kernel.ICommandsExecuteListener;
import model.kernel.Flags;

public class CMD_Intel8080_CZ  extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CZ(String arg) {
        super(arg);
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.Z) == 1) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "CZ " + arg;
    }
}