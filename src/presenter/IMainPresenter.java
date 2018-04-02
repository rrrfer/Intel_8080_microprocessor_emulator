package presenter;

import java.io.IOException;

public interface IMainPresenter {
    void translation(String program);
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
    void loadProgramFromFile(String path) throws IOException;
    void saveProgramInFile(String path, String programText) throws IOException;
}