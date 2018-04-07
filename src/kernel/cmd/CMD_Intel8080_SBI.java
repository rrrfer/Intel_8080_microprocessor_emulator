package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;
import kernel._Byte;

public class CMD_Intel8080_SBI implements ICommand {

    private String arg;

    public CMD_Intel8080_SBI(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        int firstValue = microprocessor.getValueByRegisterName("A");
        int secondValue = Integer.valueOf(arg, 16);
        firstValue = firstValue - secondValue - microprocessor.getValueByFlagName("C");
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
        return "SBI " + arg;
    }
}