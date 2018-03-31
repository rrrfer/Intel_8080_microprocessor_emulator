package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_JP extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JP(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        if (microprocessor.getValueByFlagName("S") == 0) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "JP " + arg;
    }
}