package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_PUSH implements ICommand {

    private String arg;

    public CMD_Intel8080_PUSH(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {

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
        microprocessor.push(value);
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