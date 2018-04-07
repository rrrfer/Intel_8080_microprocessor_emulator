package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_JNZ extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JNZ(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        if (microprocessor.getValueByFlagName("Z") == 0) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "JNZ " + arg;
    }
}