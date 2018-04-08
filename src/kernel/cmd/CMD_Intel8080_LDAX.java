package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.RegisterPairs;
import kernel.Registers;

public class CMD_Intel8080_LDAX implements ICommand {

    private RegisterPairs registerPair;

    public CMD_Intel8080_LDAX(RegisterPairs registerPair) {
        this.registerPair = registerPair;
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int address = executeListener.requestOnGetValueFromRegisterPair(registerPair);
        int value = executeListener.requestOnGetValueFromMemoryByAddress(address);
        executeListener.requestOnSetValueInRegister(Registers.A, value);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "LDAX " + registerPair;
    }
}