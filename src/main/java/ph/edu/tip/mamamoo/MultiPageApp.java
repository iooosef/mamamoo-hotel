package ph.edu.tip.mamamoo;

import ph.edu.tip.mamamoo.ActionListeners.GoToRegisterListener;
import ph.edu.tip.mamamoo.ActionListeners.LoginListener;
import ph.edu.tip.mamamoo.Pages.HomePage;
import ph.edu.tip.mamamoo.Pages.LoginPage;
import ph.edu.tip.mamamoo.Pages.RegisterPage;

import javax.swing.*;

public class MultiPageApp extends JFrame {
    private LoginPage loginPage;
    private HomePage homePage;
    private RegisterPage registerPage;
    public MultiPageApp(){
        setTitle("Mamamoo Hotel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setResizable(false);

        loginPage = new LoginPage();
        homePage = new HomePage();
        registerPage = new RegisterPage();

        LoginListener loginListener = new LoginListener(this, loginPage, homePage);
        GoToRegisterListener goToRegisterListener = new GoToRegisterListener(this, loginPage, registerPage);

        loginPage.addLoginListener(loginListener);
        loginPage.addGoToRegisterListener(goToRegisterListener);
        showPage(homePage); // TODO : Change this back to loginPage

    }

    private void showPage(JPanel page){
        this.getContentPane().removeAll();
        this.getContentPane().add(page);
        this.revalidate();
        this.repaint();
    }
}
