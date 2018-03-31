package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_RET implements ICommand {
    @Override
    public void execute(IMicroprocessor microprocessor) {
        int address = microprocessor.pop();
        microprocessor.setValueByRegisterName("PC", address);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "RET";
    }
}