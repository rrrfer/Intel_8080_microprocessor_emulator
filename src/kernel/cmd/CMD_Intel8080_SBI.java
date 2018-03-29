package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_SBI implements ICommand {

    private String arg;

    public CMD_Intel8080_SBI(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int firstValue = microprocessor.getValueByRegisterName("A");
        int secondValue = Integer.valueOf(arg, 16);
        firstValue = firstValue - secondValue - microprocessor.getValueByFlagName("C");
        microprocessor.checkByteForSetFlags(firstValue);
        firstValue = microprocessor.getRoundedByte(firstValue);
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