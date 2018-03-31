package presenter;

public interface IMainPresenter {
    void open(String path);
    void save(String path);
    void run();
    void step();
    void stop();
    void resetRegisters();
    void resetMemory();
    void clearScreens();
    void setBreakpoint(int address);
    void setProgramCounter(int address);
}