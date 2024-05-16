package ph.edu.tip.mamamoo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Data.SqlServerInit;
import ph.edu.tip.mamamoo.Utilities.DotEnvUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        final Logger _logger = LogManager.getLogger();
        DotEnvUtility dotenv = DotEnvUtility.configure().load();
        System.setProperty("sun.java2d.uiScale", "1.0"); // to fix low dpi scaling of images

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MultiPageApp().setVisible(true);
            }
        });
    }
}