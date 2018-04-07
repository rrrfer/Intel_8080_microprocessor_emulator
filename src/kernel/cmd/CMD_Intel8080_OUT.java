package kernel.cmd;

import emulator.IInputOutputSystem;
import kernel.IMicroprocessorAdapterForCommands;

public class CMD_Intel8080_OUT implements ICommand {

    private String arg;

    public CMD_Intel8080_OUT(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        IInputOutputSystem ioSystem = microprocessor.getIOSystem();
        if (ioSystem != null) {
            int outputValue = microprocessor.getValueByRegisterName("A");
            int portNumber = Integer.valueOf(arg, 16);
            switch (portNumber) {
                case 2: {
                    ioSystem.stdOutput(outputValue);
                    break;
                }
                case 5: {
                    ioSystem.writeValueInInputRegisterOfPixelScreen(outputValue);
                    break;
                }
                case 7: {
                    ioSystem.writeValueInInputRegisterOfCharacterScreen(outputValue);
                    break;
                }
                case 22: {
                    ioSystem.writeTimerValue(outputValue);
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