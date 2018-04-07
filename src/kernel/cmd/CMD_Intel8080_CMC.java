package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;

public class CMD_Intel8080_CMC implements ICommand {
    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value = microprocessor.getValueByFlagName(Intel8080Flags.C);
        value = (value + 1) % 2;
        microprocessor.setValueByFlagName(Intel8080Flags.C, value);
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