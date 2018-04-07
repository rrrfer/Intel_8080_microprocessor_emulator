package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel._Byte;

public class CMD_Intel8080_SUI implements ICommand {

    private String arg;

    public CMD_Intel8080_SUI(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int firstValue = microprocessor.getValueByRegisterName("A");
        int secondValue = Integer.valueOf(arg, 16);
        firstValue -= secondValue;
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
        return "SUI " + arg;
    }
}