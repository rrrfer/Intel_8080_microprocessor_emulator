package view;

public interface IMainView {
    void updateCodeEditor(String programText, boolean hasErrors);
    void updateMemoryTable(String[][] dataSource, int PC, boolean scroll);
    void updateRegistersAndFlagsTable(String[][] dataSource);
    void setRunningMode(boolean isRunningMode);
    void create();
}