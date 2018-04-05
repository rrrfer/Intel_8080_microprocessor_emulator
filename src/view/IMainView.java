package view;

import java.util.ArrayList;

public interface IMainView {
    void setEditableFileTitle(String title);
    void setMemoryDataTable(String[][] dataSource);
    void setProgramCounterPosition(int programCounterPosition);
    void setRegistersAndFlagsDataTable(String[][] dataSource);
    void setProgramText(String codeSource);
    void setTranslationResultText(String translationResult, boolean isErrorsSearch);
    void setConsoleOutData(String consoleOutData);
    void setConsoleInData(String consoleInData);
    void setPermissionForActions(int mode);
    void setBreakpointsData(ArrayList<Integer> breakpointsData);
    void setPixelScreenData(int[][] pixelScreenData);
    void setCharacterScreenData(int[][] charScreenColorData, int[][] charScreenCharData);
    int requestOfInput();
}