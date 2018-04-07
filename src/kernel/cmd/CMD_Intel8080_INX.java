package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;
import kernel._DByte;

public class CMD_Intel8080_INX implements ICommand {

    private String arg;

    public CMD_Intel8080_INX(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        int value;
        if (!arg.equals("SP")) {
            value = microprocessor.getValueByRegisterPairName(arg);
        } else {
            value = microprocessor.getValueByRegisterName("SP");
        }

        value += 1;
        value = _DByte.getRoundedValue(value);

        // TODO Проверка на перенос

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