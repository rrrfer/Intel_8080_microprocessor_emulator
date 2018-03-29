package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_CMC implements ICommand {
    @Override
    public void execute(IMicroprocessor microprocessor) {
        int value = microprocessor.getValueByFlagName("C");
        value = (value + 1) % 2;
        microprocessor.setValueByFlagName("C", value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "CMC";
    }
}