package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;

public class CMD_Intel8080_RRC implements ICommand {
    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value = microprocessor.getValueByRegisterName("A");
        if (value % 2 == 1) {
            value += 256;
            microprocessor.setValueByFlagName(Intel8080Flags.C, 1);
        } else {
            microprocessor.setValueByFlagName(Intel8080Flags.C, 0);
        }
        value = value >> 1;
        microprocessor.setValueByRegisterName("A", value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "RRC";
    }
}