package ph.edu.tip.mamamoo.ActionListeners;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class OnNavButtonPressListener implements ChangeListener {
    private JButton button;
    public OnNavButtonPressListener(JButton button){
        this.button = button;
    }
    @Override
    public void stateChanged(javax.swing.event.ChangeEvent e) {
        ButtonModel model = button.getModel();
        if (model.isPressed() && model.isArmed()) {
            button.setBackground(new Color(10, 88, 202));
        } else {
            button.setBackground(new Color(0, 120, 215));
        }
    }
}
