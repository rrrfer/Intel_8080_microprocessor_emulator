package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class RegistersAndFlagsTableCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setFont(MainWindow.mainFont);
        if (column == 0) {
            setHorizontalAlignment(SwingConstants.LEFT);
        } else {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        return this;
    }
}
