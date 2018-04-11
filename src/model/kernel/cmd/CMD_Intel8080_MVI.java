package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.RegisterPairs;
import model.kernel.Registers;

public class CMD_Intel8080_MVI implements ICommand {

    private Registers register;
    private String arg;

    public CMD_Intel8080_MVI(Registers register) {
        this.register = register;
        this.arg = "0xFF";
    }

    @Override
    public void setArgument(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int value = Integer.valueOf(arg, 16);
        if (register == Registers.M) {
            int address = executeListener.requestOnGetValueFromRegisterPair(RegisterPairs.H);
            executeListener.requestOnSetValueInMemoryByAddress(address, value);
        } else {
            executeListener.requestOnSetValueInRegister(register, value);
        }
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getName() {
        return "MVI " + register + "," + arg;
    }
}