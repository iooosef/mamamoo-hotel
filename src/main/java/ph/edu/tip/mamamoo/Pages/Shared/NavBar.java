package ph.edu.tip.mamamoo.Pages.Shared;

import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

        logoLabel = getLogoLabel(logoLabel,"./static/images/logo_sidebar.png", 160, -1);
        this.add(logoLabel);

        JLabel homeLabel = new JLabel("Home");
        homeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(homeLabel, "growx");
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
}
