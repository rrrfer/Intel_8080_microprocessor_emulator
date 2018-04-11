package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Flags;
import model.kernel.Registers;
import model.kernel._Byte;

public class CMD_Intel8080_RLC implements ICommand {

    @Override
    public void setArgument(String arg) {}

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int value = executeListener.requestOnGetValueFromRegister(Registers.A);
        value = value << 1;
        if (value > 255) {
            value += 1;
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
        return "RLC";
    }
}