package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_LHLD implements ICommand {

    private String arg;

    public CMD_Intel8080_LHLD(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int address = Integer.valueOf(arg, 16);
        int value = microprocessor.getMemory().getValueByIndex(address);
        microprocessor.setValueByRegisterName("L", value);
        value = microprocessor.getMemory().getValueByIndex(address + 1);
        microprocessor.setValueByRegisterName("H", value);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "LHLD " + arg;
    }
}