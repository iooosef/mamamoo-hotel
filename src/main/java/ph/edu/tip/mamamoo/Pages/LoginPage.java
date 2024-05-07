package ph.edu.tip.mamamoo.Pages;

import ph.edu.tip.mamamoo.ActionListeners.GoToRegisterListener;
import ph.edu.tip.mamamoo.ActionListeners.LoginListener;

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

    public LoginPage() {
        usernameBox = new JTextField(20);
        passwordBox = new JPasswordField(16);
        loginBtn = new JButton("Login");
        statusLbl = new JLabel();
        this.setFocusable(true);
        this.requestFocusInWindow();

        add(new Label("Username:"));
        add(usernameBox);
        add(new Label("Password:"));
        add(passwordBox);
        add(loginBtn);
        add(statusLbl);
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
