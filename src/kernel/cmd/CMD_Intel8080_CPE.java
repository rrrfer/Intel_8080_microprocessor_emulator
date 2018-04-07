package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;

public class CMD_Intel8080_CPE  extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CPE(String arg) {
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
        return "CPE " + arg;
    }
}