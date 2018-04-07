package kernel;

import emulator.IInputOutputSystem;
import kernel.cmd.ICommand;

public class MicroprocessorEmulatorAdapter implements IMicroprocessorEmulatorAdapter {

    private IMicroprocessor microprocessor;

    public MicroprocessorEmulatorAdapter(IMicroprocessor microprocessor) {
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
    public int getValueByRegisterName(String registerName) {
        return microprocessor.getValueByRegisterName(registerName);
    }

    @Override
    public void setValueByRegisterName(String registerName, int value) {
        microprocessor.setValueByRegisterName(registerName, value);
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
