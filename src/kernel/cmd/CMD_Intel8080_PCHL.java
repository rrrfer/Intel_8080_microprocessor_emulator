package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.RegisterPairs;
import kernel.Registers;

public class CMD_Intel8080_PCHL implements ICommand {
    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int address = executeListener.requestOnGetValueFromRegisterPair(RegisterPairs.H);
        executeListener.requestOnSetValueInRegister(Registers.PC, address);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "PCHL";
    }
}