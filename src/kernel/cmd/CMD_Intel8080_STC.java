package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_STC implements ICommand {
    @Override
    public void execute(IMicroprocessor microprocessor) {
        microprocessor.setValueByFlagName("C", 1);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "STC";
    }
}