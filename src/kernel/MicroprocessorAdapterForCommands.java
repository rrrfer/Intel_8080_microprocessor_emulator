package kernel;

import emulator.IInputOutputSystem;

public class MicroprocessorAdapterForCommands implements IMicroprocessorAdapterForCommands {

    private IMicroprocessor microprocessor;

    public MicroprocessorAdapterForCommands(IMicroprocessor microprocessor) {
        this.microprocessor = microprocessor;
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
    public int getValueFromRegisterPair(Intel8080RegisterPairs registerPair) {
        return microprocessor.getValueFromRegisterPair(registerPair);
    }

    @Override
    public void setValueInRegisterPair(Intel8080RegisterPairs registerPair, int value) {
        microprocessor.setValueInRegisterPair(registerPair, value);
    }

    @Override
    public int getAllFlags() {
        return microprocessor.getAllFlags();
    }

    @Override
    public void setAllFlags(int flags) {
        microprocessor.setAllFlags(flags);
    }

    @Override
    public IMemory getMemory() {
        return microprocessor.getMemory();
    }

    @Override
    public IInputOutputSystem getIOSystem() {
        return microprocessor.getIOSystem();
    }

    @Override
    public void checkByteForSetFlags(int value) {
        microprocessor.checkValueForSetFlags(value);
    }
}