package kernel;

import emulator.IInputOutputSystem;

public class MicroprocessorCommandsAdapter implements IMicroprocessorCommandsAdapter {

    private IMicroprocessor microprocessor;

    public MicroprocessorCommandsAdapter(IMicroprocessor microprocessor) {
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
    public int getValueByFlagName(String flagName) {
        return microprocessor.getValueByFlagName(flagName);
    }

    @Override
    public void setValueByFlagName(String flagName, int value) {
        microprocessor.setValueByFlagName(flagName, value);
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