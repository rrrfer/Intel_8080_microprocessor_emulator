package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;

public class CMD_Intel8080_SPHL implements ICommand {
    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int address = microprocessor.getValueByRegisterPairName("H");
        microprocessor.setValueByRegisterName("SP", address);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "SPHL";
    }
}