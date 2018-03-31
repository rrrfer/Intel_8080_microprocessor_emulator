package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_RZ extends CMD_Intel8080_RET {
    @Override
    public void execute(IMicroprocessor microprocessor) {
        if (microprocessor.getValueByFlagName("Z") == 1) {
            super.execute(microprocessor);
        }
    }

    @Override
    public String getName() {
        return "RZ";
    }
}