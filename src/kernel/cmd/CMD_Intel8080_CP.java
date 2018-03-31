package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_CP  extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CP(String arg) {
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
        return "CP " + arg;
    }
}