package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;
import kernel.Intel8080Registers;
import kernel._Byte;

public class CMD_Intel8080_ACI implements ICommand {

    private String arg;

    public CMD_Intel8080_ACI(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int firstValue = Integer.valueOf(arg, 16);
        int secondValue = microprocessor.getValueFromRegister(Intel8080Registers.A);
        firstValue += secondValue + microprocessor.getValueFromFlag(Intel8080Flags.C);
        microprocessor.checkByteForSetFlags(firstValue);
        firstValue = _Byte.getRoundedValue(firstValue);
        microprocessor.setValueInRegister(Intel8080Registers.A, firstValue);
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getName() {
        return "ACI " + arg;
    }
}