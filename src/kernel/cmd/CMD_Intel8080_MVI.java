package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;

public class CMD_Intel8080_MVI implements ICommand {

    private String firstArg;
    private String secondArg;

    public CMD_Intel8080_MVI(String firstArg, String secondArg) {
        this.firstArg = firstArg.toUpperCase();
        this.secondArg = secondArg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value = Integer.valueOf(secondArg, 16);
        if (firstArg.equals("M")) {
            int address = microprocessor.getValueByRegisterPairName("H");
            microprocessor.getMemory().setValueByIndex(address, value);
        } else {
            microprocessor.setValueByRegisterName(firstArg, value);
        }
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getName() {
        return "MVI " + firstArg + "," + secondArg;
    }
}