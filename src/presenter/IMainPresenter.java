package presenter;

public interface IMainPresenter {
    void loadProgram(String program);
    void run();
    void step();
    void stop();
    void resetRegisters();
    void resetMemory();
    void clearScreens();
    void setBreakpoint(int address);
    void setProgramCounter(int address);
    void consoleOut(int value);
    int consoleIn();
}