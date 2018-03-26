package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_MOV implements ICommand {

    private String firstArg;
    private String secondArg;

    public CMD_Intel8080_MOV(String firstArg, String secondArg) {
        this.firstArg = firstArg.toUpperCase();
        this.secondArg = secondArg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int value = microprocessor.getValueByRegisterName(secondArg);
        microprocessor.setValueByRegisterName(firstArg, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "MOV " + firstArg + "," + secondArg;
    }
}
