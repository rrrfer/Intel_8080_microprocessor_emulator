package kernel.cmd;

import kernel.IMicroprocessorAdapterForCommands;
import kernel.Intel8080RegisterPairs;
import kernel.Intel8080Registers;

public class CMD_Intel8080_MOV implements ICommand {

    private Intel8080Registers firstRegister;
    private Intel8080Registers secondRegister;

    public CMD_Intel8080_MOV(Intel8080Registers firstRegister, Intel8080Registers secondRegister) {
        this.firstRegister = firstRegister;
        this.secondRegister = secondRegister;
    }

    @Override
    public void execute(IMicroprocessorAdapterForCommands microprocessor) {
        int value;

        if (secondRegister == Intel8080Registers.M) {
            int address = microprocessor.getValueFromRegisterPair(Intel8080RegisterPairs.H);
            value = microprocessor.getMemory().getValueByIndex(address);
        } else {
            value = microprocessor.getValueFromRegister(secondRegister);
        }

        if (firstRegister == Intel8080Registers.M) {
           int address = microprocessor.getValueFromRegisterPair(Intel8080RegisterPairs.H);
           microprocessor.getMemory().setValueByIndex(address, value);
        } else {
            microprocessor.setValueInRegister(firstRegister, value);
        }
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "MOV " + firstRegister + "," + secondRegister;
    }
}
