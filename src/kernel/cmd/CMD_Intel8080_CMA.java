package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;

public class CMD_Intel8080_CMA implements ICommand {
    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value = microprocessor.getValueByRegisterName("A");
        value = 255 - value;
        microprocessor.setValueByRegisterName("A", value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "CMA";
    }
}