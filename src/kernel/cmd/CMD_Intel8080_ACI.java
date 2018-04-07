package kernel.cmd;

import kernel.IMicroprocessor;
import kernel._Byte;

public class CMD_Intel8080_ACI implements ICommand {

    private String arg;

    public CMD_Intel8080_ACI(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int firstValue = Integer.valueOf(arg, 16);
        int secondValue = microprocessor.getValueByRegisterName("A");
        firstValue += secondValue + microprocessor.getValueByFlagName("C");
        microprocessor.checkByteForSetFlags(firstValue);
        firstValue = _Byte.getRoundedValue(firstValue);
        microprocessor.setValueByRegisterName("A", firstValue);
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