package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_CZ  extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CZ(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        if (microprocessor.getValueByFlagName("Z") == 1) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "CZ " + arg;
    }
}