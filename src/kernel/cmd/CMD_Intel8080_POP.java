package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_POP implements ICommand {

    private String arg;

    public CMD_Intel8080_POP(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int value = microprocessor.pop();

        if (!arg.equals("PSW")) {
            microprocessor.setValueByRegisterName(arg, value / 256);
        } else {
            microprocessor.setValueByRegisterName("A", value / 256);
        }

        switch (arg) {
            case "B": {
                microprocessor.setValueByRegisterName("C", value % 256);
                break;
            }
            case "D": {
                microprocessor.setValueByRegisterName("E", value % 256);
                break;
            }
            case "H": {
                microprocessor.setValueByRegisterName("L", value % 256);
                break;
            }
            case "PSW": {
                microprocessor.setAllFlags(value % 256);
                break;
            }
        }
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "POP " + arg;
    }
}