package model.kernel.cmd;

import model.kernel.ICommandExecuteEventsListener;
import model.kernel.RegisterPairs;
import model.kernel.Registers;

public class CMD_Intel8080_STAX implements ICommand {

    private RegisterPairs registerPair;

    public CMD_Intel8080_STAX(RegisterPairs registerPair) {
        this.registerPair = registerPair;
    }

    @Override
    public void execute(ICommandExecuteEventsListener executeListener) {
        int value = executeListener.requestOnGetValueFromRegister(Registers.A);
        int address = executeListener.requestOnGetValueFromRegisterPair(registerPair);
        executeListener.requestOnSetValueInMemoryByAddress(address, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "STAX " + registerPair;
    }
}