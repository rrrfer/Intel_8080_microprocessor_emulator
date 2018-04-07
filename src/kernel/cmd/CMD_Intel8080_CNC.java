package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_CNC  extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CNC(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        if (microprocessor.getValueByFlagName("C") == 0) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "CNC " + arg;
    }
}