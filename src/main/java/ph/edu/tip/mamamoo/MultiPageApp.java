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
    private BookingsPage bookingsPage;
    private RoomServicePage roomServicePage;

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
        roomServicePage = new RoomServicePage(this);

        LoginListener loginListener = new LoginListener(this, loginPage, homePage);
        GoToRegisterListener goToRegisterListener = new GoToRegisterListener(this, loginPage, registerPage);

        loginPage.addLoginListener(loginListener);
        loginPage.addGoToRegisterListener(goToRegisterListener);
        showPage(homePage); // TODO : Change this back to loginPage

        NavBar homePageNavBar = homePage.getNavBar();
        homePageNavBar.addNavButtonListenerTo(new NavButtonListener(this, homePageNavBar, roomsPage),
                homePageNavBar.getNavButtons().roomsButton);
        homePageNavBar.addNavButtonListenerTo(new NavButtonListener(this, homePageNavBar, bookingsPage),
                homePageNavBar.getNavButtons().bookingsButton);
        homePageNavBar.addNavButtonListenerTo(new NavButtonListener(this, homePageNavBar, roomServicePage),
                homePageNavBar.getNavButtons().roomServiceButton);

        NavBar roomsPageNavBar = roomsPage.getNavBar();
        roomsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, roomsPageNavBar, homePage),
                roomsPageNavBar.getNavButtons().homeButton);
        roomsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, roomsPageNavBar, bookingsPage),
                roomsPageNavBar.getNavButtons().bookingsButton);
        roomsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, roomsPageNavBar, roomServicePage),
                roomsPageNavBar.getNavButtons().roomServiceButton);

        NavBar bookingsPageNavBar = bookingsPage.getNavBar();
        bookingsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, bookingsPageNavBar, homePage),
                bookingsPageNavBar.getNavButtons().homeButton);
        bookingsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, bookingsPageNavBar, roomsPage),
                bookingsPageNavBar.getNavButtons().roomsButton);
        bookingsPageNavBar.addNavButtonListenerTo(new NavButtonListener(this, bookingsPageNavBar, roomServicePage),
                bookingsPageNavBar.getNavButtons().roomServiceButton);

        NavBar roomServicePageNavBar = roomServicePage.getNavBar();
        roomServicePageNavBar.addNavButtonListenerTo(new NavButtonListener(this, roomServicePageNavBar, homePage),
                roomServicePageNavBar.getNavButtons().homeButton);
        roomServicePageNavBar.addNavButtonListenerTo(new NavButtonListener(this, roomServicePageNavBar, roomsPage),
                roomServicePageNavBar.getNavButtons().roomsButton);
        roomServicePageNavBar.addNavButtonListenerTo(new NavButtonListener(this, roomServicePageNavBar, bookingsPage),
                roomServicePageNavBar.getNavButtons().bookingsButton);

    }

    private void showPage(JPanel page){
        this.getContentPane().removeAll();
        this.getContentPane().add(page);
        this.revalidate();
        this.repaint();
    }
}
