package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel._DByte;

public class CMD_Intel8080_PUSH implements ICommand {

    public static void push(IMicroprocessorAdapterForCommands microprocessor, int value) {
        int address = microprocessor.getValueByRegisterName("SP");
        address = _DByte.getRoundedValue(address - 1);
        microprocessor.getMemory().setValueByIndex(address, value / 256);
        address = _DByte.getRoundedValue(address - 1);
        microprocessor.getMemory().setValueByIndex(address, value % 256);
        microprocessor.setValueByRegisterName("SP", address);
    }

    private String arg;

    public CMD_Intel8080_PUSH(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {

        int value;
        if (!arg.equals("PSW")) {
            value = microprocessor.getValueByRegisterName(arg) * 256;
        } else {
            value = microprocessor.getValueByRegisterName("A") * 256;
        }

        switch (arg) {
            case "B": {
                value += microprocessor.getValueByRegisterName("C");
                break;
            }
            case "D": {
                value += microprocessor.getValueByRegisterName("E");
                break;
            }
            case "H": {
                value += microprocessor.getValueByRegisterName("L");
                break;
            }
            case "PSW": {
                value += microprocessor.getAllFlags();
                break;
            }
        }
        push(microprocessor, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "PUSH " + arg;
    }
}