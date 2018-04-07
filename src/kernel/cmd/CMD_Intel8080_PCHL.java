package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_PCHL implements ICommand {
    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        int address = microprocessor.getValueByRegisterPairName("H");
        microprocessor.setValueByRegisterName("PC", address);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "PCHL";
    }
}