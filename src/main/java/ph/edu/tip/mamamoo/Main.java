package ph.edu.tip.mamamoo;

import ph.edu.tip.mamamoo.Data.SqlServerInit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MultiPageApp().setVisible(true);
            }
        });
    }
}