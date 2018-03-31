package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_CPE  extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CPE(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        if (microprocessor.getValueByFlagName("P") == 1) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "CPE " + arg;
    }
}