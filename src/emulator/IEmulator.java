package emulator;

import kernel.IReadOnlyMicroprocessor;

public interface IEmulator {
    boolean loadProgram(String program);
    String getTranslationResult();
    String[] getCommandsList();
    IReadOnlyMicroprocessor getViewInterface();
    void run();
    boolean step();
    void resetRegisters();
    void resetMemory();
}
