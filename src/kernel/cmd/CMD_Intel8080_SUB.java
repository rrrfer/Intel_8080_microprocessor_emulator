package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.RegisterPairs;
import kernel.Registers;
import kernel._Byte;

public class CMD_Intel8080_SUB implements ICommand {

    private Registers register;

    public CMD_Intel8080_SUB(Registers register) {
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
        firstValue = firstValue - secondValue;
        executeListener.requestOnCheckByteForSetFlags(firstValue);
        firstValue = _Byte.getRoundedValue(firstValue);
        executeListener.requestOnSetValueInRegister(Registers.A, firstValue);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "SUB " + register;
    }
}
