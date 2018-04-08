package model.kernel.cmd;

import model.kernel.ICommandsExecuteListener;
import model.kernel.Flags;

public class CMD_Intel8080_RNZ extends CMD_Intel8080_RET {
    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.Z) == 0) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "RNZ";
    }
}