package model.kernel.cmd;

import model.kernel.ICommandsExecuteListener;
import model.kernel.RegisterPairs;
import model.kernel.Registers;

public class CMD_Intel8080_ORA implements ICommand {

    private Registers register;

    public CMD_Intel8080_ORA(Registers register) {
        this.register = register;
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int firstValue = executeListener.requestOnGetValueFromRegister(Registers.A);
        int secondValue;
        if (register == Registers.M) {
            int address = executeListener.requestOnGetValueFromRegisterPair(RegisterPairs.H);
            secondValue = executeListener.requestOnGetValueFromMemoryByAddress(address);
        } else {
            secondValue = executeListener.requestOnGetValueFromRegister(register);
        }

        firstValue = firstValue | secondValue;
        executeListener.requestOnCheckByteForSetFlags(firstValue);

        executeListener.requestOnSetValueInRegister(Registers.A, firstValue);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "ORA " + register;
    }
}