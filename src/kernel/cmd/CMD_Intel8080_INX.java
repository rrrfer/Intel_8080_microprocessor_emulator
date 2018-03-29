package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_INX implements ICommand {

    private String arg;

    public CMD_Intel8080_INX(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int value;
        if (!arg.equals("SP")) {
            value = microprocessor.getValueByRegisterPairName(arg);
        } else {
            value = microprocessor.getValueByRegisterName("SP");
        }

        value += 1;
        microprocessor.checkWordForSetFlags(value);
        value = microprocessor.getRoundedWord(value);

        if (!arg.equals("SP")) {
            microprocessor.setValueByRegisterPairName(arg, value);
        } else {
            microprocessor.setValueByRegisterName("SP", value);
        }
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "INX " + arg;
    }
}