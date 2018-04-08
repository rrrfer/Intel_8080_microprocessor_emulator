package model.kernel.cmd;

import model.kernel.ICommandsExecuteListener;
import model.kernel.Flags;
import model.kernel.Registers;
import model.kernel._Byte;

public class CMD_Intel8080_RAL implements ICommand {
    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int value = executeListener.requestOnGetValueFromRegister(Registers.A);
        value = value << 1;
        if (executeListener.requestOnGetValueFromFlag(Flags.C) == 1) {
            value += 1;
        }
        if (value > 255) {
            executeListener.requestOnSetValueInFlag(Flags.C, 1);
        } else {
            executeListener.requestOnSetValueInFlag(Flags.C, 0);
        }
        value = _Byte.getRoundedValue(value);
        executeListener.requestOnSetValueInRegister(Registers.A, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "RAL";
    }
}