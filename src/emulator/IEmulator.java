package emulator;

import kernel.IViewMicroprocessor;

interface IEmulator {
    IViewMicroprocessor run();
    IViewMicroprocessor step();
    void loadProgram(String program);
    boolean hasErrors();
    String getTranslationsResult();
}
