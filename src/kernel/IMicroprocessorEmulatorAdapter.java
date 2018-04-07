package kernel;

import emulator.IInputOutputSystem;
import kernel.cmd.ICommand;

public interface IMicroprocessorEmulatorAdapter {

    void setIOSystem(IInputOutputSystem ioSystem);

    void setValueByRegisterName(String registerName, int value);
    int getValueByRegisterName(String registerName);

    IMemory getMemory();

    void executeCommand(ICommand command);

    void resetRegisters();
    void resetMemory();
}