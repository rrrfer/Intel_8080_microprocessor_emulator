package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class MemoryTableCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setFont(MainWindow.mainFont);

        if (row == MainWindow.selectedRow) {
            setBackground(MainWindow.greenColor);
        } else if (MainWindow.breakpoints[row] != 0) {
            setBackground(Color.ORANGE);
        } else {
            setBackground(Color.WHITE);
        }

        if (column == 1) {
            setHorizontalAlignment(SwingConstants.LEFT);
        } else {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        return this;
    }
}
