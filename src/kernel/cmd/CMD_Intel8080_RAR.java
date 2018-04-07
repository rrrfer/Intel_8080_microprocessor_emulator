package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_RAR implements ICommand {
    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        int value = microprocessor.getValueByRegisterName("A");
        if (microprocessor.getValueByFlagName("C") == 1) {
            value += 256;
        }
        if (value % 2 == 1) {
            microprocessor.setValueByFlagName("C", 1);
        } else {
            microprocessor.setValueByFlagName("C", 0);
        }
        value = value >> 1;
        microprocessor.setValueByRegisterName("A", value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "RAR";
    }
}