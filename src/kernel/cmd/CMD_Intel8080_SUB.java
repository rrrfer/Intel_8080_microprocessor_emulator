package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_SUB implements ICommand {

    private String arg;

    public CMD_Intel8080_SUB(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int value = microprocessor.getValueByRegisterName("A");
        value = value - microprocessor.getValueByRegisterName(arg);
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
        return "SUB " + arg;
    }
}
