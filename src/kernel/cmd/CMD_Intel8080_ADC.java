package kernel.cmd;

import kernel.*;

public class CMD_Intel8080_ADC implements ICommand {

    private Intel8080Registers register;

    public CMD_Intel8080_ADC(Intel8080Registers register) {
        this.register = register;
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int firstValue = microprocessor.getValueFromRegister(Intel8080Registers.A);
        int secondValue;
        if (register == Intel8080Registers.M) {
            int address = microprocessor.getValueFromRegisterPair(Intel8080RegisterPairs.H);
            secondValue = microprocessor.getMemory().getValueByIndex(address);
        } else {
            secondValue = microprocessor.getValueFromRegister(register);
        }
        firstValue = firstValue + secondValue + microprocessor.getValueFromFlag(Intel8080Flags.C);
        microprocessor.checkByteForSetFlags(firstValue);
        firstValue = _Byte.getRoundedValue(firstValue);
        microprocessor.setValueInRegister(Intel8080Registers.A, firstValue);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "ADC " + register;
    }
}