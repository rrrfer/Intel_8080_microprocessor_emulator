package kernel;

import emulator.IInputOutputSystem;
import kernel.cmd.ICommand;

public interface IMicroprocessorAdapterForEmulator {

    void setIOSystem(IInputOutputSystem ioSystem);

    int getValueFromRegister(Intel8080Registers register);
    void setValueInRegister(Intel8080Registers register, int value);

    int getValueFromFlag(Intel8080Flags flag);
    void setValueInFlag(Intel8080Flags flag, int value);

    IMemory getMemory();

    void executeCommand(ICommand command);

    void resetRegisters();
    void resetMemory();
}