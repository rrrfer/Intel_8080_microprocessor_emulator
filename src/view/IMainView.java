package view;

public interface IMainView {
    void updateCodeEditor(String programText);
    void updateMemoryTable(String[][] dataSource);
    void updateRegistersAndFlagsTable(String[][] dataSource);
    void create();
}