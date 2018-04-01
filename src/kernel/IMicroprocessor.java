package kernel;

import emulator.IIOSystem;
import kernel.cmd.ICommand;

public interface IMicroprocessor extends IReadOnlyMicroprocessor {

    int getValueByRegisterPairName(String registerPairName);
    void setValueByRegisterPairName(String registerPairName, int value);

    void setValueByRegisterName(String registerName, int value);

    void setValueByFlagName(String flagName, int value);

    void executeCommand(ICommand command);

    void checkByteForSetFlags(int value);
    void checkWordForSetFlags(int value);

    int getRoundedByte(int value);
    int getRoundedWord(int value);

    void setIOSystem(IIOSystem ioSystem);
    IIOSystem getIOSystem();

    IMemory getMemory();

    void resetRegisters();
    void resetMemory();

    void push(int value);
    int pop();
}