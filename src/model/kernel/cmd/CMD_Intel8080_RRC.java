package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Flags;
import model.kernel.Registers;

public class CMD_Intel8080_RRC implements ICommand {

    @Override
    public void setArgument(String arg) {}

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int value = executeListener.requestOnGetValueFromRegister(Registers.A);
        if (value % 2 == 1) {
            value += 256;
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
        return "RRC";
    }
}