package kernel.cmd;

import kernel.ICommandsExecuteListener;
import kernel.RegisterPairs;
import kernel.Registers;
import kernel._DByte;

public class CMD_Intel8080_INX implements ICommand {

    private RegisterPairs registerPair;

    public CMD_Intel8080_INX(RegisterPairs registerPair) {
        this.registerPair = registerPair;
    }

    @Override
    public void execute(ICommandsExecuteListener executeListener) {
        int value;
        if (registerPair != null) {
            value = executeListener.requestOnGetValueFromRegisterPair(registerPair);
        } else {
            value = executeListener.requestOnGetValueFromRegister(Registers.SP);
        }

        value += 1;
        value = _DByte.getRoundedValue(value);

        // TODO Проверка на перенос

        if (!registerPair.equals("SP")) {
            executeListener.requestOnSetValueInRegisterPair(registerPair, value);
        } else {
            executeListener.requestOnSetValueInRegister(Registers.SP, value);
        }
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "INX " + registerPair;
    }
}