package kernel;

import kernel.cmd.ICommand;

public interface IMicroprocessor {
    IMemory getMemory();
    int getValueByRegisterName(String registerName);
    void setValueByRegisterName(String registerName, int value);
    int getValueByFlagName(String flagName);
    void setValueByFlagName(String flagName, int value);
    void executeCommand(ICommand command);
    void checkValueForSetFlags(int value);
    int getRoundedValue(int value);
}
