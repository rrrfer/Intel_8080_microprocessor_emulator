package kernel;

public class MicroprocessorPresenterAdapter implements IMicroprocessorPresenterAdapter {

    private IMicroprocessor microprocessor;

    public MicroprocessorPresenterAdapter(IMicroprocessor microprocessor) {
        this.microprocessor = microprocessor;
    }

    @Override
    public int getValueByRegisterName(String registerName) {
        return microprocessor.getValueByRegisterName(registerName);
    }

    @Override
    public int getValueByFlagName(String flagName) {
        return microprocessor.getValueByFlagName(flagName);
    }

    @Override
    public IReadOnlyMemory getReadOnlyMemory() {
        return microprocessor.getReadOnlyMemory();
    }
}