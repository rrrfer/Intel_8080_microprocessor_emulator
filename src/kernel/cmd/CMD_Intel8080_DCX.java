package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080RegisterPairs;
import kernel.Intel8080Registers;
import kernel._DByte;

public class CMD_Intel8080_DCX implements ICommand {

    private Intel8080RegisterPairs registerPair;

    public CMD_Intel8080_DCX(Intel8080RegisterPairs registerPair) {
        this.registerPair = registerPair;
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value;
        if (registerPair != null) {
            value = microprocessor.getValueFromRegisterPair(registerPair);
        } else {
            value = microprocessor.getValueFromRegister(Intel8080Registers.SP);
        }

        value -= 1;
        value = _DByte.getRoundedValue(value);

        // TODO Проверка на перенос

        if (!registerPair.equals("SP")) {
            microprocessor.setValueInRegisterPair(registerPair, value);
        } else {
            microprocessor.setValueInRegister(Intel8080Registers.SP, value);
        }
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "DCX " + registerPair;
    }
}