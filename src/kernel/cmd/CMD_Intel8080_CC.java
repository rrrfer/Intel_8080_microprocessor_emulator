package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_CC extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CC(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        if (microprocessor.getValueByFlagName("C") == 1) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "CC";
    }
}