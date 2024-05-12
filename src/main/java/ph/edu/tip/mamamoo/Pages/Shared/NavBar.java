package ph.edu.tip.mamamoo.Pages.Shared;

import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Components.ClockPanel;
import ph.edu.tip.mamamoo.Components.NavButton;
import ph.edu.tip.mamamoo.Utilities.IconUtility;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class NavBar extends JPanel {
    final Logger _logger = LogManager.getLogger();
    private JLabel logoLabel;
    private NavButtonsModel navButtons;

    public NavBar(MultiPageApp app, JPanel parentPanel) {
        this.app = app;
        this.parentPanel = parentPanel;

        this.setLayout(new MigLayout("flowy, gapy 10, insets 10, align center"));
        this.setPreferredSize(new Dimension(200, 768));
        this.setBackground(new Color(0, 120, 215));

        logoLabel = getLogoLabel(logoLabel,"./static/images/logo_sidebar.png", 175, -1);
        this.add(logoLabel);

        this.navButtons = new NavButtonsModel();
        this.navButtons.homeButton = new NavButton(" Home", IconUtility.getIcon("./static/images/home_ico.png", 24, 24));
        this.add(this.navButtons.homeButton, "growx");

        this.navButtons.roomsButton = new NavButton(" Rooms", IconUtility.getIcon("./static/images/rooms_ico.png", 24, 24));
        this.add(this.navButtons.roomsButton, "growx");

        this.navButtons.bookingsButton = new NavButton(" Bookings", IconUtility.getIcon("./static/images/bookings_ico.png", 24, 24));
        this.add(this.navButtons.bookingsButton, "growx");

        this.navButtons.roomServiceButton = new NavButton(" Room Service", IconUtility.getIcon("./static/images/room_service_ico.png", 24, 24));
        this.add(this.navButtons.roomServiceButton, "growx");

        ClockPanel clock = new ClockPanel();
        JPanel alignYEnd = new JPanel();
        alignYEnd.setLayout(new BoxLayout(alignYEnd, BoxLayout.Y_AXIS));
        alignYEnd.setBackground(null);
        alignYEnd.add(Box.createVerticalGlue());
        alignYEnd.add(clock, BorderLayout.SOUTH);
        this.add(alignYEnd, "growx, h 100%");
    }

    private JLabel getLogoLabel(JLabel label, String path, int width, int height) {
        try {
            BufferedImage logoImg = ImageIO.read(new File(path));
            Image smLogoImg = logoImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            label = new JLabel(new ImageIcon(smLogoImg));
        } catch (Exception e) {
            _logger.error("Error loading logo");
            label = new JLabel("LOGO HERE");
        }
        return label;
    }

    public void addNavButtonListenerTo(NavButtonListener listener, NavButton selectedBtn) {
        selectedBtn.addActionListener(listener);
    }

    public NavButtonsModel getNavButtons() {
        return navButtons;
    }
}
