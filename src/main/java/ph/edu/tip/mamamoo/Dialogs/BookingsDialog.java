package ph.edu.tip.mamamoo.Dialogs;

import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Data.BookingsData;
import ph.edu.tip.mamamoo.Models.BookingsTableModel;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BookingsDialog extends Dialog {
    final Logger _logger = LogManager.getLogger();
    private BookingsTableModel model;
    private BookingsData data;

    public BookingsDialog(Frame owner, int bkngID) {
        super(owner);
        this.data = new BookingsData();
        this.model = data.getNonReservationBookingOf(bkngID);
        this.setTitle("Booking No. " + model.bkng_id);
        this.setSize(400, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(owner);
        this.setLayout(new MigLayout("fillx, filly"));



        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
