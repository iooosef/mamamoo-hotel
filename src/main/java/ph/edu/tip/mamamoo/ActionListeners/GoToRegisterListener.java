package ph.edu.tip.mamamoo.ActionListeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.MultiPageApp;
import ph.edu.tip.mamamoo.Pages.LoginPage;
import ph.edu.tip.mamamoo.Pages.RegisterPage;
import ph.edu.tip.mamamoo.Utilities.DotEnvUtility;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GoToRegisterListener extends KeyAdapter {
    Logger _logger = LogManager.getLogger();
    DotEnvUtility dotEnv = DotEnvUtility.configure().load();
    private MultiPageApp app;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    public GoToRegisterListener(MultiPageApp app, LoginPage loginPage, RegisterPage registerPage){
        this.app = app;
        this.loginPage = loginPage;
        this.registerPage = registerPage;
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_F12){
            String regKey = dotEnv.get("regKey");
            JPasswordField passwordBox = new JPasswordField(10);
            int option = JOptionPane.showConfirmDialog(null,
                                                        passwordBox,
                                                        "Enter Registration Key:",
                                                        JOptionPane.OK_CANCEL_OPTION);
            if(option == JOptionPane.OK_OPTION){
                char[] input_key = passwordBox.getPassword();
                if(new String(input_key).equals(regKey)){
                    showRegisterPage();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Registration Key", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    private MultiPageApp showRegisterPage(){
        app.getContentPane().removeAll();
        app.getContentPane().add(registerPage);
        app.revalidate();
        app.repaint();
        return app;
    }
}
