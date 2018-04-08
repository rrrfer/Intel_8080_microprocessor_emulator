package model.kernel.cmd;

import model.kernel.ICommandsExecuteListener;
import model.kernel.Registers;
import model.kernel._DByte;

public class CMD_Intel8080_PUSH implements ICommand {

    public static void push(ICommandsExecuteListener executeListener, int value) {
        int address = executeListener.requestOnGetValueFromRegister(Registers.SP);
        address = _DByte.getRoundedValue(address - 1);
        executeListener.requestOnSetValueInMemoryByAddress(address, value / 256);
        address = _DByte.getRoundedValue(address - 1);
        executeListener.requestOnSetValueInMemoryByAddress(address, value % 256);
        executeListener.requestOnSetValueInRegister(Registers.SP, address);
    }

    private Registers register;

    public CMD_Intel8080_PUSH(Registers register) {
        this.register = register;
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {

        int value;
        if (register != null) {
            value = executeListener.requestOnGetValueFromRegister(register) * 256;
        } else {
            value = executeListener.requestOnGetValueFromRegister(Registers.A) * 256;
        }

        if (register != null) {
            switch (register) {
                case B: {
                    value += executeListener.requestOnGetValueFromRegister(Registers.C);
                    break;
                }
                case D: {
                    value += executeListener.requestOnGetValueFromRegister(Registers.E);
                    break;
                }
                case H: {
                    value += executeListener.requestOnGetValueFromRegister(Registers.L);
                    break;
                }
            }
        } else {
            value += executeListener.requestOnGerValueFromFlagsRegister();
        }
        push(executeListener, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "PUSH " + register;
    }
}