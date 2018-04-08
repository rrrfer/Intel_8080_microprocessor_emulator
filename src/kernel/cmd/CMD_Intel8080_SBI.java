package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.Flags;
import kernel.Registers;
import kernel._Byte;

public class CMD_Intel8080_SBI implements ICommand {

    private String arg;

    public CMD_Intel8080_SBI(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
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