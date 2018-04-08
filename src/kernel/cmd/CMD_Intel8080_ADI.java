package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.Registers;
import kernel._Byte;

public class CMD_Intel8080_ADI implements ICommand {

    private String arg;

    public CMD_Intel8080_ADI(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int firstValue = Integer.valueOf(arg, 16);
        int secondValue = executeListener.requestOnGetValueFromRegister(Registers.A);
        firstValue += secondValue;
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
        return "ADI " + arg;
    }
}