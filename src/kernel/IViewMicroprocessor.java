package kernel;

public interface IViewMicroprocessor {
    IMemory getMemory();
    int getValueByRegisterName(String registerName);
    int getValueByFlagName(String flagName);
}
