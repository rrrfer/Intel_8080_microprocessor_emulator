package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_CALL implements ICommand {

    protected String arg;

    public CMD_Intel8080_CALL(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int callAddress = Integer.valueOf(arg, 16);
        int pushAddress = microprocessor.getValueByRegisterName("PC");
        microprocessor.push(pushAddress);
        microprocessor.setValueByRegisterName("PC", callAddress);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "CALL " + arg;
    }
}