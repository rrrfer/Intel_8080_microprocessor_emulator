package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.Flags;

public class CMD_Intel8080_RPE extends CMD_Intel8080_RET {
    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.P) == 1) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "RPE";
    }
}