package model.kernel.cmd;

import model.emulator.IIntraProgramIOEventsListener;
import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Registers;

public class CMD_Intel8080_OUT implements ICommand {

    private String arg;

    public CMD_Intel8080_OUT() {
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
            int outputValue = executeListener.requestOnGetValueFromRegister(Registers.A);
            int portNumber = Integer.valueOf(arg, 16);
            switch (portNumber) {
                case 2: {
                    ioSystem.out_0x02(outputValue);
                    break;
                }
                case 5: {
                    ioSystem.out_0x05(outputValue);
                    break;
                }
                case 7: {
                    ioSystem.out_0x07(outputValue);
                    break;
                }
                case 22: {
                    ioSystem.out_0x16(outputValue);
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