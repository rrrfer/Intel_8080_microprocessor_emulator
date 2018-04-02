package emulator;

import kernel.IReadOnlyMicroprocessor;

import java.io.IOException;

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
    String loadProgramFromFile(String path) throws IOException;
    void saveProgramInFile(String path, String programText) throws IOException;
}
