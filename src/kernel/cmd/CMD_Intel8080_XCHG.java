package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080RegisterPairs;

public class CMD_Intel8080_XCHG implements ICommand {

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        // TODO
        int fValue = microprocessor.getValueFromRegisterPair(Intel8080RegisterPairs.H);
        int sValue = microprocessor.getValueFromRegisterPair(Intel8080RegisterPairs.D);
        microprocessor.setValueInRegisterPair(Intel8080RegisterPairs.H, sValue);
        microprocessor.setValueInRegisterPair(Intel8080RegisterPairs.D, fValue);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "XCHG";
    }
}