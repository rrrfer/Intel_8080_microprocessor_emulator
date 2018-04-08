package model.kernel.cmd;

import model.kernel.ICommandsExecuteListener;
import model.kernel.Registers;

public class CMD_Intel8080_ORI implements ICommand {

    private String arg;

    public CMD_Intel8080_ORI(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int firstValue = executeListener.requestOnGetValueFromRegister(Registers.A);
        int secondValue = Integer.valueOf(arg, 16);
        firstValue = firstValue | secondValue;
        executeListener.requestOnCheckByteForSetFlags(firstValue);
        executeListener.requestOnSetValueInRegister(Registers.A, firstValue);
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getName() {
        return "ORI " + arg;
    }
}