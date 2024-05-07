package ph.edu.tip.mamamoo.ActionListeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.MultiPageApp;
import ph.edu.tip.mamamoo.Pages.HomePage;
import ph.edu.tip.mamamoo.Pages.LoginPage;

import java.awt.event.*;

public class LoginListener implements ActionListener {
    private final Logger _logger = LogManager.getLogger();
    private MultiPageApp app;
    private LoginPage loginPage;
    private HomePage homePage;
    public LoginListener(MultiPageApp app, LoginPage loginPage, HomePage homePage){
        this.app = app;
        this.loginPage = loginPage;
        this.homePage = homePage;
    }
    public void actionPerformed(ActionEvent e){
        String login_username = loginPage.getUsername();
        char[] login_password = loginPage.getPassword();
        if(login_username.equals("admin") && new String(login_password).equals("admin")){
            showHomePage();
            _logger.info("User logged in!");
        } else {
            loginPage.setStatus("Invalid username or password");
        }
    }
    private MultiPageApp showHomePage(){
        app.getContentPane().removeAll();
        app.getContentPane().add(homePage);
        app.revalidate();
        app.repaint();
        return app;
    }
}
