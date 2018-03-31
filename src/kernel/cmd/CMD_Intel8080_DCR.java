package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_DCR implements ICommand {

    private String arg;

    public CMD_Intel8080_DCR(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int value;
        if (arg.equals("M")) {
            int address = microprocessor.getValueByRegisterPairName("H");
            value = microprocessor.getReadOnlyMemory().getValueByIndex(address);
        } else {
            value = microprocessor.getValueByRegisterName(arg);
        }

        value -= 1;
        microprocessor.checkByteForSetFlags(value);
        value = microprocessor.getRoundedByte(value);

        if (arg.equals("M")) {
            int address = microprocessor.getValueByRegisterPairName("H");
            microprocessor.getMemory().setValueByIndex(address, value);
        } else {
            microprocessor.setValueByRegisterName(arg, value);
        }
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "DCR " + arg;
    }
}