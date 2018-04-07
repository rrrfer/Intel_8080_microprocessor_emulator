package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Registers;
import kernel._DByte;

public class CMD_Intel8080_PUSH implements ICommand {

    public static void push(IMicroprocessorAdapterForCommands microprocessor, int value) {
        int address = microprocessor.getValueFromRegister(Intel8080Registers.SP);
        address = _DByte.getRoundedValue(address - 1);
        microprocessor.getMemory().setValueByIndex(address, value / 256);
        address = _DByte.getRoundedValue(address - 1);
        microprocessor.getMemory().setValueByIndex(address, value % 256);
        microprocessor.setValueInRegister(Intel8080Registers.SP, address);
    }

    private Intel8080Registers register;

    public CMD_Intel8080_PUSH(Intel8080Registers register) {
        this.register = register;
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {

        int value;
        if (register != null) {
            value = microprocessor.getValueFromRegister(register) * 256;
        } else {
            value = microprocessor.getValueFromRegister(Intel8080Registers.A) * 256;
        }

        if (register != null) {
            switch (register) {
                case B: {
                    value += microprocessor.getValueFromRegister(Intel8080Registers.C);
                    break;
                }
                case D: {
                    value += microprocessor.getValueFromRegister(Intel8080Registers.E);
                    break;
                }
                case H: {
                    value += microprocessor.getValueFromRegister(Intel8080Registers.L);
                    break;
                }
            }
        } else {
            value += microprocessor.getAllFlags();
        }
        push(microprocessor, value);
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