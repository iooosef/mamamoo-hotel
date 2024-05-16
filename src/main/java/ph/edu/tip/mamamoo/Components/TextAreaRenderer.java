package ph.edu.tip.mamamoo.Components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class TextAreaRenderer extends JTextArea implements TableCellRenderer {
    public TextAreaRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}
