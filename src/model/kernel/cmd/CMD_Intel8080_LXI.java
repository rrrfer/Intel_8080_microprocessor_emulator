package model.kernel.cmd;

import model.kernel.ICommandsExecuteListener;
import model.kernel.Registers;

public class CMD_Intel8080_LXI implements ICommand {

    private Registers register;
    private String secondArg;

    public CMD_Intel8080_LXI(Registers register, String secondArg) {
        this.register = register;
        this.secondArg = secondArg.toUpperCase();
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int value = Integer.valueOf(secondArg, 16);
        if (register != Registers.SP) {
            executeListener.requestOnSetValueInRegister(register, value / 256);
        }
        switch (register) {
            case B: {
                executeListener.requestOnSetValueInRegister(Registers.C, value % 256);
                break;
            }
            case D: {
                executeListener.requestOnSetValueInRegister(Registers.E, value % 256);
                break;
            }
            case H: {
                executeListener.requestOnSetValueInRegister(Registers.L, value % 256);
                break;
            }
            case SP: {
                executeListener.requestOnSetValueInRegister(Registers.SP, value);
            }
        }
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "LXI " + register + "," + secondArg;
    }
}