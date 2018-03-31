package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_CPO  extends CMD_Intel8080_CALL {

    public CMD_Intel8080_CPO(String arg) {
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
        return "CPO " + arg;
    }
}