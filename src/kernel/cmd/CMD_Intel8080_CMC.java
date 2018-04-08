package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.Flags;

public class CMD_Intel8080_CMC implements ICommand {
    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int value = executeListener.requestOnGetValueFromFlag(Flags.C);
        value = (value + 1) % 2;
        executeListener.requestOnSetValueInFlag(Flags.C, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "CMC";
    }
}