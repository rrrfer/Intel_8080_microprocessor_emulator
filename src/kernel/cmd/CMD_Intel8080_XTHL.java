package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_XTHL implements ICommand {

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int firstValue = microprocessor.getValueByRegisterPairName("H");
        int secondValue = microprocessor.pop();
        microprocessor.setValueByRegisterPairName("H", secondValue);
        microprocessor.push(firstValue);
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