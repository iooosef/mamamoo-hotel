package ph.edu.tip.mamamoo.ActionListeners;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class OnNavButtonPressListener implements ChangeListener {
    private JButton button;
    public Color bgColor = new Color(0, 120, 215);
    public Color bgColorPressed = new Color(10, 88, 202);
    public OnNavButtonPressListener(JButton button){
        this.button = button;
    }
    public OnNavButtonPressListener(JButton button, Color bgColor, Color bgColorPressed) {
        this(button);
        this.bgColor = bgColor;
        this.bgColorPressed = bgColorPressed;
    }
    @Override
    public void stateChanged(javax.swing.event.ChangeEvent e) {
        ButtonModel model = button.getModel();
        if (model.isPressed() && model.isArmed()) {
            button.setBackground(bgColorPressed);
        } else {
            button.setBackground(bgColor);
        }
    }
}
