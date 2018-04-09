package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.RegisterPairs;
import model.kernel.Registers;

public class CMD_Intel8080_MVI implements ICommand {

    private Registers firstRegister;
    private String arg;

    public CMD_Intel8080_MVI(Registers firstRegister, String arg) {
        this.firstRegister = firstRegister;
        this.arg = arg;
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int value = Integer.valueOf(arg, 16);
        if (firstRegister == Registers.M) {
            int address = executeListener.requestOnGetValueFromRegisterPair(RegisterPairs.H);
            executeListener.requestOnSetValueInMemoryByAddress(address, value);
        } else {
            executeListener.requestOnSetValueInRegister(firstRegister, value);
        }
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getName() {
        return "MVI " + firstRegister + "," + arg;
    }
}