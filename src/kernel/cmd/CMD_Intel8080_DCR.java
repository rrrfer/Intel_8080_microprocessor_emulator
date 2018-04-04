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
        if (value % 256 == 0) {
            microprocessor.setValueByFlagName("Z", 1);
        } else {
            microprocessor.setValueByFlagName("Z", 0);
        }

        if (value < 0) {
            microprocessor.setValueByFlagName("S", 1);
        } else {
            microprocessor.setValueByFlagName("S", 0);
        }

        int tmpValue = value;
        if (tmpValue < 0) tmpValue += 256;

        int counter = 0;
        for (int i = 0; i < 8; ++i) {
            counter += tmpValue % 2;
            tmpValue = tmpValue >> 1;
        }

        microprocessor.setValueByFlagName("P", (counter + 1) % 2);
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