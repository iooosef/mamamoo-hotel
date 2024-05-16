package ph.edu.tip.mamamoo.Components;

import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Data.BookingsData;
import ph.edu.tip.mamamoo.Dialogs.AccountControlsDialog;
import ph.edu.tip.mamamoo.Models.BookingsTableModel;
import ph.edu.tip.mamamoo.Models.PaymentTblModel;
import ph.edu.tip.mamamoo.MultiPageApp;
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
import java.util.ArrayList;

public class PaymentsTable extends JPanel {
    final Logger _logger = LogManager.getLogger();
    private MultiPageApp app;
    private MainPanel parentPanel;
    private JPanel headerPanel;
    private JLabel headerLabel;
    private String headerText;

    protected JButton button;
    private String label;
    private boolean isPushed;
    private Object rowId;
    private Object rowStatus;

    public PaymentsTable(String headerText, MultiPageApp app, MainPanel parentPanel) {
        this.app = app;
        this.parentPanel = parentPanel;
        this.headerText = headerText;
        this.addAncestorListener(new AncestorListener() {
            public void ancestorAdded(AncestorEvent event) {
                PaymentsTable.this.removeAll();
                PaymentsTable.this.init();
                PaymentsTable.this.revalidate();
                PaymentsTable.this.repaint();
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

        BookingsData bookingsData = new BookingsData();
        ArrayList<PaymentTblModel> payments = bookingsData.getAllPayments();

        String[] columnNames = {
                "ID",
                "Code",
                "Date-time",
                "Rated Cost",
                "VAT",
                "Discount",
                "Voucher",
                "Service Fee",
                "Amount Due",
                "Paid",
                "Change",
                "Refund",
                "Tip"
        };

        Object[][] data = new Object[payments.size()][columnNames.length];
        for (int i = 0; i < payments.size(); i++) {
            PaymentTblModel payment = payments.get(i);
            data[i][0] = payment.bkng_id;
            data[i][1] = payment.payment_code;
            data[i][2] = payment.payment_datetime;
            data[i][3] = payment.total_rated_cost;
            data[i][4] = payment.vat;
            data[i][5] = payment.senior_pwd_discount;
            data[i][6] = payment.voucher_discount;
            data[i][7] = payment.total_service_fee;
            data[i][8] = payment.total_amount_due;
            data[i][9] = payment.total_paid;
            data[i][10] = payment.total_change;
            data[i][11] = payment.total_refund;
            data[i][12] = payment.total_tip;
        }

        JTable table = new JTable(data, columnNames);
        table.setRowHeight(48);
        table.getColumnModel().getColumn(0).setPreferredWidth(3);
        table.getColumnModel().getColumn(7).setPreferredWidth(
                table.getColumnModel().getColumn(7).getWidth()-15
        );
        table.getColumnModel().getColumn(12).setPreferredWidth(
                table.getColumnModel().getColumn(12).getWidth()-15
        );
        for (int column = 0; column < table.getColumnCount(); column++) {
            table.getColumnModel().getColumn(column).setCellRenderer(new TextAreaRenderer());
            table.getColumnModel().getColumn(column).setCellEditor(new TextAreaEditor());
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, "grow,  h 100%");
    }
}
