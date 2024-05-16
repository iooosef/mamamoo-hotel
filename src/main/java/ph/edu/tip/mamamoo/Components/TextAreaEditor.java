package ph.edu.tip.mamamoo.Components;

import javax.swing.*;
import java.awt.*;

class TextAreaEditor extends DefaultCellEditor {
    protected JScrollPane scrollPane;
    protected JTextArea textArea;

    public TextAreaEditor() {
        super(new JCheckBox());
        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        scrollPane = new JScrollPane(textArea);
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        textArea.setText((value == null) ? "" : value.toString());
        return scrollPane;
    }

    public Object getCellEditorValue() {
        return textArea.getText();
    }
}