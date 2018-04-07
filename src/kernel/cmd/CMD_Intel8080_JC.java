package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;

public class CMD_Intel8080_JC extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JC(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        if (microprocessor.getValueByFlagName(Intel8080Flags.C) == 1) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "JC " + arg;
    }
}