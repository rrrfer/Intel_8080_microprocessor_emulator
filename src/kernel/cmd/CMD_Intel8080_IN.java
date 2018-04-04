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
        if (ioSystem != null) {
            int inputValue;
            int portNumber = Integer.valueOf(arg, 16);
            switch (portNumber) {
                case 8: {
                    inputValue = ioSystem.requestOfInput();
                    microprocessor.setValueByRegisterName("A", inputValue);
                    break;
                }
                case 22: {
                    inputValue = ioSystem.getTimerValue();
                    microprocessor.setValueByRegisterName("A", inputValue);
                    break;
                }
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