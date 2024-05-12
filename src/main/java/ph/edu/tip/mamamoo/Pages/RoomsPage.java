package ph.edu.tip.mamamoo.Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Pages.Shared.MainPanel;
import ph.edu.tip.mamamoo.Pages.Shared.NavBar;

import javax.swing.*;
import java.awt.*;

public class RoomsPage extends JPanel {
    final Logger _logger = LogManager.getLogger();
    private NavBar navBar;
    private MainPanel mainPanel;
    public RoomsPage(){
        this.setLayout(new BorderLayout());
        navBar = new NavBar();
        this.add(navBar, BorderLayout.WEST);

        this.mainPanel = new MainPanel("Book a Room");
        this.add(mainPanel, BorderLayout.CENTER);

    }
}
