package emulator;

import kernel.IReadOnlyMicroprocessor;

import java.io.IOException;
import java.util.ArrayList;

public interface IEmulator {
    boolean translation(String program);
    String getTranslationResult();
    String[] getCommandsList();
    IReadOnlyMicroprocessor getViewInterface();
    void run();
    boolean step();
    void resetRegisters();
    void resetMemory();
    void setProgramCounter(int address);
    void setBreakpoint(int address);
    void removeAllBreakpoints();
    ArrayList<Integer> getBreakpoints();
    String loadProgramFromFile(String path) throws IOException;
    void saveProgramInFile(String path, String programText) throws IOException;
}
