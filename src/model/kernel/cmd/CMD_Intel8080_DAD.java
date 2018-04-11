package model.kernel.cmd;

import model.kernel.*;

public class CMD_Intel8080_DAD implements ICommand {

    private RegisterPairs registerPair;

    public CMD_Intel8080_DAD(RegisterPairs registerPair) {
        this.registerPair = registerPair;
    }

    @Override
    public void setArgument(String arg) {}

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {

        int firstValue;
        if (registerPair != null) {
            firstValue = executeListener.requestOnGetValueFromRegisterPair(registerPair);
        } else {
            firstValue = executeListener.requestOnGetValueFromRegister(Registers.SP);
        }

        int secondValue = executeListener.requestOnGetValueFromRegisterPair(RegisterPairs.H);

        secondValue += firstValue;
        if (secondValue > 65535 || secondValue < 0) {
            executeListener.requestOnSetValueInFlag(Flags.C, 1);
        } else {
           executeListener.requestOnSetValueInFlag(Flags.C, 0);
        }

        secondValue = _DByte.getRoundedValue(secondValue);
        executeListener.requestOnSetValueInRegisterPair(RegisterPairs.H, secondValue);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        if (registerPair != null) {
            return "DAD " + registerPair;
        } else {
            return "DAD SP";
        }
    }
}