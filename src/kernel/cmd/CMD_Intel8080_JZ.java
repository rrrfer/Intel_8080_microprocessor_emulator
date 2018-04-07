package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_JZ extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JZ(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        if (microprocessor.getValueByFlagName("Z") == 1) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "JZ " + arg;
    }
}