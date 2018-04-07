package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Registers;

public class CMD_Intel8080_CMA implements ICommand {
    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value = microprocessor.getValueFromRegister(Intel8080Registers.A);
        value = 255 - value;
        microprocessor.setValueInRegister(Intel8080Registers.A, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "CMA";
    }
}