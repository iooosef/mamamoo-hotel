package ph.edu.tip.mamamoo.Components;

import ph.edu.tip.mamamoo.ActionListeners.ButtonCursorListener;
import ph.edu.tip.mamamoo.ActionListeners.OnNavButtonPressListener;

import javax.swing.*;
import java.awt.*;

public class NavButton extends JButton {
    private Color pressBgColor= new Color(10, 88, 202);
    private Color defaultBgColor = new Color(0, 120, 215);
    private Color pressFgColor = Color.WHITE;
    private Color defaultFgColor = Color.WHITE;
    public NavButton() { }
    public NavButton(String text) {
        this();
        this.setText(text);
        this.init();
    }
    public NavButton(Icon icon) {
        this();
        this.setIcon(icon);
        this.init();
    }
    public NavButton(String text, Icon icon) {
        this();
        this.setText(text);
        this.setIcon(icon);
        this.init();
    }
    public NavButton(String text, Icon icon, Color pressBgColor, Color defaultBgColor, Color pressFgColor, Color defaultFgColor) {
        this();
        this.setText(text);
        this.setIcon(icon);
        this.pressBgColor = pressBgColor;
        this.defaultBgColor = defaultBgColor;
        this.pressFgColor = pressFgColor;
        this.defaultFgColor = defaultFgColor;
        this.init();
    }
    private void init() {
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setBackground(defaultBgColor);
        this.setForeground(defaultFgColor);;
        this.setMargin(new Insets(5, 0, 5, 0));
        this.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.getModel().addChangeListener(new OnNavButtonPressListener(this, getDefaultBgColor(), getPressBgColor()));
        this.addMouseListener(new ButtonCursorListener(this));
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
