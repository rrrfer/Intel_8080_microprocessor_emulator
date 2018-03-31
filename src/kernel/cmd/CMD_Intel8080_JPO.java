package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_JPO extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JPO(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        if (microprocessor.getValueByFlagName("P") == 0) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "JPO " + arg;
    }
}