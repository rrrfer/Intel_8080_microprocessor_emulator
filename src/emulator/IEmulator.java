package emulator;

import kernel.IMicroprocessorAdapterForPresenter;

import java.io.IOException;
import java.util.ArrayList;

public interface IEmulator {
    void translation(String program);
    void run();
    boolean step();
    void resetRegisters();
    void resetMemory();
    void clearScreen();
    void setProgramCounter(int address);
    void setBreakpoint(int address);
    void removeAllBreakpoints();
    ArrayList<Integer> getBreakpoints();
    String getTranslationResult();
    String[] getCommandsList();
    IMicroprocessorAdapterForPresenter getMicroprocessor();
    String loadProgramFromFile(String path) throws IOException;
    void saveProgramInFile(String path, String programText) throws IOException;
}
