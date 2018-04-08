package model.kernel.cmd;

import model.kernel.ICommandsExecuteListener;
import model.kernel.Flags;

public class CMD_Intel8080_JC extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JC(String arg) {
        super(arg);
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.C) == 1) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "JC " + arg;
    }
}