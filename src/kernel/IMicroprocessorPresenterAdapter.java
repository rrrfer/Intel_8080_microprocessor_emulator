package kernel;

public interface IMicroprocessorPresenterAdapter {
    int getValueByRegisterName(String registerName);
    int getValueByFlagName(String flagName);
    IReadOnlyMemory getReadOnlyMemory();
}