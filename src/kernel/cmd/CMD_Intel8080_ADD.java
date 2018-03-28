package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_ADD implements ICommand {

    private String arg;

    public CMD_Intel8080_ADD(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int value = microprocessor.getValueByRegisterName("A");
        int secondValue;
        if (arg.equals("M")) {
            int address = microprocessor.getValueByRegisterPairName("H");
            secondValue = microprocessor.getMemory().getValueByIndex(address);
        } else {
            secondValue = microprocessor.getValueByRegisterName(arg);
        }
        value = value + secondValue;
        microprocessor.checkValueForSetFlags(value);
        value = microprocessor.getRoundedValue(value);
        microprocessor.setValueByRegisterName("A", value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "ADD " + arg;
    }
}
