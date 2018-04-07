package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;

public class CMD_Intel8080_LDAX implements ICommand {

    private String arg;

    public CMD_Intel8080_LDAX(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int address = microprocessor.getValueByRegisterPairName(arg);
        int value = microprocessor.getMemory().getValueByIndex(address);
        microprocessor.setValueByRegisterName("A", value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "LDAX " + arg;
    }
}