package view;

import javax.swing.table.AbstractTableModel;

class MemoryTableModel extends AbstractTableModel {

    private String[][] dataSourceForMemoryTable;
    private String[] columnName = {"Address", "Command", "Code"};

    MemoryTableModel(String[][] dataSourceForMemoryTable) {
        this.dataSourceForMemoryTable = dataSourceForMemoryTable;
    }

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

    @Override
    public int getRowCount() {
        return dataSourceForMemoryTable.length;
    }

    @Override
    public int getColumnCount() {
        return dataSourceForMemoryTable[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dataSourceForMemoryTable[rowIndex][columnIndex];
    }
}
