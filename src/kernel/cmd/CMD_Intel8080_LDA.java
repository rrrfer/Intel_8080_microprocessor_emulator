package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Registers;

public class CMD_Intel8080_LDA implements ICommand {

    private String arg;

    public CMD_Intel8080_LDA(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int address = Integer.valueOf(arg, 16);
        int value = microprocessor.getMemory().getValueByIndex(address);
        microprocessor.setValueInRegister(Intel8080Registers.A, value);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "LDA " + arg;
    }
}