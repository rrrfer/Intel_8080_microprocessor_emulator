package kernel.cmd;

import kernel.IMicroprocessor;
import kernel.IMicroprocessorCommandsAdapter;

public class CMD_Intel8080_CM extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CM(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessorCommandsAdapter microprocessor) {
        if (microprocessor.getValueByFlagName("S") == 1) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "CP " + arg;
    }
}