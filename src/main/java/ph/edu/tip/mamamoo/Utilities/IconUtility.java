package ph.edu.tip.mamamoo.Utilities;

import javax.swing.*;
import java.awt.*;

public class IconUtility {
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
}
