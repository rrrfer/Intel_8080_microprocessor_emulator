package kernel.cmd;

import kernel.IMicroprocessor;

public class CMD_Intel8080_XCHG implements ICommand {

    @Override
    public void execute(IMicroprocessor microprocessor) {
        int fValue = microprocessor.getValueByRegisterPairName("H");
        int sValue = microprocessor.getValueByRegisterPairName("D");
        microprocessor.setValueByRegisterPairName("H", sValue);
        microprocessor.setValueByRegisterPairName("D", fValue);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "XCHG";
    }
}