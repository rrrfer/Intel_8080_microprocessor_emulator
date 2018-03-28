package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_ADI implements ICommand {

    private String arg;

    public CMD_Intel8080_ADI(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int firstValue = Integer.valueOf(arg, 16);
        int secondValue = microprocessor.getValueByRegisterName("A");
        firstValue += secondValue;
        microprocessor.checkValueForSetFlags(firstValue);
        firstValue = microprocessor.getRoundedValue(firstValue);
        microprocessor.setValueByRegisterPairName("A", firstValue);
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