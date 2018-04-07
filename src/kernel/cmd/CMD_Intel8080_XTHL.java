package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;

public class CMD_Intel8080_XTHL implements ICommand {

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int firstValue = microprocessor.getValueByRegisterPairName("H");
        int secondValue = CMD_Intel8080_POP.pop(microprocessor);
        microprocessor.setValueByRegisterPairName("H", secondValue);
        CMD_Intel8080_PUSH.push(microprocessor, firstValue);
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