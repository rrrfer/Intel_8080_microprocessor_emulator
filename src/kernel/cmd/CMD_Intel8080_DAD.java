package kernel.cmd;

import kernel.*;

public class CMD_Intel8080_DAD implements ICommand {

    private Intel8080RegisterPairs registerPair;

    public CMD_Intel8080_DAD(Intel8080RegisterPairs registerPair) {
        this.registerPair = registerPair;
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {

        int firstValue;
        if (registerPair != null) {
            firstValue = microprocessor.getValueFromRegisterPair(registerPair);
        } else {
            firstValue = microprocessor.getValueFromRegister(Intel8080Registers.SP);
        }

        int secondValue = microprocessor.getValueFromRegisterPair(Intel8080RegisterPairs.H);

        secondValue += firstValue;
        if (secondValue > 65535 || secondValue < 0) {
            microprocessor.setValueInFlag(Intel8080Flags.C, 1);
        } else {
           microprocessor.setValueInFlag(Intel8080Flags.C, 0);
        }

        secondValue = _DByte.getRoundedValue(secondValue);
        microprocessor.setValueInRegisterPair(Intel8080RegisterPairs.H, secondValue);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "DAD " + registerPair;
    }
}