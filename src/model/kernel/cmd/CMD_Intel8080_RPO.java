package model.kernel.cmd;

import model.kernel.ICommandsExecuteListener;
import model.kernel.Flags;

public class CMD_Intel8080_RPO extends CMD_Intel8080_RET {
    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.P) == 0) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "RPO";
    }
}