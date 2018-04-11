package model.kernel.cmd;

import model.kernel.*;

public class CMD_Intel8080_INR implements ICommand {

    private Registers register;

    public CMD_Intel8080_INR(Registers register) {
        this.register = register;
    }

    @Override
    public void setArgument(String arg) {}

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int value;
        if (register == Registers.M) {
            int address = executeListener.requestOnGetValueFromRegisterPair(RegisterPairs.H);
            value = executeListener.requestOnGetValueFromMemoryByAddress(address);
        } else {
            value = executeListener.requestOnGetValueFromRegister(register);
        }

        value += 1;
        if (value % 256 == 0) {
            executeListener.requestOnSetValueInFlag(Flags.Z, 1);
        } else {
            executeListener.requestOnSetValueInFlag(Flags.Z, 0);
        }

        if (value < 0) {
            executeListener.requestOnSetValueInFlag(Flags.S, 1);
        } else {
            executeListener.requestOnSetValueInFlag(Flags.S, 0);
        }

        int tmpValue = value;
        if (tmpValue < 0) tmpValue += 256;

        int counter = 0;
        for (int i = 0; i < 8; ++i) {
            counter += tmpValue % 2;
            tmpValue = tmpValue >> 1;
        }

        executeListener.requestOnSetValueInFlag(Flags.P, (counter + 1) % 2);
        value = _Byte.getRoundedValue(value);

        if (register == Registers.M) {
            int address = executeListener.requestOnGetValueFromRegisterPair(RegisterPairs.H);
            executeListener.requestOnSetValueInMemoryByAddress(address, value);
        } else {
            executeListener.requestOnSetValueInRegister(register, value);
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