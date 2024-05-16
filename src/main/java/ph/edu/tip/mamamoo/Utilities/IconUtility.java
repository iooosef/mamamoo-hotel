package ph.edu.tip.mamamoo.Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class IconUtility {
    static final Logger _logger = LogManager.getLogger();
    public static ImageIcon getIcon(String path, int width, int height) {
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

    public static ImageIcon getIcon(String path) {
        return getIcon(path, -1, -1);
    }

    public static JLabel getLogoLabel(JLabel label, String path, int width, int height) {
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
}
