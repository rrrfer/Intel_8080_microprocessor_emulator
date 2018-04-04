package emulator;

public interface IIOSystem {
    void consoleOut(int value);
    int requestOfInput();
    void setTimerValue(int value);
    int getTimerValue();
}