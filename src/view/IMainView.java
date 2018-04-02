package view;

public interface IMainView {

    void setMemoryDataTable(String[][] dataSource);
    void setProgramCounterPosition(int programCounterPosition);
    void setRegistersAndFlagsDataTable(String[][] dataSource);
    void setProgramText(String codeSource);
    void setTranslationResultText(String translationResult);
    void setConsoleOutData(String consoleOutData);
    void setConsoleInData(String consoleInData);
    void setPermissionForAction(int mode);
    int consoleIn();

    /*
    void updateCodeEditor(String programText, boolean hasErrors);
    void updateMemoryTable(String[][] dataSource, int PC, boolean scroll);
    void updateRegistersAndFlagsTable(String[][] dataSource);
    void consoleOut(int value);
    int consoleIn();
    void setRunningMode(boolean isRunningMode);;
    void create();*/
}