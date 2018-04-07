package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_RNZ extends CMD_Intel8080_RET {
    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        if (microprocessor.getValueByFlagName("Z") == 0) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "RNZ";
    }
}