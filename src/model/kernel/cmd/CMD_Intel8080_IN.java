package model.kernel.cmd;

import model.emulator.IIntraProgramIOEventsListener;
import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Registers;

public class CMD_Intel8080_IN implements ICommand {

    private String arg;

    public CMD_Intel8080_IN() {
        this.arg = "0x00";
    }

    @Override
    public void setArgument(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        IIntraProgramIOEventsListener ioSystem = executeListener.requestOnGetInputOutputActionListener();
        if (ioSystem != null) {
            int inputValue = 0;
            int portNumber = Integer.valueOf(arg, 16);
            switch (portNumber) {
                case 5: {
                    inputValue = ioSystem.in_0x05();
                    break;
                }
                case 7: {
                    inputValue = ioSystem.in_0x07();
                    break;
                }
                case 8: {
                    inputValue = ioSystem.in_0x08();
                    break;
                }
                case 22: {
                    inputValue = ioSystem.in_0x16();
                    break;
                }
            }
            executeListener.requestOnSetValueInRegister(Registers.A, inputValue);
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