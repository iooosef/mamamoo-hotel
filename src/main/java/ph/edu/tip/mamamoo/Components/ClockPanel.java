package ph.edu.tip.mamamoo.Components;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ClockPanel extends JPanel implements Runnable {
    private JLabel timeLabel;
    private JLabel dateLabel;
    private SimpleDateFormat timeFormat;
    private SimpleDateFormat dateFormat;
    public ClockPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(null);

        // time
        this.timeLabel = new JLabel();
        this.timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.timeLabel.setForeground(Color.WHITE);
        this.timeLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(timeLabel);
        this.timeFormat = new SimpleDateFormat("hh:mm:ss aa"); // Create time format

        // date
        this.dateLabel = new JLabel();
        this.dateLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.dateLabel.setForeground(Color.WHITE);
        this.dateLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(dateLabel);
        this.dateFormat = new SimpleDateFormat("LLLL dd, YYYY"); // Create date format

        // Start a thread to update date and time
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        // Update date and time every second
        while (true) {
            try {
                // Get current date and time
                Date now = new Date();
                // Format date and time
                String time = timeFormat.format(now);
                String date = dateFormat.format(now);
                // Set the formatted date and time to the label
                SwingUtilities.invokeLater(() -> {
                    timeLabel.setText(time);
                    dateLabel.setText(date);
                });
                // Wait for 1 second
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
