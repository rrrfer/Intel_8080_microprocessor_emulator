package kernel;

import emulator.IInputOutputSystem;

public interface IMicroprocessorAdapterForCommands {

    int getValueByRegisterName(String registerName);
    void setValueByRegisterName(String registerName, int value);

    int getValueByFlagName(Intel8080Flags flag);
    void setValueByFlagName(Intel8080Flags flag, int value);

    int getValueByRegisterPairName(String registerPairName);
    void setValueByRegisterPairName(String registerPairName, int value);

    int getAllFlags();
    void setAllFlags(int flags);

    IMemory getMemory();

    IInputOutputSystem getIOSystem();

    void checkByteForSetFlags(int value);

}