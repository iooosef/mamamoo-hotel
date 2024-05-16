package ph.edu.tip.mamamoo.Pages.Shared;

import javax.swing.*;

public class CurrentTimeTestPanel extends JPanel {
    public CurrentTimeTestPanel() {
        JLabel currentTimeLabel = new JLabel("Current Time: " + java.time.LocalDateTime.now().toString());
        this.add(currentTimeLabel, "grow");
    }
}
