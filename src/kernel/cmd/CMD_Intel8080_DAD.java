package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_DAD implements ICommand {

    private String arg;

    public CMD_Intel8080_DAD(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int firstValue;
        if (!arg.equals("SP")) {
            firstValue = microprocessor.getValueByRegisterPairName(arg);
        } else {
            firstValue = microprocessor.getValueByRegisterName("SP");
        }

        int secondValue = microprocessor.getValueByRegisterPairName("H");

        secondValue += firstValue;
        microprocessor.checkWordForSetFlags(secondValue);
        secondValue = microprocessor.getRoundedWord(secondValue);

        microprocessor.setValueByRegisterPairName("H", secondValue);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "DAD " + arg;
    }
}