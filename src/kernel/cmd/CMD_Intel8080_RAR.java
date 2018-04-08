package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.Flags;
import kernel.Registers;

public class CMD_Intel8080_RAR implements ICommand {
    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int value = executeListener.requestOnGetValueFromRegister(Registers.A);
        if (executeListener.requestOnGetValueFromFlag(Flags.C) == 1) {
            value += 256;
        }
        if (value % 2 == 1) {
            executeListener.requestOnSetValueInFlag(Flags.C, 1);
        } else {
            executeListener.requestOnSetValueInFlag(Flags.C, 0);
        }
        value = value >> 1;
        executeListener.requestOnSetValueInRegister(Registers.A, value);
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