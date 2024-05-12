package ph.edu.tip.mamamoo.ActionListeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.MultiPageApp;
import ph.edu.tip.mamamoo.Pages.Shared.NavBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavButtonListener implements ActionListener {
    private final Logger _logger = LogManager.getLogger();
    private MultiPageApp app;
    private NavBar navBar;
    private JPanel destinationPanel;

    public NavButtonListener(MultiPageApp app, NavBar navBar, JPanel destinationPanel){
        this.app = app;
        this.navBar = navBar;
        this.destinationPanel = destinationPanel;
    }

    public void actionPerformed(ActionEvent e) {
        _logger.info("Redirected to " + destinationPanel.getClass().getName() + " page");
        redirectToPage(destinationPanel);
    }

    private MultiPageApp redirectToPage(JPanel panel) {
        app.getContentPane().removeAll();
        app.getContentPane().add(panel);
        app.revalidate();
        app.repaint();
        return app;
    }
}
