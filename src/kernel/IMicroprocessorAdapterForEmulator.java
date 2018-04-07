package kernel;

import emulator.IInputOutputSystem;
import kernel.cmd.ICommand;

public interface IMicroprocessorAdapterForEmulator {

    void setIOSystem(IInputOutputSystem ioSystem);

    void setValueByRegisterName(String registerName, int value);
    int getValueByRegisterName(String registerName);

    int getValueByFlagName(Intel8080Flags flag);
    void setValueByFlagName(Intel8080Flags flag, int value);

    IMemory getMemory();

    void executeCommand(ICommand command);

    void resetRegisters();
    void resetMemory();
}