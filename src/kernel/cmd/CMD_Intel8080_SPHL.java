package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_SPHL implements ICommand {
    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        int address = microprocessor.getValueByRegisterPairName("H");
        microprocessor.setValueByRegisterName("SP", address);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "SPHL";
    }
}