package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;
import kernel.Intel8080Registers;
import kernel._DByte;

public class CMD_Intel8080_DAD implements ICommand {

    private String arg;

    public CMD_Intel8080_DAD(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int firstValue;
        if (!arg.equals("SP")) {
            firstValue = microprocessor.getValueByRegisterPairName(arg);
        } else {
            firstValue = microprocessor.getValueFromRegister(Intel8080Registers.SP);
        }

        int secondValue = microprocessor.getValueByRegisterPairName("H");

        secondValue += firstValue;
        if (secondValue > 65535 || secondValue < 0) {
            microprocessor.setValueInFlag(Intel8080Flags.C, 1);
        } else {
           microprocessor.setValueInFlag(Intel8080Flags.C, 0);
        }

        secondValue = _DByte.getRoundedValue(secondValue);
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