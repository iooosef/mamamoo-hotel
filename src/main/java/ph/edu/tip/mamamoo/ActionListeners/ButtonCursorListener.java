package ph.edu.tip.mamamoo.ActionListeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonCursorListener extends MouseAdapter {
    private final JComponent component;

    public ButtonCursorListener(JComponent component) {
        this.component = component;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        component.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        component.setCursor(Cursor.getDefaultCursor());
    }
}
