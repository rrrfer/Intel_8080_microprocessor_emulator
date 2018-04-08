package view;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Label2AddressTableModel extends AbstractTableModel {

    private final String[] columnName = {"Label", "Address"};
    private ArrayList<String> dataSourceForLabel2AddressTable;

    public Label2AddressTableModel(ArrayList<String> dataSourceForLabel2AddressTable) {
        this.dataSourceForLabel2AddressTable = dataSourceForLabel2AddressTable;
    }

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

    @Override
    public int getRowCount() {
        return dataSourceForLabel2AddressTable.size() / 2;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dataSourceForLabel2AddressTable.get(rowIndex * 2 + columnIndex);
    }
}