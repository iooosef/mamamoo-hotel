package ph.edu.tip.mamamoo.Pages;

import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Pages.Shared.NavBar;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {
    final Logger _logger = LogManager.getLogger();
    private NavBar navBar;
    public HomePage(){
        this.setLayout(new BorderLayout());
        navBar = new NavBar();
        this.add(navBar, BorderLayout.WEST);
    }
}
