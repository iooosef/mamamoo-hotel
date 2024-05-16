package ph.edu.tip.mamamoo.Pages;

import net.miginfocom.swing.MigLayout;
import ph.edu.tip.mamamoo.ActionListeners.GoToRegisterListener;
import ph.edu.tip.mamamoo.ActionListeners.LoginListener;
import ph.edu.tip.mamamoo.Utilities.IconUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel {
    private JTextField usernameBox;
    private JPasswordField passwordBox;
    private JButton loginBtn;
    private JLabel statusLbl;
    private LoginListener loginListener;
    private GoToRegisterListener goToRegisterListener;
    private JLabel logoLabel;
    private IconUtility iconUtility = new IconUtility();

    public LoginPage() {
        this.setLayout(new MigLayout("flowy, alignx center, aligny center"));
        this.setFocusable(true);
        this.requestFocusInWindow();

        logoLabel = iconUtility.getLogoLabel(logoLabel,"./static/images/logo_login.png", 175, -1);
        this.add(logoLabel, "grow");

        add(new Label("Username:"));
        usernameBox = new JTextField(20);
        add(usernameBox, "growx");
        add(new Label("Password:"));
        passwordBox = new JPasswordField(16);
        add(passwordBox, "growx");
        loginBtn = new JButton("Login");
        add(loginBtn, "growx");
        statusLbl = new JLabel();
        add(statusLbl, "growx");
    }
    public String getUsername() {
        return usernameBox.getText();
    }
    public char[] getPassword() {
        return passwordBox.getPassword();
    }
    public void setStatus(String status) {
        statusLbl.setText(status);
    }
    public void addLoginListener(LoginListener listener) {
        this.loginListener = listener;
        loginBtn.addActionListener(listener);
    }
    public void addGoToRegisterListener(GoToRegisterListener listener) {
        this.goToRegisterListener = listener;
        this.addKeyListener(listener);
    }
}
