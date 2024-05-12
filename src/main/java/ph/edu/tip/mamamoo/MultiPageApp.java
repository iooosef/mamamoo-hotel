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
        NavButtonListener toRoomsPageListener = new NavButtonListener(this, homePageNavBar, roomsPage);
        homePageNavBar.addNavButtonListenerTo(toRoomsPageListener, homePageNavBar.getNavButtons().roomsButton);
        NavButtonListener toBookingsPageListener = new NavButtonListener(this, homePageNavBar, bookingsPage);
        homePageNavBar.addNavButtonListenerTo(toBookingsPageListener, homePageNavBar.getNavButtons().bookingsButton);
        NavButtonListener toRoomServicePageListener = new NavButtonListener(this, homePageNavBar, roomServicePage);
        homePageNavBar.addNavButtonListenerTo(toRoomServicePageListener, homePageNavBar.getNavButtons().roomServiceButton);

        NavBar roomsPageNavBar = roomsPage.getNavBar();
        NavButtonListener toHomePageListener = new NavButtonListener(this, roomsPageNavBar, homePage);
        roomsPageNavBar.addNavButtonListenerTo(toHomePageListener, roomsPageNavBar.getNavButtons().homeButton);
        NavButtonListener toBookingsPageFromRoomsPageListener = new NavButtonListener(this, roomsPageNavBar, bookingsPage);
        roomsPageNavBar.addNavButtonListenerTo(toBookingsPageFromRoomsPageListener, roomsPageNavBar.getNavButtons().bookingsButton);
        NavButtonListener toRoomServicePageFromRoomsPageListener = new NavButtonListener(this, roomsPageNavBar, roomServicePage);
        roomsPageNavBar.addNavButtonListenerTo(toRoomServicePageFromRoomsPageListener, roomsPageNavBar.getNavButtons().roomServiceButton);

        NavBar bookingsPageNavBar = bookingsPage.getNavBar();
        NavButtonListener toHomePageFromBookingsPageListener = new NavButtonListener(this, bookingsPageNavBar, homePage);
        bookingsPageNavBar.addNavButtonListenerTo(toHomePageFromBookingsPageListener, bookingsPageNavBar.getNavButtons().homeButton);
        NavButtonListener toRoomsPageFromBookingsPageListener = new NavButtonListener(this, bookingsPageNavBar, roomsPage);
        bookingsPageNavBar.addNavButtonListenerTo(toRoomsPageFromBookingsPageListener, bookingsPageNavBar.getNavButtons().roomsButton);
        NavButtonListener toRoomServicePageFromBookingsPageListener = new NavButtonListener(this, bookingsPageNavBar, roomServicePage);
        bookingsPageNavBar.addNavButtonListenerTo(toRoomServicePageFromBookingsPageListener, bookingsPageNavBar.getNavButtons().roomServiceButton);

        NavBar roomServicePageNavBar = roomServicePage.getNavBar();
        NavButtonListener toHomePageFromRoomServicePageListener = new NavButtonListener(this, roomServicePageNavBar, homePage);
        roomServicePageNavBar.addNavButtonListenerTo(toHomePageFromRoomServicePageListener, roomServicePageNavBar.getNavButtons().homeButton);
        NavButtonListener toRoomsPageFromRoomServicePageListener = new NavButtonListener(this, roomServicePageNavBar, roomsPage);
        roomServicePageNavBar.addNavButtonListenerTo(toRoomsPageFromRoomServicePageListener, roomServicePageNavBar.getNavButtons().roomsButton);
        NavButtonListener toBookingsPageFromRoomServicePageListener = new NavButtonListener(this, roomServicePageNavBar, bookingsPage);
        roomServicePageNavBar.addNavButtonListenerTo(toBookingsPageFromRoomServicePageListener, roomServicePageNavBar.getNavButtons().bookingsButton);

    }

    private void showPage(JPanel page){
        this.getContentPane().removeAll();
        this.getContentPane().add(page);
        this.revalidate();
        this.repaint();
    }
}
