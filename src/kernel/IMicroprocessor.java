package kernel;

import emulator.IInputOutputSystem;
import kernel.cmd.ICommand;

public interface IMicroprocessor extends IReadOnlyMicroprocessor {

    int getValueByRegisterPairName(String registerPairName);
    void setValueByRegisterPairName(String registerPairName, int value);

    void setValueByRegisterName(String registerName, int value);

    void setValueByFlagName(String flagName, int value);

    int getAllFlags();
    void setAllFlags(int flags);

    void executeCommand(ICommand command);

    void checkByteForSetFlags(int value);

    int getRoundedByte(int value);
    int getRoundedWord(int value);

    void setIOSystem(IInputOutputSystem ioSystem);
    IInputOutputSystem getIOSystem();

    IMemory getMemory();

    void resetRegisters();
    void resetMemory();

    void push(int value);
    int pop();
}