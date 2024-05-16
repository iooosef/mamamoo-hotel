package ph.edu.tip.mamamoo.ActionListeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Components.NonRsvBookingsTable;
import ph.edu.tip.mamamoo.Components.PaymentsTable;
import ph.edu.tip.mamamoo.Components.RsvBookingsTable;
import ph.edu.tip.mamamoo.MultiPageApp;
import ph.edu.tip.mamamoo.Pages.BookingsPage;
import ph.edu.tip.mamamoo.Pages.HomePage;
import ph.edu.tip.mamamoo.Pages.PaymentsPage;
import ph.edu.tip.mamamoo.Pages.RoomsPage;
import ph.edu.tip.mamamoo.Pages.Shared.MainPanel;
import ph.edu.tip.mamamoo.Pages.Shared.NavBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavButtonListener implements ActionListener {
    private final Logger _logger = LogManager.getLogger();
    private MultiPageApp app;
    private NavBar navBar;
    private JPanel destinationPanel;
    private HomePage homePage = null;
    private RoomsPage roomsPage = null;
    private BookingsPage bookingsPage = null;
    private PaymentsPage paymentsPage = null;

    public NavButtonListener(MultiPageApp app, NavBar navBar, JPanel destinationPanel){
        this.initRoomsPage(app, navBar);
        this.destinationPanel = destinationPanel;
    }

    public NavButtonListener(MultiPageApp app, NavBar navBar, HomePage destination) {
        this.initRoomsPage(app, navBar);
        this.homePage = destination;
    }
//
//    public NavButtonListener(MultiPageApp app, NavBar navBar, RoomsPage destination) {
//        this.initRoomsPage(app, navBar);
//        this.roomsPage = destination;
//    }
//
    public NavButtonListener(MultiPageApp app, NavBar navBar, BookingsPage destination) {
        this.initRoomsPage(app, navBar);
        this.bookingsPage = destination;
    }
//
    public NavButtonListener(MultiPageApp app, NavBar navBar, PaymentsPage destination) {
        this.initRoomsPage(app, navBar);
        this.paymentsPage = destination;
    }

    private void initRoomsPage(MultiPageApp app, NavBar navBar) {
        this.app = app;
        this.navBar = navBar;
    }

    public void actionPerformed(ActionEvent e) {
        if (homePage != null) {
            this.redirectToPageGrow(homePage.mainPanel, new NonRsvBookingsTable("Booked-in Rooms Dashboard", app, homePage.mainPanel));
            redirectToPage(homePage);
        }
        else if (bookingsPage != null) {
            this.redirectToPageGrow(bookingsPage.mainPanel, new RsvBookingsTable("Reservations Dashboard", app, bookingsPage.mainPanel));
            redirectToPage(bookingsPage);
        }
        else if (paymentsPage != null) {
            this.redirectToPageGrow(paymentsPage.mainPanel, new PaymentsTable("Reservations Dashboard", app, paymentsPage.mainPanel));
            redirectToPage(paymentsPage);
        }
        else {
            _logger.info("Redirected to " + destinationPanel.getClass().getName() + " page");
            redirectToPage(destinationPanel);
        }
    }

    private MultiPageApp redirectToPage(JPanel panel) {
        app.getContentPane().removeAll();
        app.getContentPane().add(panel);
        app.revalidate();
        app.repaint();
        return app;
    }

    private MainPanel redirectToPageGrow(MainPanel mainPanel, JPanel content) {
        mainPanel.removeAll();
        mainPanel.add(content, "grow,w 100%");
        mainPanel.revalidate();
        mainPanel.repaint();
        return mainPanel;
    }
}
