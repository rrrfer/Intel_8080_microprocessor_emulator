package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Registers;

public class CMD_Intel8080_MVI implements ICommand {

    private Intel8080Registers firstRegister;
    private String arg;

    public CMD_Intel8080_MVI(Intel8080Registers firstRegister, String arg) {
        this.firstRegister = firstRegister;
        this.arg = arg;
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value = Integer.valueOf(arg, 16);
        if (firstRegister == Intel8080Registers.M) {
            int address = microprocessor.getValueByRegisterPairName("H");
            microprocessor.getMemory().setValueByIndex(address, value);
        } else {
            microprocessor.setValueInRegister(firstRegister, value);
        }
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getName() {
        return "MVI " + firstRegister + "," + arg;
    }
}