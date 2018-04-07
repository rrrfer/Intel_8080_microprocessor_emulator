package kernel;

public class MicroprocessorAdapterForPresenter implements IMicroprocessorAdapterForPresenter {

    private IMicroprocessor microprocessor;

    public MicroprocessorAdapterForPresenter(IMicroprocessor microprocessor) {
        this.microprocessor = microprocessor;
    }

    @Override
    public int getValueByRegisterName(String registerName) {
        return microprocessor.getValueByRegisterName(registerName);
    }

    @Override
    public int getValueByFlagName(Intel8080Flags flag) {
        return microprocessor.getValueByFlagName(flag);
    }

    @Override
    public IReadOnlyMemory getReadOnlyMemory() {
        return microprocessor.getReadOnlyMemory();
    }
}