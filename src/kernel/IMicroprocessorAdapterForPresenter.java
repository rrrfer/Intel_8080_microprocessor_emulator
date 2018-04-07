package kernel;

public interface IMicroprocessorAdapterForPresenter {
    int getValueByRegisterName(String registerName);
    int getValueByFlagName(Intel8080Flags flag);
    IReadOnlyMemory getReadOnlyMemory();
}