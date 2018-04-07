package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;
import kernel.Intel8080Registers;
import kernel._Byte;

public class CMD_Intel8080_RLC implements ICommand {
    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value = microprocessor.getValueFromRegister(Intel8080Registers.A);
        value = value << 1;
        if (value > 255) {
            value += 1;
            microprocessor.setValueInFlag(Intel8080Flags.C, 1);
        } else {
            microprocessor.setValueInFlag(Intel8080Flags.C, 0);
        }
        value = _Byte.getRoundedValue(value);
        microprocessor.setValueInRegister(Intel8080Registers.A, value);
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