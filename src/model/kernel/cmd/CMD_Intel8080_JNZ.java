package model.kernel.cmd;

import model.kernel.ICommandsExecuteListener;
import model.kernel.Flags;

public class CMD_Intel8080_JNZ extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JNZ(String arg) {
        super(arg);
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        if (executeListener.requestOnGetValueFromFlag(Flags.Z) == 0) {
            super.execute(executeListener);
        }
    }

    @Override
    public String getName() {
        return "JNZ " + arg;
    }
}