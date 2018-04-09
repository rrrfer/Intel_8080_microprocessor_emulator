package model.kernel.cmd;

import model.kernel.ICommandExecuteEventsListener;
import model.kernel.RegisterPairs;
import model.kernel.Registers;

public class CMD_Intel8080_SPHL implements ICommand {
    @Override
    public void execute(ICommandExecuteEventsListener executeListener) {
        int address = executeListener.requestOnGetValueFromRegisterPair(RegisterPairs.H);
        executeListener.requestOnSetValueInRegister(Registers.SP, address);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "SPHL";
    }
}