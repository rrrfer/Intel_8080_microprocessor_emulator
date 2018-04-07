package kernel.cmd;

import emulator.IInputOutputSystem;
import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Registers;

public class CMD_Intel8080_IN implements ICommand {

    private String arg;

    public CMD_Intel8080_IN(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        IInputOutputSystem ioSystem = microprocessor.getIOSystem();
        if (ioSystem != null) {
            int inputValue = 0;
            int portNumber = Integer.valueOf(arg, 16);
            switch (portNumber) {
                case 5: {
                    inputValue = ioSystem.readValueFromOutputRegisterOfPixelScreen();
                    break;
                }
                case 7: {
                    inputValue = ioSystem.readValueFromOutputRegisterOfCharacterScreen();
                    break;
                }
                case 8: {
                    inputValue = ioSystem.requestOfStdInput();
                    break;
                }
                case 22: {
                    inputValue = ioSystem.readTimerValue();
                    break;
                }
            }
            microprocessor.setValueInRegister(Intel8080Registers.A, inputValue);
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