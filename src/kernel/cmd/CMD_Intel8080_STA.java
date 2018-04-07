package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Registers;

public class CMD_Intel8080_STA implements ICommand {

    private String arg;

    public CMD_Intel8080_STA(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value = microprocessor.getValueFromRegister(Intel8080Registers.A);
        int address = Integer.valueOf(arg, 16);
        microprocessor.getMemory().setValueByIndex(address, value);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "STA " + arg;
    }
}