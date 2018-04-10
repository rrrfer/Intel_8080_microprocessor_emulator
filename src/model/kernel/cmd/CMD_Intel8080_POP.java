package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.Registers;
import model.kernel._DByte;

public class CMD_Intel8080_POP implements ICommand {

    public static int pop(IExecutableCommandEventsListener executeListener) {
        int address = executeListener.requestOnGetValueFromRegister(Registers.SP);
        int value = executeListener.requestOnGetValueFromMemoryByAddress(address);
        address = _DByte.getRoundedValue(address + 1);
        value += executeListener.requestOnGetValueFromMemoryByAddress(address) * 256;
        address = _DByte.getRoundedValue(address + 1);
        executeListener.requestOnSetValueInRegister(Registers.SP, address);
        return value;
    }

    private Registers register;

    public CMD_Intel8080_POP(Registers register) {
        this.register = register;
    }

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int value = pop(executeListener);

        if (register != null) {
            executeListener.requestOnSetValueInRegister(register, value / 256);
        } else {
            executeListener.requestOnSetValueInRegister(Registers.A, value / 256);
        }

        if (register != null) {
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
            }
        } else {
            executeListener.requestOnSetValueInFlagsRegister(value % 256);
        }
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        if (register != null) {
            return "POP " + register;
        } else {
            return "POP PSW";
        }
    }
}