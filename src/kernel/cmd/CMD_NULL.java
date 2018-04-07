package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;

public class CMD_NULL implements ICommand {
    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {

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