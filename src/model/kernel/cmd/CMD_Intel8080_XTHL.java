package model.kernel.cmd;

import model.kernel.IExecutableCommandEventsListener;
import model.kernel.RegisterPairs;

public class CMD_Intel8080_XTHL implements ICommand {

    @Override
    public void execute(IExecutableCommandEventsListener executeListener) {
        int firstValue = executeListener.requestOnGetValueFromRegisterPair(RegisterPairs.H);
        int secondValue = CMD_Intel8080_POP.pop(executeListener);
        executeListener.requestOnSetValueInRegisterPair(RegisterPairs.H, secondValue);
        CMD_Intel8080_PUSH.push(executeListener, firstValue);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "XTHL";
    }
}