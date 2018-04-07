package kernel;

import emulator.IInputOutputSystem;
import kernel.cmd.ICommand;

public interface IMicroprocessor {

    int getValueFromRegister(Intel8080Registers register);
    void setValueInRegister(Intel8080Registers register, int value);

    int getValueFromRegisterPair(Intel8080RegisterPairs registerPair);
    void setValueInRegisterPair(Intel8080RegisterPairs registerPair, int value);

    int getValueFromFlag(Intel8080Flags flag);
    void setValueInFlag(Intel8080Flags flag, int value);

    int getAllFlags();
    void setAllFlags(int flags);

    IInputOutputSystem getIOSystem();
    void setIOSystem(IInputOutputSystem ioSystem);

    IReadOnlyMemory getReadOnlyMemory();
    IMemory getMemory();

    void executeCommand(ICommand command);
    void checkValueForSetFlags(int value);

    void resetRegisters();
    void resetMemory();
}