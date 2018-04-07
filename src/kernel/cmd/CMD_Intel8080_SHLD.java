package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_SHLD implements ICommand {

    private String arg;

    public CMD_Intel8080_SHLD(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        int address = Integer.valueOf(arg, 16);
        int value = microprocessor.getValueByRegisterName("L");
        microprocessor.getMemory().setValueByIndex(address, value);
        value = microprocessor.getValueByRegisterName("H");
        microprocessor.getMemory().setValueByIndex(address + 1, value);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "SHLD " + arg;
    }
}