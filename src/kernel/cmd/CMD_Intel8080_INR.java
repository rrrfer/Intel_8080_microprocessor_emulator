package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080Flags;
import kernel.Intel8080Registers;
import kernel._Byte;

public class CMD_Intel8080_INR implements ICommand {

    private Intel8080Registers register;

    public CMD_Intel8080_INR(Intel8080Registers register) {
        this.register = register;
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value;
        if (register == Intel8080Registers.M) {
            int address = microprocessor.getValueByRegisterPairName("H");
            value = microprocessor.getMemory().getValueByIndex(address);
        } else {
            value = microprocessor.getValueFromRegister(register);
        }

        value += 1;
        if (value % 256 == 0) {
            microprocessor.setValueInFlag(Intel8080Flags.Z, 1);
        } else {
            microprocessor.setValueInFlag(Intel8080Flags.Z, 0);
        }

        if (value < 0) {
            microprocessor.setValueInFlag(Intel8080Flags.S, 1);
        } else {
            microprocessor.setValueInFlag(Intel8080Flags.S, 0);
        }

        int tmpValue = value;
        if (tmpValue < 0) tmpValue += 256;

        int counter = 0;
        for (int i = 0; i < 8; ++i) {
            counter += tmpValue % 2;
            tmpValue = tmpValue >> 1;
        }

        microprocessor.setValueInFlag(Intel8080Flags.P, (counter + 1) % 2);
        value = _Byte.getRoundedValue(value);

        if (register == Intel8080Registers.M) {
            int address = microprocessor.getValueByRegisterPairName("H");
            microprocessor.getMemory().setValueByIndex(address, value);
        } else {
            microprocessor.setValueInRegister(register, value);
        }
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "INR " + register;
    }
}