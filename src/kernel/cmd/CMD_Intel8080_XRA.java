package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Registers;

public class CMD_Intel8080_XRA implements ICommand {

    private Intel8080Registers register;

    public CMD_Intel8080_XRA(Intel8080Registers register) {
        this.register = register;
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int firstValue = microprocessor.getValueFromRegister(Intel8080Registers.A);
        int secondValue;
        if (register == Intel8080Registers.M) {
            int address = microprocessor.getValueByRegisterPairName("H");
            secondValue = microprocessor.getMemory().getValueByIndex(address);
        } else {
            secondValue = microprocessor.getValueFromRegister(register);
        }

        firstValue = firstValue ^ secondValue;
        microprocessor.checkByteForSetFlags(firstValue);

        microprocessor.setValueInRegister(Intel8080Registers.A, firstValue);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "XRA " + register;
    }
}