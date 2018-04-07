package kernel;

import emulator.IInputOutputSystem;

public class MicroprocessorAdapterForCommands implements IMicroprocessorAdapterForCommands {

    private IMicroprocessor microprocessor;

    public MicroprocessorAdapterForCommands(IMicroprocessor microprocessor) {
        this.microprocessor = microprocessor;
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
    public int getValueByFlagName(Intel8080Flags flag) {
        return microprocessor.getValueByFlagName(flag);
    }

    @Override
    public void setValueByFlagName(Intel8080Flags flag, int value) {
        microprocessor.setValueByFlagName(flag, value);
    }

    @Override
    public int getValueByRegisterPairName(String registerPairName) {
        return microprocessor.getValueByRegisterPairName(registerPairName);
    }

    @Override
    public void setValueByRegisterPairName(String registerPairName, int value) {
        microprocessor.setValueByRegisterPairName(registerPairName, value);
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
        microprocessor.checkByteForSetFlags(value);
    }
}