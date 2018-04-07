package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;

public class CMD_Intel8080_RC extends CMD_Intel8080_RET {
    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        if (microprocessor.getValueFromFlag(Intel8080Flags.C) == 1) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "RC";
    }
}