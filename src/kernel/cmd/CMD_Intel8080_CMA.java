package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.Registers;

public class CMD_Intel8080_CMA implements ICommand {
    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int value = executeListener.requestOnGetValueFromRegister(Registers.A);
        value = 255 - value;
        executeListener.requestOnSetValueInRegister(Registers.A, value);
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