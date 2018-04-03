package view;

import java.util.ArrayList;

public interface IMainView {
    void setMemoryDataTable(String[][] dataSource);
    void setProgramCounterPosition(int programCounterPosition);
    void setRegistersAndFlagsDataTable(String[][] dataSource);
    void setProgramText(String codeSource);
    void setTranslationResultText(String translationResult);
    void setConsoleOutData(String consoleOutData);
    void setConsoleInData(String consoleInData);
    void setPermissionForActions(int mode);
    void setBreakpointsData(ArrayList<Integer> breakpointsData);
    int requestOfInput();
}