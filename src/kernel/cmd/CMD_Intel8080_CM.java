package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;

public class CMD_Intel8080_CM extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CM(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        if (microprocessor.getValueByFlagName(Intel8080Flags.S) == 1) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "CP " + arg;
    }
}