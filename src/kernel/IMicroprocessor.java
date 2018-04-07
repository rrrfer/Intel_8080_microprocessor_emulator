package kernel;

import emulator.IInputOutputSystem;
import kernel.cmd.ICommand;

public interface IMicroprocessor {

    int getValueByRegisterName(String registerName);
    void setValueByRegisterName(String registerName, int value);

    int getValueByFlagName(Intel8080Flags flag);
    void setValueByFlagName(Intel8080Flags flag, int value);

    int getValueByRegisterPairName(String registerPairName);
    void setValueByRegisterPairName(String registerPairName, int value);

    int getAllFlags();
    void setAllFlags(int flags);

    IInputOutputSystem getIOSystem();
    void setIOSystem(IInputOutputSystem ioSystem);

    IReadOnlyMemory getReadOnlyMemory();
    IMemory getMemory();

    void executeCommand(ICommand command);
    void checkByteForSetFlags(int value);

    void resetRegisters();
    void resetMemory();
}