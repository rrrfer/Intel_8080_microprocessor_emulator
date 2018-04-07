package kernel;

public interface IMicroprocessorAdapterForPresenter {
    int getValueFromRegister(Intel8080Registers register);
    int getValueByFlag(Intel8080Flags flag);
    IReadOnlyMemory getReadOnlyMemory();
}