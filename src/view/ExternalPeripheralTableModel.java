package view;

import model.emulator.IExternalPeripheral;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ExternalPeripheralTableModel extends AbstractTableModel {

    private ArrayList<IExternalPeripheral> externalPeripherals;

    public ExternalPeripheralTableModel(ArrayList<IExternalPeripheral> externalPeripherals) {
        this.externalPeripherals = externalPeripherals;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Interrupt";
            case 1: return "Description";
            case 2: return "Port";
            default: return "Name";
        }
    }

    @Override
    public int getRowCount() {
        return externalPeripherals.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: {
                if (rowIndex < 8 && externalPeripherals.get(rowIndex)._getPriority() < 8) {
                    StringBuilder address = new StringBuilder("0x" + Integer.toString(externalPeripherals.get(rowIndex)._getPriority() * 0x08, 16));
                    while (address.length() != 6) {
                        address.insert(2, "0");
                    }
                    return address;
                } else {
                    return "-";
                }
            }
            case 1:
                return externalPeripherals.get(rowIndex).getDescription();
            case 2:
                return "0x" + Integer.toString(externalPeripherals.get(rowIndex).getPort(), 16);
            default:
                return 0;
        }
    }
}