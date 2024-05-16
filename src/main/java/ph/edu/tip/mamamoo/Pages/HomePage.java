package ph.edu.tip.mamamoo.Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Components.NonRsvBookingsTable;
import ph.edu.tip.mamamoo.MultiPageApp;
import ph.edu.tip.mamamoo.Pages.Shared.MainPanel;
import ph.edu.tip.mamamoo.Pages.Shared.NavBar;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {
    final Logger _logger = LogManager.getLogger();
    private MultiPageApp app;
    private NavBar navBar;
    public MainPanel mainPanel;
    public HomePage(MultiPageApp app){
        this.app = app;
        this.setLayout(new BorderLayout());
        navBar = new NavBar(app, this);
        this.add(navBar, BorderLayout.WEST);

        this.mainPanel = new MainPanel("Booked-in Rooms Dashboard", app);
        this.add(mainPanel, BorderLayout.CENTER);

        this.add(new NonRsvBookingsTable("Booked-in Rooms Dashboard", app, mainPanel));
    }

    public NavBar getNavBar() {
        return navBar;
    }
}
