package model.kernel.cmd;

import model.kernel.ICommandsExecuteListener;
import model.kernel.Registers;

public class CMD_Intel8080_LDA implements ICommand {

    private String arg;

    public CMD_Intel8080_LDA(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int address = Integer.valueOf(arg, 16);
        int value = executeListener.requestOnGetValueFromMemoryByAddress(address);
        executeListener.requestOnSetValueInRegister(Registers.A, value);
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