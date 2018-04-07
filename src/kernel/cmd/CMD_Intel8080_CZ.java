package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;

public class CMD_Intel8080_CZ  extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CZ(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        if (microprocessor.getValueByFlagName(Intel8080Flags.Z) == 1) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "CZ " + arg;
    }
}