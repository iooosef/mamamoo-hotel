package ph.edu.tip.mamamoo;

import ph.edu.tip.mamamoo.ActionListeners.GoToRegisterListener;
import ph.edu.tip.mamamoo.ActionListeners.LoginListener;
import ph.edu.tip.mamamoo.ActionListeners.NavButtonListener;
import ph.edu.tip.mamamoo.Pages.*;
import ph.edu.tip.mamamoo.Pages.Shared.NavBar;

import javax.swing.*;

public class MultiPageApp extends JFrame {
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private HomePage homePage;
    private RoomsPage roomsPage;
    public BookingsPage bookingsPage;
    private PaymentsPage paymentsPage;

    public MultiPageApp(){
        setTitle("Mamamoo Hotel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setResizable(false);

        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        homePage = new HomePage(this);
        roomsPage = new RoomsPage(this);
        bookingsPage = new BookingsPage(this);
        paymentsPage = new PaymentsPage(this);

        LoginListener loginListener = new LoginListener(this, loginPage, homePage);
        GoToRegisterListener goToRegisterListener = new GoToRegisterListener(this, loginPage, registerPage);

        loginPage.addLoginListener(loginListener);
        loginPage.addGoToRegisterListener(goToRegisterListener);
        showPage(loginPage); // TODO : Change this back to loginPage

        NavBar homePageNavBar = homePage.getNavBar();
        homePageNavBar.addNavButtonListenerTo(new NavButtonListener(this, homePageNavBar, roomsPage),
                homePageNavBar.getNavButtons().roomsButton);
        homePageNavBar.addNavButtonListenerTo(new NavButtonListener(this, homePageNavBar, bookingsPage),
                homePageNavBar.getNavButtons().bookingsButton);
        homePageNavBar.addNavButtonListenerTo(new NavButtonListener(this, homePageNavBar, paymentsPage),
                homePageNavBar.getNavButtons().paymentsButton);

        NavBar roomsPageNavBar = roomsPage.getNavBar();
        roomsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, roomsPageNavBar, homePage),
                roomsPageNavBar.getNavButtons().homeButton);
        roomsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, roomsPageNavBar, bookingsPage),
                roomsPageNavBar.getNavButtons().bookingsButton);
        roomsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, roomsPageNavBar, paymentsPage),
                roomsPageNavBar.getNavButtons().paymentsButton);

        NavBar bookingsPageNavBar = bookingsPage.getNavBar();
        bookingsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, bookingsPageNavBar, homePage),
                bookingsPageNavBar.getNavButtons().homeButton);
        bookingsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, bookingsPageNavBar, roomsPage),
                bookingsPageNavBar.getNavButtons().roomsButton);
        bookingsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, bookingsPageNavBar, paymentsPage),
                bookingsPageNavBar.getNavButtons().paymentsButton);

        NavBar paymentsPageNavBar = paymentsPage.getNavBar();
        paymentsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, paymentsPageNavBar, homePage),
                paymentsPageNavBar.getNavButtons().homeButton);
        paymentsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, paymentsPageNavBar, roomsPage),
                paymentsPageNavBar.getNavButtons().roomsButton);
        paymentsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, paymentsPageNavBar, bookingsPage),
                paymentsPageNavBar.getNavButtons().bookingsButton);

    }

    private void showPage(JPanel page){
        this.getContentPane().removeAll();
        this.getContentPane().add(page);
        this.revalidate();
        this.repaint();
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }
}
