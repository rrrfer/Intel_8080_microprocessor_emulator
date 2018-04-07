package kernel;

import emulator.IInputOutputSystem;

public interface IMicroprocessorCommandsAdapter {

    int getValueByRegisterName(String registerName);
    void setValueByRegisterName(String registerName, int value);

    int getValueByFlagName(String flagName);
    void setValueByFlagName(String flagName, int value);

    int getValueByRegisterPairName(String registerPairName);
    void setValueByRegisterPairName(String registerPairName, int value);

    int getAllFlags();
    void setAllFlags(int flags);

    IMemory getMemory();

    IInputOutputSystem getIOSystem();

    void checkByteForSetFlags(int value);

}