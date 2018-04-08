package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.RegisterPairs;
import kernel.Registers;

public class CMD_Intel8080_STAX implements ICommand {

    private RegisterPairs registerPair;

    public CMD_Intel8080_STAX(RegisterPairs registerPair) {
        this.registerPair = registerPair;
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
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