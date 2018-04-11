package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Flags;
import model.kernel.Registers;
import model.kernel._Byte;

public class CMD_Intel8080_SBI implements ICommand {

    private String arg;

    public CMD_Intel8080_SBI() {
        this.arg = "0x00";
    }

    @Override
    public void setArgument(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int firstValue = executeListener.requestOnGetValueFromRegister(Registers.A);
        int secondValue = Integer.valueOf(arg, 16);
        firstValue = firstValue - secondValue - executeListener.requestOnGetValueFromFlag(Flags.C);
        executeListener.requestOnCheckByteForSetFlags(firstValue);
        firstValue = _Byte.getRoundedValue(firstValue);
        executeListener.requestOnSetValueInRegister(Registers.A, firstValue);
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getName() {
        return "SBI " + arg;
    }
}