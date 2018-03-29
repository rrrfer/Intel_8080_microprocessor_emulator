package kernel;

import kernel.cmd.ICommand;

public interface IMicroprocessor extends IViewMicroprocessor {
    void setValueByRegisterName(String registerName, int value);
    void setValueByFlagName(String flagName, int value);
    int getValueByRegisterPairName(String registerPairName);
    void setValueByRegisterPairName(String registerPairName, int value);
    void executeCommand(ICommand command);
    void checkByteForSetFlags(int value);
    void checkWordForSetFlags(int value);
    int getRoundedByte(int value);
    int getRoundedWord(int value);
    void resetRegisters();
    void resetMemory();
}
