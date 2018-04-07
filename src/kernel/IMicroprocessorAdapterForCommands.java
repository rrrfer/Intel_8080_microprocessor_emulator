package kernel;

import emulator.IInputOutputSystem;

public interface IMicroprocessorAdapterForCommands {

    int getValueFromRegister(Intel8080Registers register);
    void setValueInRegister(Intel8080Registers register, int value);

    int getValueFromFlag(Intel8080Flags flag);
    void setValueInFlag(Intel8080Flags flag, int value);

    int getValueByRegisterPairName(String registerPairName);
    void setValueByRegisterPairName(String registerPairName, int value);

    int getAllFlags();
    void setAllFlags(int flags);

    IMemory getMemory();

    IInputOutputSystem getIOSystem();

    void checkByteForSetFlags(int value);

}