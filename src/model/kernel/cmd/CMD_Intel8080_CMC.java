package model.kernel.cmd;

import model.kernel.ICommandExecuteEventsListener;
import model.kernel.Flags;

public class CMD_Intel8080_CMC implements ICommand {
    @Override
    public void execute(ICommandExecuteEventsListener executeListener) {
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