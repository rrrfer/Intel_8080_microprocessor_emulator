package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_POP implements ICommand {

    private String arg;

    public CMD_Intel8080_POP(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int SP = microprocessor.getValueByRegisterName("SP");
        SP = (SP + 1) % microprocessor.getMemory().getSize();
        int value = microprocessor.getMemory().getValueByIndex(SP);
        switch (arg) {
            case "B": {
                microprocessor.setValueByRegisterName("C", value);
                break;
            }
            case "D": {
                microprocessor.setValueByRegisterName("E", value);
                break;
            }
            case "H": {
                microprocessor.setValueByRegisterName("L", value);
                break;
            }
        }
        SP = (SP + 1) % microprocessor.getMemory().getSize();
        value = microprocessor.getMemory().getValueByIndex(SP);
        microprocessor.setValueByRegisterName(arg, value);
        microprocessor.setValueByRegisterName("SP", SP);
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