package ph.edu.tip.mamamoo.Dialogs;

import net.miginfocom.swing.MigLayout;
import ph.edu.tip.mamamoo.Components.NavButton;
import ph.edu.tip.mamamoo.MultiPageApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AccountControlsDialog extends Dialog {
    MultiPageApp app;
    public AccountControlsDialog(Frame owner, MultiPageApp app) {
        super(owner, "Account", true);
        this.app = app;
        this.setSize(200, 180);
        this.setLayout(new MigLayout("flowy, fill, debug"));
        this.setLocationRelativeTo(owner);
        this.setResizable(false);

        NavButton logoutButton = new NavButton("Logout");
        logoutButton.setHorizontalAlignment(SwingConstants.CENTER);
        logoutButton.addActionListener(e -> {
            app.getContentPane().removeAll();
            app.getContentPane().add(app.getLoginPage());
            app.revalidate();
            app.repaint();
            dispose();
        });
        this.add(logoutButton, "growx");

        NavButton closeButton = new NavButton("Close");
        closeButton.setHorizontalAlignment(SwingConstants.CENTER);
        closeButton.addActionListener(e -> dispose());
        this.add(closeButton, "growx");


        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
