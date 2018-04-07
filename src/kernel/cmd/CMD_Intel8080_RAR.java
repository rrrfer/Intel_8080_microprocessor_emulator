package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;
import kernel.Intel8080Registers;

public class CMD_Intel8080_RAR implements ICommand {
    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value = microprocessor.getValueFromRegister(Intel8080Registers.A);
        if (microprocessor.getValueFromFlag(Intel8080Flags.C) == 1) {
            value += 256;
        }
        if (value % 2 == 1) {
            microprocessor.setValueInFlag(Intel8080Flags.C, 1);
        } else {
            microprocessor.setValueInFlag(Intel8080Flags.C, 0);
        }
        value = value >> 1;
        microprocessor.setValueInRegister(Intel8080Registers.A, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "RAR";
    }
}