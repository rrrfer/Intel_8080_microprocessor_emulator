package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_JNC extends CMD_Intel8080_JMP {

    public CMD_Intel8080_JNC(String arg) {
        super(arg);
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        if (microprocessor.getValueByFlagName("C") == 0) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "JNZ " + arg;
    }
}