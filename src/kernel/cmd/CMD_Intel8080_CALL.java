package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.Registers;

public class CMD_Intel8080_CALL implements ICommand {

    protected String arg;

    public CMD_Intel8080_CALL(String arg) {
        this.arg = arg.toUpperCase();
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int callAddress = Integer.valueOf(arg, 16);
        int pushAddress = executeListener.requestOnGetValueFromRegister(Registers.PC);
        CMD_Intel8080_PUSH.push(executeListener, pushAddress);
        executeListener.requestOnSetValueInRegister(Registers.PC, callAddress);
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String getName() {
        return "CALL " + arg;
    }
}