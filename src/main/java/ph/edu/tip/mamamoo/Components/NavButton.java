package ph.edu.tip.mamamoo.Components;

import ph.edu.tip.mamamoo.ActionListeners.ButtonCursorListener;
import ph.edu.tip.mamamoo.ActionListeners.OnNavButtonPressListener;

import javax.swing.*;
import java.awt.*;

public class NavButton extends JButton {
    private Color pressBgColor;
    private Color defaultBgColor;
    private Color pressFgColor;
    private Color defaultFgColor;
    public NavButton() {
        this.pressBgColor = new Color(10, 88, 202);
        this.defaultBgColor = new Color(0, 120, 215);
        this.pressFgColor = Color.WHITE;
        this.defaultFgColor = Color.WHITE;
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setBackground(defaultBgColor);
        this.setForeground(defaultFgColor);;
        this.setMargin(new Insets(5, 0, 5, 0));
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.getModel().addChangeListener(new OnNavButtonPressListener(this));
        this.addMouseListener(new ButtonCursorListener(this));
    }
    public NavButton(String text) {
        this();
        this.setText(text);
    }
    public NavButton(Icon icon) {
        this();
        this.setIcon(icon);
    }
    public NavButton(String text, Icon icon) {
        this();
        this.setText(text);
        this.setIcon(icon);
    }
    public NavButton(String text, Icon icon, Color pressBgColor, Color defaultBgColor, Color pressFgColor, Color defaultFgColor) {
        this(text, icon);
        this.pressBgColor = pressBgColor;
        this.defaultBgColor = defaultBgColor;
        this.pressFgColor = pressFgColor;
        this.defaultFgColor = defaultFgColor;
    }
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressBgColor);
        } else {
            g.setColor(defaultBgColor);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
    public Color getPressBgColor() {
        return pressBgColor;
    }
    public void setPressBgColor(Color pressBgColor) {
        this.pressBgColor = pressBgColor;
    }
    public Color getDefaultBgColor() {
        return defaultBgColor;
    }
    public void setDefaultBgColor(Color defaultBgColor) {
        this.defaultBgColor = defaultBgColor;
    }
    public Color getPressFgColor() {
        return pressFgColor;
    }
    public void setPressFgColor(Color pressFgColor) {
        this.pressFgColor = pressFgColor;
    }
    public Color getDefaultFgColor() {
        return defaultFgColor;
    }
    public void setDefaultFgColor(Color defaultFgColor) {
        this.defaultFgColor = defaultFgColor;
    }
}
