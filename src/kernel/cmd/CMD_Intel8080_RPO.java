package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_RPO extends CMD_Intel8080_RET {
    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        if (microprocessor.getValueByFlagName("P") == 0) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "RPO";
    }
}