package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_PUSH implements ICommand {

    private String arg;

    public CMD_Intel8080_PUSH(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int SP = microprocessor.getValueByRegisterName("SP");
        int value = microprocessor.getValueByRegisterName(arg);
        microprocessor.getMemory().setValueByIndex(SP, value);
        SP = (SP + microprocessor.getMemory().getSize() - 1)
                % microprocessor.getMemory().getSize();

        switch (arg) {
            case "B": {
                value = microprocessor.getValueByRegisterName("C");
                break;
            }
            case "D": {
                value = microprocessor.getValueByRegisterName("E");
                break;
            }
            case "H": {
                value = microprocessor.getValueByRegisterName("L");
                break;
            }
        }

        microprocessor.getMemory().setValueByIndex(SP, value);
        SP = (SP + microprocessor.getMemory().getSize() - 1)
                % microprocessor.getMemory().getSize();
        microprocessor.setValueByRegisterName("SP", SP);
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