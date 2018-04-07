package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Registers;

public class CMD_Intel8080_JMP implements ICommand {

    protected String arg;

    public CMD_Intel8080_JMP(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int address = Integer.valueOf(arg, 16);
        microprocessor.setValueInRegister(Intel8080Registers.PC, address);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "JMP " + arg;
    }
}