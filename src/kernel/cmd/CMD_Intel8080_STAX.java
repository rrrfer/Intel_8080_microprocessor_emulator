package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_STAX implements ICommand {

    private String arg;

    public CMD_Intel8080_STAX(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        int value = microprocessor.getValueByRegisterName("A");
        int address = microprocessor.getValueByRegisterPairName(arg);
        microprocessor.getMemory().setValueByIndex(address, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "STAX " + arg;
    }
}