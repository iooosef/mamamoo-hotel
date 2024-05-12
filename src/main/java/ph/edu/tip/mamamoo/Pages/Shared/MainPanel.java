package ph.edu.tip.mamamoo.Pages.Shared;

import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Components.NavButton;
import ph.edu.tip.mamamoo.Utilities.IconUtility;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    final Logger _logger = LogManager.getLogger();
    JPanel headerPanel;
    JLabel headerLabel;
    NavButton acctButton;
    public MainPanel(String headerText) {
        this.setLayout(new MigLayout("flowy, inset 0"));

        this.headerPanel = new JPanel();
        this.headerPanel.setLayout(new MigLayout("inset 0"));
        this.headerPanel.setBackground(new Color(247, 251, 253));
        this.add(headerPanel, "growx, w 100%");

        this.headerLabel = new JLabel(headerText);
        this.headerLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
        this.headerPanel.add(headerLabel, "w 100%, gap 10 0 10 10");

        this.acctButton = new NavButton(" Account",
                                        IconUtility.getIcon("./static/images/account_ico.png", 24, 24),
                                        new Color(239, 243, 245),
                                        new Color(247, 251, 253),
                                        Color.BLACK,
                                        Color.BLACK);
        this.acctButton.setMargin(new Insets(5,5,5,5));
        this.acctButton.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        this.headerPanel.add(acctButton, "gap 5 5 5 5");
    }
}
