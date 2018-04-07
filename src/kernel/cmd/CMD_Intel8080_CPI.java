package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Registers;

public class CMD_Intel8080_CPI implements ICommand {

    private String arg;

    public CMD_Intel8080_CPI(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int firstValue = microprocessor.getValueFromRegister(Intel8080Registers.A);
        int secondValue = Integer.valueOf(arg, 16);
        firstValue -= secondValue;
        microprocessor.checkByteForSetFlags(firstValue);
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getName() {
        return "CPI " + arg;
    }
}