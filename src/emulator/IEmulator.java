package emulator;

import kernel.IViewMicroprocessor;

public interface IEmulator {
    void run();
    boolean step();
    void loadProgram(String program);
    boolean hasTranslationErrors();
    String getTranslationResult();
    String[] getCommandsList();
    IViewMicroprocessor getViewInterface();
}
