package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;
import kernel._Byte;

public class CMD_Intel8080_RLC implements ICommand {
    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value = microprocessor.getValueByRegisterName("A");
        value = value << 1;
        if (value > 255) {
            value += 1;
            microprocessor.setValueByFlagName(Intel8080Flags.C, 1);
        } else {
            microprocessor.setValueByFlagName(Intel8080Flags.C, 0);
        }
        value = _Byte.getRoundedValue(value);
        microprocessor.setValueByRegisterName("A", value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "RLC";
    }
}