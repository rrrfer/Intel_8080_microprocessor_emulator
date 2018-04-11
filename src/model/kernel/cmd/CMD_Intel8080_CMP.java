package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.RegisterPairs;
import model.kernel.Registers;

public class CMD_Intel8080_CMP implements ICommand {

    private Registers register;

    public CMD_Intel8080_CMP(Registers register) {
        this.register = register;
    }

    @Override
    public void setArgument(String arg) {}

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int firstValue = executeListener.requestOnGetValueFromRegister(Registers.A);
        int secondValue;
        if (register == Registers.M) {
            int address = executeListener.requestOnGetValueFromRegisterPair(RegisterPairs.H);
            secondValue = executeListener.requestOnGetValueFromMemoryByAddress(address);
        } else {
            secondValue = executeListener.requestOnGetValueFromRegister(register);
        }
        firstValue = firstValue - secondValue;
        executeListener.requestOnCheckByteForSetFlags(firstValue);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "CMP " + register;
    }
}