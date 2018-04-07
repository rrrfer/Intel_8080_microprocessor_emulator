package view;

import java.util.ArrayList;

public interface IMainView {

    void setViewTitle(String title);
    void setProgramText(String codeSource);
    void setProgramCounterPosition(int programCounterPosition);
    void setTranslationResult(String translationResult, boolean isErrorsSearch);;

    void setBreakpoints(ArrayList<Integer> breakpointsData);

    void registersTableUpdate();
    void memoryTableUpdate();
    void pixelScreenUpdate();
    void characterScreenUpdate();

    void setConsoleOutputData(String consoleOutData);
    void clearInputConsole();

    void setPermissionForActions(int mode);

    int requestOfInput();
}