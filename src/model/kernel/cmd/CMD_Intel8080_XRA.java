package model.kernel.cmd;

import model.kernel.ICommandExecuteEventsListener;
import model.kernel.RegisterPairs;
import model.kernel.Registers;

public class CMD_Intel8080_XRA implements ICommand {

    private Registers register;

    public CMD_Intel8080_XRA(Registers register) {
        this.register = register;
    }

    @Override
    public void execute(ICommandExecuteEventsListener executeListener) {
        int firstValue = executeListener.requestOnGetValueFromRegister(Registers.A);
        int secondValue;
        if (register == Registers.M) {
            int address = executeListener.requestOnGetValueFromRegisterPair(RegisterPairs.H);
            secondValue = executeListener.requestOnGetValueFromMemoryByAddress(address);
        } else {
            secondValue = executeListener.requestOnGetValueFromRegister(register);
        }

        firstValue = firstValue ^ secondValue;
        executeListener.requestOnCheckByteForSetFlags(firstValue);

        executeListener.requestOnSetValueInRegister(Registers.A, firstValue);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "XRA " + register;
    }
}