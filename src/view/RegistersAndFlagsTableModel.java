package view;

import javax.swing.table.AbstractTableModel;

class RegistersAndFlagsTableModel extends AbstractTableModel {

    private String[][] dataSourceForRegistersAndFlagsTable;
    private String[] columnName = {"Name", "Hex", "Bin", "Dec"};

    RegistersAndFlagsTableModel(String[][] dataSourceForRegistersAndFlagsTable) {
        this.dataSourceForRegistersAndFlagsTable = dataSourceForRegistersAndFlagsTable;
    }

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

    @Override
    public int getRowCount() {
        return dataSourceForRegistersAndFlagsTable.length;
    }

    @Override
    public int getColumnCount() {
        return dataSourceForRegistersAndFlagsTable[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dataSourceForRegistersAndFlagsTable[rowIndex][columnIndex];
    }
}
