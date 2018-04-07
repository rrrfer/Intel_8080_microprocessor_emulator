package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;

public class CMD_Intel8080_JPE extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JPE(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        if (microprocessor.getValueFromFlag(Intel8080Flags.P) == 1) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "JPE " + arg;
    }
}