package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;

public class CMD_Intel8080_STC implements ICommand {
    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        microprocessor.setValueInFlag(Intel8080Flags.C, 1);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "STC";
    }
}