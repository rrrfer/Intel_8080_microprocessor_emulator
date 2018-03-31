package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_CMP implements ICommand {

    private String arg;

    public CMD_Intel8080_CMP(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int firstValue = microprocessor.getValueByRegisterName("A");
        int secondValue;
        if (arg.equals("M")) {
            int address = microprocessor.getValueByRegisterPairName("H");
            secondValue = microprocessor.getReadOnlyMemory().getValueByIndex(address);
        } else {
            secondValue = microprocessor.getValueByRegisterName(arg);
        }
        firstValue = firstValue - secondValue;
        microprocessor.checkByteForSetFlags(firstValue);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "CMP " + arg;
    }
}