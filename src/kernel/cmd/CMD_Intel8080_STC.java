package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_STC implements ICommand {
    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        microprocessor.setValueByFlagName("C", 1);
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