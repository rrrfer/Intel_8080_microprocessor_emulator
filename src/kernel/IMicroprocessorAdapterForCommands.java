package kernel;

import emulator.IInputOutputSystem;

public interface IMicroprocessorAdapterForCommands {

    int getValueFromRegister(Intel8080Registers register);
    void setValueInRegister(Intel8080Registers register, int value);

    int getValueFromFlag(Intel8080Flags flag);
    void setValueInFlag(Intel8080Flags flag, int value);

    int getValueFromRegisterPair(Intel8080RegisterPairs registerPair);
    void setValueInRegisterPair(Intel8080RegisterPairs registerPair, int value);

    int getAllFlags();
    void setAllFlags(int flags);

    IMemory getMemory();

    IInputOutputSystem getIOSystem();

    void checkByteForSetFlags(int value);

}