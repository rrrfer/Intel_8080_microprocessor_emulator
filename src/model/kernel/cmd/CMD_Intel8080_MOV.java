package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.RegisterPairs;
import model.kernel.Registers;

public class CMD_Intel8080_MOV implements ICommand {

    private Registers firstRegister;
    private Registers secondRegister;

    public CMD_Intel8080_MOV(Registers firstRegister, Registers secondRegister) {
        this.firstRegister = firstRegister;
        this.secondRegister = secondRegister;
    }

    @Override
    public void setArgument(String arg) {}

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int value;

        if (secondRegister == Registers.M) {
            int address = executeListener.requestOnGetValueFromRegisterPair(RegisterPairs.H);
            value = executeListener.requestOnGetValueFromMemoryByAddress(address);
        } else {
            value = executeListener.requestOnGetValueFromRegister(secondRegister);
        }

        if (firstRegister == Registers.M) {
           int address = executeListener.requestOnGetValueFromRegisterPair(RegisterPairs.H);
           executeListener.requestOnSetValueInMemoryByAddress(address, value);
        } else {
            executeListener.requestOnSetValueInRegister(firstRegister, value);
        }
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "MOV " + firstRegister + "," + secondRegister;
    }
}
