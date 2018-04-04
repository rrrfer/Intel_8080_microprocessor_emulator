package emulator;

public interface IInputOutputSystem {
    void stdOutput(int value);
    int requestOfStdInput();
    void writeTimerValue(int value);
    int readTimerValue();
    void writeValueInInputRegisterOfPixelScreen(int value);
    int readValueFromOutputRegisterOfPixelScreen();
    void clearScreens();
}