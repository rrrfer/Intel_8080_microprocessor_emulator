package kernel.cmd;

import emulator.IIOSystem;
import kernel.IMicroprocessor;

public class CMD_Intel8080_OUT implements ICommand {

    private String arg;

    public CMD_Intel8080_OUT(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessor microprocessor) {
        IIOSystem ioSystem = microprocessor.getIOSystem();
        if (ioSystem != null) {
            int outputValue = microprocessor.getValueByRegisterName("A");
            int portNumber = Integer.valueOf(arg, 16);
            switch (portNumber) {
                case 2: {
                    ioSystem.consoleOut(outputValue);
                    break;
                }
                case 22: {
                    ioSystem.setTimerValue(outputValue);
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
        return "OUT " + arg;
    }
}