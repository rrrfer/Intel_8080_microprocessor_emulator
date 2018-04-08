package kernel.cmd;

import kernel.ICommandsExecuteListener;

public class CMD_NULL implements ICommand {
    @Override
    public void execute(ICommandsExecuteListener executeListener) {

    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "(no command)";
    }
}