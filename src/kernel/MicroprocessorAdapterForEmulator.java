package kernel;

import emulator.IInputOutputSystem;
import kernel.cmd.ICommand;

public class MicroprocessorAdapterForEmulator implements IMicroprocessorAdapterForEmulator {

    private IMicroprocessor microprocessor;

    public MicroprocessorAdapterForEmulator(IMicroprocessor microprocessor) {
        this.microprocessor = microprocessor;
    }

    @Override
    public void setIOSystem(IInputOutputSystem ioSystem) {
        microprocessor.setIOSystem(ioSystem);
    }

    @Override
    public IMemory getMemory() {
        return microprocessor.getMemory();
    }

    @Override
    public int getValueFromRegister(Intel8080Registers register) {
        return microprocessor.getValueFromRegister(register);
    }

    @Override
    public void setValueInRegister(Intel8080Registers register, int value) {
        microprocessor.setValueInRegister(register, value);
    }

    @Override
    public int getValueFromFlag(Intel8080Flags flag) {
        return microprocessor.getValueFromFlag(flag);
    }

    @Override
    public void setValueInFlag(Intel8080Flags flag, int value) {
        microprocessor.setValueInFlag(flag, value);
    }

    @Override
    public void executeCommand(ICommand command) {
        microprocessor.executeCommand(command);
    }

    @Override
    public void resetRegisters() {
        microprocessor.resetRegisters();
    }

    @Override
    public void resetMemory() {
        microprocessor.resetMemory();
    }
}
