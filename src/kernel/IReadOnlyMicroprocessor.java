package kernel;

public interface IReadOnlyMicroprocessor {
    IReadOnlyMemory getReadOnlyMemory();
    int getValueByRegisterName(String registerName);
    int getValueByFlagName(String flagName);
}
