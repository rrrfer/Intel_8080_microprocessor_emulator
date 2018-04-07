package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Registers;
import kernel._DByte;

public class CMD_Intel8080_POP implements ICommand {

    public static int pop(IMicroprocessorAdapterForCommands microprocessor) {
        int address = microprocessor.getValueFromRegister(Intel8080Registers.SP);
        int value = microprocessor.getMemory().getValueByIndex(address);
        address = _DByte.getRoundedValue(address + 1);
        value += microprocessor.getMemory().getValueByIndex(address) * 256;
        address = _DByte.getRoundedValue(address + 1);
        microprocessor.setValueInRegister(Intel8080Registers.SP, address);
        return value;
    }

    private Intel8080Registers register;

    public CMD_Intel8080_POP(Intel8080Registers register) {
        this.register = register;
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value = pop(microprocessor);

        if (register != null) {
            microprocessor.setValueInRegister(register, value / 256);
        } else {
            microprocessor.setValueInRegister(Intel8080Registers.A, value / 256);
        }

        if (register != null) {
            switch (register) {
                case B: {
                    microprocessor.setValueInRegister(Intel8080Registers.C, value % 256);
                    break;
                }
                case D: {
                    microprocessor.setValueInRegister(Intel8080Registers.E, value % 256);
                    break;
                }
                case H: {
                    microprocessor.setValueInRegister(Intel8080Registers.L, value % 256);
                    break;
                }
            }
        } else {
            microprocessor.setAllFlags(value % 256);
        }
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "POP " + register;
    }
}