package kernel.cmd;

import emulator.IIOSystem;
import kernel.IMicroprocessor;

public class CMD_Intel8080_IN implements ICommand {

    private String arg;

    public CMD_Intel8080_IN(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        IIOSystem ioSystem = microprocessor.getIOSystem();
        int portNumber = Integer.valueOf(arg);
        switch (portNumber) {
            case 8: {
                int value = 0;
                if (ioSystem != null) {
                    value = ioSystem.requestOfInput();
                }
                microprocessor.setValueByRegisterName("A", value);
                break;
            }
        }
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getName() {
        return "IN " + arg;
    }
}