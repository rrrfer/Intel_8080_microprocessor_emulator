package kernel;

public class MicroprocessorAdapterForPresenter implements IMicroprocessorAdapterForPresenter {

    private IMicroprocessor microprocessor;

    public MicroprocessorAdapterForPresenter(IMicroprocessor microprocessor) {
        this.microprocessor = microprocessor;
    }

    @Override
    public int getValueFromRegister(Intel8080Registers register) {
        return microprocessor.getValueFromRegister(register);
    }

    @Override
    public int getValueByFlag(Intel8080Flags flag) {
        return microprocessor.getValueFromFlag(flag);
    }

    @Override
    public IReadOnlyMemory getReadOnlyMemory() {
        return microprocessor.getReadOnlyMemory();
    }
}