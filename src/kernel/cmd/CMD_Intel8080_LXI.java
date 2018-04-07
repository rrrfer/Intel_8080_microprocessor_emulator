package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080;
import kernel.Intel8080Registers;

public class CMD_Intel8080_LXI implements ICommand {

    private Intel8080Registers register;
    private String secondArg;

    public CMD_Intel8080_LXI(Intel8080Registers register, String secondArg) {
        this.register = register;
        this.secondArg = secondArg.toUpperCase();
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value = Integer.valueOf(secondArg, 16);
        if (register != Intel8080Registers.SP) {
            microprocessor.setValueInRegister(register, value / 256);
        }
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
            case SP: {
                microprocessor.setValueInRegister(Intel8080Registers.SP, value);
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