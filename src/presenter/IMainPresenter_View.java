package presenter;

import java.io.IOException;

public interface IMainPresenter_View {
    void translation(String program);
    void run();
    void step();
    void stop();
    void resetRegisters();
    void resetMemory();
    void setBreakpoint(int address);
    void removeAllBreakpoints();
    void setProgramCounter(int address);
    void loadProgramFromFile(String path) throws IOException;
    void saveAsProgramInFile(String path, String programText) throws IOException;
    boolean saveProgramInFile(String programText) throws IOException;
}