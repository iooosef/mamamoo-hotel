package ph.edu.tip.mamamoo.Components;

import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Data.BookingsData;
import ph.edu.tip.mamamoo.Dialogs.AccountControlsDialog;
import ph.edu.tip.mamamoo.Dialogs.InvoiceDialog;
import ph.edu.tip.mamamoo.Models.*;
import ph.edu.tip.mamamoo.MultiPageApp;
import ph.edu.tip.mamamoo.Pages.BookingsPage;
import ph.edu.tip.mamamoo.Pages.HomePage;
import ph.edu.tip.mamamoo.Pages.RoomsPage;
import ph.edu.tip.mamamoo.Pages.Shared.MainPanel;
import ph.edu.tip.mamamoo.Utilities.IconUtility;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.ArrayList;

public class RsvBookingsTable extends JPanel {
    final Logger _logger = LogManager.getLogger();
    private MultiPageApp app;
    private MainPanel parentPanel;
    private JPanel headerPanel;
    private JLabel headerLabel;
    private String headerText;
    NavButton acctButton;

    protected JButton button;
    private String label;
    private boolean isPushed;
    private Object rowId;
    private Object rowStatus;

    public RsvBookingsTable(String headerText, MultiPageApp app, MainPanel parentPanel) {
        this.app = app;
        this.parentPanel = parentPanel;
        this.headerText = headerText;
        this.addAncestorListener(new AncestorListener() {
            public void ancestorAdded(AncestorEvent event) {
                RsvBookingsTable.this.removeAll();
                RsvBookingsTable.this.init();
                RsvBookingsTable.this.revalidate();
                RsvBookingsTable.this.repaint();
            }
            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }
            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });
        init();
    }

    private void init() {
        this.setLayout(new MigLayout("fillx, inset 0"));
        this.setLayout(new MigLayout("flowy, fillx, inset 0, gap 0 0"));

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

        this.acctButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountControlsDialog dialog = new AccountControlsDialog(app, app);
                dialog.setVisible(true);
            }
        });

        BookingsData bookingsData = new BookingsData();
        ArrayList<BookingsTableModel> bookings = bookingsData.getAllReservationBookings();

        String[] columnNames = {
                "Booking ID",
                "Room ID",
                "Booking Datetime",
                "Check-in Datetime",
                "Check-out Datetime",
                "Guest First Name",
                "Guest Last Name",
                "Contact Number",
                "Status",
                "Action"
        };

        Object[][] data = new Object[bookings.size()][columnNames.length];
        for (int i = 0; i < bookings.size(); i++) {
            BookingsTableModel booking = bookings.get(i);
            data[i][0] = booking.bkng_id;
            data[i][1] = booking.room_id;
            data[i][2] = booking.bkng_datetime;
            data[i][3] = booking.checkin;
            data[i][4] = booking.checkout;
            data[i][5] = booking.guest_fname;
            data[i][6] = booking.guest_lname;
            data[i][7] = booking.guest_contact;
            data[i][8] = booking.status;
            data[i][9] = "Action";
        }

        JTable table = new JTable(data, columnNames);
        table.setRowHeight(40);
        for (int column = 0; column < table.getColumnCount(); column++) {
            table.getColumnModel().getColumn(column).setCellRenderer(new TextAreaRenderer());
            table.getColumnModel().getColumn(column).setCellEditor(new TextAreaEditor());
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        table.getColumn("Action").setCellRenderer(new ButtonRenderer());
        table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));
        this.add(scrollPane, "grow, h 100%");
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Define the button editor
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private BookingsData bookingsData = new BookingsData();

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    System.out.println("Row data: " + rowId);
                    System.out.println("Row status: " + rowStatus);
                    this.showActionDialog((int) rowId, (String) rowStatus);
                }

                private void showActionDialog(int rowId, String rowStatus) {
                    if (rowStatus != null) {
                        // Open dialog
                        Dialog actionDialog = new Dialog(app);
                            actionDialog.setTitle("Booking No. " + rowId);
                            actionDialog.setLayout(new MigLayout("flowy, fillx"));
                            actionDialog.setResizable(false);

                        JLabel dialogTitle = new JLabel("Booking No. " + rowId);
                            dialogTitle.setFont(new Font("Sans Serif", Font.BOLD, 18));
                            actionDialog.add(dialogTitle, "growx, w 100%");

                        JButton checkOutPayButton = new JButton("Check-in");
                        checkOutPayButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Update booking status to checked-out
                                bookingsData.updBookingStatus(rowId, "checked-in");
                                _logger.info("Booking No. " + rowId + " has been checked-in");
                                actionDialog.dispose();
                                RsvBookingsTable.this.removeAll();
                                RsvBookingsTable.this.init();
                                RsvBookingsTable.this.revalidate();
                                RsvBookingsTable.this.repaint();
                            }
                        });
                        actionDialog.add(checkOutPayButton, "growx, w 100%, h 75%");

                        JButton cancelButton = new JButton();
                        cancelButton.setText("Cancel");
                        cancelButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                actionDialog.dispose();
                            }
                        });
                        actionDialog.add(cancelButton, "growx, w 100%, h 75%");

                        actionDialog.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent e) {
                                actionDialog.dispose();
                            }
                        });

                        actionDialog.pack();
                        actionDialog.setLocationRelativeTo(app);
                        actionDialog.setVisible(true);
                    } else {
                        // Do nothing
                    }
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            // Get row data
            rowId = table.getModel().getValueAt(row, 0);
            rowStatus = table.getModel().getValueAt(row, 8);
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                // Define what happens when the button is clicked
                System.out.println(label + ": Button clicked");
            }
            isPushed = false;
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
