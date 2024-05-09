package ph.edu.tip.mamamoo.Pages.Shared;

import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Components.ClockPanel;
import ph.edu.tip.mamamoo.Components.NavButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class NavBar extends JPanel {
    final Logger _logger = LogManager.getLogger();
    private JLabel logoLabel;
    public NavBar() {
        this.setLayout(new MigLayout("flowy, insets 15"));
        this.setPreferredSize(new Dimension(200, 768));
        this.setBackground(new Color(0, 120, 215));

        logoLabel = getLogoLabel(logoLabel,"./static/images/logo_sidebar.png", 175, -1);
        this.add(logoLabel);

        NavButton homeNavBtn = new NavButton(" Home", getIcon("./static/images/home_ico.png", 24, 24));
        homeNavBtn.setDefaultBgColor(new Color(4, 58, 78));
        this.add(homeNavBtn, "growx");

        NavButton roomsNavBtn = new NavButton(" Rooms", getIcon("./static/images/rooms_ico.png", 24, 24));
        this.add(roomsNavBtn, "growx");

        NavButton bookingsNavBtn = new NavButton(" Bookings", getIcon("./static/images/bookings_ico.png", 24, 24));
        this.add(bookingsNavBtn, "growx");

        NavButton roomServiceNavBtn = new NavButton(" Room Service", getIcon("./static/images/room_service_ico.png", 24, 24));
        this.add(roomServiceNavBtn, "growx");

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
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }
    private ImageIcon getIcon(String path, int width, int height) {
        try {
            ImageIcon logoIcon = new ImageIcon(path);
            Image logoImg = logoIcon.getImage();
            Image smLogoImg = logoImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(smLogoImg);
        } catch (Exception e) {
            e.printStackTrace();
            return new ImageIcon();
        }
    }
    private ImageIcon getIcon(String path) {
        return getIcon(path, -1, -1);
    }
}
