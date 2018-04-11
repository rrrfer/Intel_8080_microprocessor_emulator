package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Registers;

public class CMD_Intel8080_CPI implements ICommand {

    private String arg;

    public CMD_Intel8080_CPI() {
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
        firstValue -= secondValue;
        executeListener.requestOnCheckByteForSetFlags(firstValue);
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getName() {
        return "CPI " + arg;
    }
}