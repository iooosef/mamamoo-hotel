package ph.edu.tip.mamamoo.Components;

import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Data.BookingsData;
import ph.edu.tip.mamamoo.Dialogs.AccountControlsDialog;
import ph.edu.tip.mamamoo.Dialogs.InvoiceDialog;
import ph.edu.tip.mamamoo.Models.*;
import ph.edu.tip.mamamoo.MultiPageApp;
import ph.edu.tip.mamamoo.Pages.HomePage;
import ph.edu.tip.mamamoo.Pages.Shared.MainPanel;
import ph.edu.tip.mamamoo.Utilities.IconUtility;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;

public class NonRsvBookingsTable extends JPanel {
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
    private Object rowNum;
    private Object rowStatus;

    public NonRsvBookingsTable(String headerText, MultiPageApp app, MainPanel parentPanel) {
        this.app = app;
        this.parentPanel = parentPanel;
        this.headerText = headerText;
        this.addAncestorListener(new AncestorListener() {
            public void ancestorAdded(AncestorEvent event) {
                NonRsvBookingsTable.this.removeAll();
                NonRsvBookingsTable.this.init();
                NonRsvBookingsTable.this.revalidate();
                NonRsvBookingsTable.this.repaint();
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
        this.setBorder(null);
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
        ArrayList<BookingsTableModel> bookings = bookingsData.getAllNonReservationBookings();

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
                        int height = 240;
                        if(rowStatus.equals("paid") || rowStatus.equals("cancelled")) {
                            height = 160;
                        }

                        // Open dialog
                        Dialog actionDialog = new Dialog(app);
                            actionDialog.setTitle("Booking No. " + rowId);
                            actionDialog.setLayout(new MigLayout("flowy, fillx"));
                            actionDialog.setResizable(false);
                            actionDialog.setSize(400, height);
                            actionDialog.setLocationRelativeTo(app);

                        JLabel dialogTitle = new JLabel("Booking No. " + rowId);
                            dialogTitle.setFont(new Font("Sans Serif", Font.BOLD, 18));
                            actionDialog.add(dialogTitle, "growx, w 100%");

                        if(rowStatus.equals("paid") || rowStatus.equals("cancelled")) {
                            JLabel noActionsLabel = new JLabel("No actions available for this booking.");
                            actionDialog.add(noActionsLabel, "growx, dock center, w 100%");
                        }

                        if(!rowStatus.equals("paid") && !rowStatus.equals("cancelled") && !rowStatus.equals("checked-in_paid")) {
                            JButton checkOutPayButton = new JButton("Check-out & Pay");
                            checkOutPayButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // Update booking status to checked-out
                                    showCheckoutNPayDialog(rowId);
                                    _logger.info("Booking No. " + rowId + " has been checked-out");
                                    actionDialog.dispose();
                                }
                            });
                            actionDialog.add(checkOutPayButton, "growx, w 100%, h 75%");
                        }

                        if(!rowStatus.equals("paid") && !rowStatus.equals("cancelled") && !rowStatus.equals("checked-in")) {
                            JButton checkOutPaidButton = new JButton("Check-out & Pay Other Costs");
                            checkOutPaidButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // Update booking status to checked-out
                                    showCheckoutAndOtherCostDialog(rowId);
                                    _logger.info("Booking No. " + rowId + " has been checked-out");
                                    actionDialog.dispose();
                                }
                            });
                            actionDialog.add(checkOutPaidButton, "growx, w 100%, h 75%");
                        }

                        if (!rowStatus.equals("paid") && !rowStatus.equals("cancelled")) {
                            JButton addServiceTransactionButton = new JButton("Add Service Transaction");
                            addServiceTransactionButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // Open service transaction dialog
                                    _logger.info("Service transaction dialog opened");
                                    actionDialog.dispose();

                                    Dialog serviceTransactionDialog = new Dialog(app);
                                    serviceTransactionDialog.setTitle("Add Service Transaction");
                                    serviceTransactionDialog.setLayout(new MigLayout("flowy, fillx, debug", "[grow]", "[]10[]"));
                                    serviceTransactionDialog.setResizable(false);

                                    JLabel dialogTitle = new JLabel("Add Service Transaction to Booking No. " + rowId);
                                    dialogTitle.setFont(new Font("Sans Serif", Font.BOLD, 18));
                                    serviceTransactionDialog.add(dialogTitle, "growx, w 100%");

                                    JPanel detailsLeftValueRightPanel = new JPanel();
                                    detailsLeftValueRightPanel.setLayout(new MigLayout("fillx, inset 0, debug"));
                                    serviceTransactionDialog.add(detailsLeftValueRightPanel, "growx, w 100%");
                                    JLabel serviceLabel = new JLabel("Service: ");
                                    detailsLeftValueRightPanel.add(serviceLabel, "growx");
                                    JTextField serviceTextField = new JTextField();
                                    detailsLeftValueRightPanel.add(serviceTextField, "growx, wrap");

                                    JLabel costLabel = new JLabel("Cost: ");
                                    detailsLeftValueRightPanel.add(costLabel, "growx");
                                    JTextField costTextField = new JTextField();
                                    detailsLeftValueRightPanel.add(costTextField, "growx, wrap");
                                    JButton addServiceTransactionButton = new JButton("Add");
                                    addServiceTransactionButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            // Insert service fee
                                            ServiceFeeModel serviceFeeModel = new ServiceFeeModel();
                                            serviceFeeModel.bkng_id = rowId;
                                            serviceFeeModel.service_name = serviceTextField.getText();
                                            serviceFeeModel.cost = new BigDecimal(costTextField.getText());
//                                            bookingsData.insServiceFee(serviceFeeModel);
                                            _logger.info("Service transaction added to Booking No. " + rowId);
                                            serviceTransactionDialog.dispose();
                                        }
                                    });
                                    detailsLeftValueRightPanel.add(addServiceTransactionButton, "growx");

                                    JButton cancelButton = new JButton();
                                    cancelButton.setText("Cancel");
                                    cancelButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            serviceTransactionDialog.dispose();
                                        }
                                    });
                                    detailsLeftValueRightPanel.add(cancelButton, "growx, wrap");

                                    serviceTransactionDialog.pack();
                                    serviceTransactionDialog.setLocationRelativeTo(app);
                                    serviceTransactionDialog.setVisible(true);
                                    serviceTransactionDialog.addWindowListener(new WindowAdapter() {
                                        public void windowClosing(WindowEvent e) {
                                            serviceTransactionDialog.dispose();
                                        }
                                    });
                                }
                            });
                            actionDialog.add(addServiceTransactionButton, "growx, w 100%, h 75%");
                        }

                        JButton cancelButton = new JButton();
                        if(rowStatus.equals("paid") || rowStatus.equals("cancelled")) {
                            cancelButton.setText("Close");
                        } else {
                            cancelButton.setText("Cancel");
                        }
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

                        actionDialog.setVisible(true);
                    } else {
                        // Do nothing
                    }
                }

                private void showCheckoutNPayDialog(int rowId) {
                    PaymentModel paymentModel = bookingsData.getTotalRatedCost(rowId);
                    InvoiceModel invoiceModel = new InvoiceModel();
                    ArrayList<ServiceFeeModel> serviceFees = bookingsData.getAllServiceFeesOfBooking(rowId);
                    ServiceFeeTotalModel serviceFeeTotalModel = bookingsData.getTotalServiceFeesOfBooking(rowId);
                    serviceFeeTotalModel.total = (serviceFeeTotalModel.total == null ? BigDecimal.ZERO : serviceFeeTotalModel.total);

                    Dialog payFormDialog = new Dialog(app);
                    payFormDialog.setTitle("Check-out & Pay");
                    payFormDialog.setLayout(new MigLayout("flowy, fillx, debug", "[grow]", "[]10[]"));
                    payFormDialog.setResizable(false);

                    JLabel dialogTitle = new JLabel("Check-out & Pay Booking No. " + rowId);
                        dialogTitle.setFont(new Font("Sans Serif", Font.BOLD, 18));
                        payFormDialog.add(dialogTitle, "growx, w 100%");
                    JLabel roomNumberLabel = new JLabel("Room No.  " + paymentModel.room_num);
                        payFormDialog.add(roomNumberLabel, "growx, w 100%");

                    JLabel paymentDetailsTitleLabel = new JLabel("Payment Details");
                        paymentDetailsTitleLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
                        payFormDialog.add(paymentDetailsTitleLabel, "growx, w 100%");
                    JPanel detailsLeftValueRightPanel = new JPanel();
                        detailsLeftValueRightPanel.setLayout(new MigLayout("fillx, inset 0"));
                        payFormDialog.add(detailsLeftValueRightPanel, "growx, w 100%");
                        JLabel subtotalLabel = new JLabel("Subtotal: ");
                            detailsLeftValueRightPanel.add(subtotalLabel, "growx");
                        JLabel roomRateTotalValueLabel = new JLabel("\u20B1  " + paymentModel.total_rated_amount);
                            detailsLeftValueRightPanel.add(roomRateTotalValueLabel, "growx, wrap");
                        JLabel vatLabel = new JLabel("Value-added Tax (12%): ");
                            detailsLeftValueRightPanel.add(vatLabel, "growx");
                        JLabel vatValueLabel = new JLabel("\u20B1  " + paymentModel.vat);
                            detailsLeftValueRightPanel.add(vatValueLabel, "growx, wrap");
                        JLabel seniorPwdDiscountLabel = new JLabel("Senior/PWD Discount (-20%): ");
                        detailsLeftValueRightPanel.add(seniorPwdDiscountLabel, "growx");
                        JLabel seniorPwdDiscountValueLabel = new JLabel("\u20B1  " + paymentModel.senior_pwd_discount);
                        detailsLeftValueRightPanel.add(seniorPwdDiscountValueLabel, "growx, wrap");
                        JLabel voucherDiscountLabel = new JLabel("Voucher Discount (-5%): ");
                        detailsLeftValueRightPanel.add(voucherDiscountLabel, "growx");
                        JLabel voucherDiscountValueLabel = new JLabel("\u20B1  " + paymentModel.voucher_discount);
                        detailsLeftValueRightPanel.add(voucherDiscountValueLabel, "growx, wrap");
                        serviceFees.forEach(service -> {
                            JLabel serviceFeeLabel = new JLabel(service.service_name + ": ");
                            detailsLeftValueRightPanel.add(serviceFeeLabel, "growx");
                            JLabel serviceFeeValueLabel = new JLabel("\u20B1  " + service.cost);
                            detailsLeftValueRightPanel.add(serviceFeeValueLabel, "growx, wrap");
                        });

                    JSeparator separator = new JSeparator();
                        payFormDialog.add(separator, "growx, w 100%");
                    JPanel totalPanel = new JPanel();
                        totalPanel.setLayout(new MigLayout("fillx, inset 0, debug"));
                        payFormDialog.add(totalPanel, "growx, w 100%");
                        JLabel serviceFeesTotalLabel = new JLabel("Service Fees Total Cost: ");
                            totalPanel.add(serviceFeesTotalLabel, "growx");
                        JLabel serviceFeesTotalValueLabel = new JLabel("\u20B1  " + serviceFeeTotalModel.total);
                            totalPanel.add(serviceFeesTotalValueLabel, "growx, wrap");

                        JLabel totalLabel = new JLabel("Total Amount Due: ");
                            totalPanel.add(totalLabel, "growx");

                        BigDecimal total =  paymentModel.total_rated_amount.add(paymentModel.vat);
                            total = total.add(paymentModel.senior_pwd_discount);
                            total = total.add(paymentModel.voucher_discount);
                            total = total.add(serviceFeeTotalModel.total);
                        JLabel totalValueLabel = new JLabel("\u20B1  " + total);
                            totalPanel.add(totalValueLabel, "growx, wrap");
                        JLabel enterAmountPaidLabel = new JLabel("Enter Amount Paid: ");
                            totalPanel.add(enterAmountPaidLabel, "growx");
                        JTextField amountPaidTextField = new JTextField();
                            totalPanel.add(amountPaidTextField, "growx, wrap");
                        JLabel enterTipLabel = new JLabel("Enter Tip: ");
                            totalPanel.add(enterTipLabel, "growx");
                        JTextField tipTextField = new JTextField();
                            totalPanel.add(tipTextField, "growx, wrap");

                    JButton payButton = new JButton("Pay");
                    BigDecimal finalTotal = total;
                    payButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Insert Into Payment
                                invoiceModel.bkn_id = paymentModel.bkng_id;
                                invoiceModel.check_out = paymentModel.check_out;
                                invoiceModel.total_rated_cost = paymentModel.total_rated_amount;
                                invoiceModel.vat = paymentModel.vat;
                                invoiceModel.senior_pwd_discount = paymentModel.senior_pwd_discount;
                                invoiceModel.voucher_discount = paymentModel.voucher_discount;
                                invoiceModel.total_service_fee = serviceFeeTotalModel.total;
                                invoiceModel.total_amount_due = finalTotal;
                                invoiceModel.total_amount_paid = new BigDecimal(amountPaidTextField.getText().length() <= 0 ? "0.00" : amountPaidTextField.getText());
                                invoiceModel.total_tip = new BigDecimal(tipTextField.getText().length() <= 0 ? "0.00" : tipTextField.getText());
                                if (invoiceModel.total_amount_paid.compareTo(invoiceModel.total_amount_due) < 0) {
                                    JOptionPane.showMessageDialog(app, "Amount paid is less than the total amount due.", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    bookingsData.insPayment(invoiceModel);
                                    bookingsData.updBookingStatus(rowId, "paid");
                                    _logger.info("Booking No. " + rowId + " has been paid");
                                    payFormDialog.dispose();
                                    InvoiceDialog dialog = new InvoiceDialog(app, invoiceModel.bkn_id);
                                    dialog.addWindowListener(new WindowAdapter() {
                                        public void windowClosed(WindowEvent e) {
                                            refreshPage();

                                            _logger.info("Invoice dialog closed");
                                        }
                                    });
                                }
                            }
                        });
                        payFormDialog.add(payButton, "growx, w 100%, h 75%");

                    JButton cancelButton = new JButton("Cancel");
                        cancelButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                payFormDialog.dispose();
                            }
                        });
                        payFormDialog.add(cancelButton, "growx, w 100%, h 75%");

                    payFormDialog.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            payFormDialog.dispose();
                        }
                    });
                    payFormDialog.pack();
                    payFormDialog.setLocationRelativeTo(app);
                    payFormDialog.setVisible(true);
                }

                private void showCheckoutAndOtherCostDialog(int rowId) {
                    ArrayList<ServiceFeeModel> serviceFees = bookingsData.getAllServiceFeesOfBooking(rowId);
                    PaymentModel paymentModel = bookingsData.getTotalRatedCost(rowId);
                    ServiceFeeTotalModel model = bookingsData.getTotalServiceFeesOfBooking(rowId);
                    model.total = model.total == null ? BigDecimal.ZERO : model.total;

                    Dialog payFormDialog = new Dialog(app);
                    payFormDialog.setTitle("Check-out & Pay Other Fees");
                    payFormDialog.setLayout(new MigLayout("flowy, fillx", "[grow]", "[]10[]"));
                    payFormDialog.setResizable(false);

                    JLabel dialogTitle = new JLabel("Check-out & Pay Other Fees of Booking No. " + rowId);
                    dialogTitle.setFont(new Font("Sans Serif", Font.BOLD, 18));
                    payFormDialog.add(dialogTitle, "growx, w 100%");
                    JLabel roomNumberLabel = new JLabel("Room No.  " + paymentModel.room_num);
                    payFormDialog.add(roomNumberLabel, "growx, w 100%");

                    JLabel paymentDetailsTitleLabel = new JLabel("Payment Details");
                        paymentDetailsTitleLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
                        payFormDialog.add(paymentDetailsTitleLabel, "growx, w 100%");
                    JPanel detailsLeftValueRightPanel = new JPanel();
                    detailsLeftValueRightPanel.setLayout(new MigLayout("fillx, inset 0"));
                    payFormDialog.add(detailsLeftValueRightPanel, "growx, w 100%");
                    serviceFees.forEach(service -> {
                        JLabel serviceFeeLabel = new JLabel(service.service_name + ": ");
                        detailsLeftValueRightPanel.add(serviceFeeLabel, "growx");
                        JLabel serviceFeeValueLabel = new JLabel("\u20B1  " + service.cost);
                        detailsLeftValueRightPanel.add(serviceFeeValueLabel, "growx, wrap");
                    });

                    JSeparator separator = new JSeparator();
                    payFormDialog.add(separator, "growx, w 100%");

                    JPanel totalPanel = new JPanel();
                        totalPanel.setLayout(new MigLayout("fillx, inset 0, debug"));
                        payFormDialog.add(totalPanel, "growx, w 100%");
                            JLabel totalLabel = new JLabel("Service Fees Total Cost: ");
                                totalPanel.add(totalLabel, "growx");
                            JLabel totalValueLabel = new JLabel("\u20B1  " + model.total);
                                totalPanel.add(totalValueLabel, "growx, wrap");
                            JLabel enterAmountPaidLabel = new JLabel("Enter Amount Paid: ");
                                totalPanel.add(enterAmountPaidLabel, "growx");
                            JTextField amountPaidTextField = new JTextField();
                                totalPanel.add(amountPaidTextField, "growx, wrap");
                            JLabel enterTipLabel = new JLabel("Enter Tip: ");
                                totalPanel.add(enterTipLabel, "growx");
                            JTextField tipTextField = new JTextField();
                                totalPanel.add(tipTextField, "growx, wrap");

                    JButton payButton = new JButton("Pay");
                    payButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Update Payment
                            InvoiceModel invoiceModel = new InvoiceModel();
                            invoiceModel = bookingsData.getPaymentOfBooking(rowId);
                            invoiceModel.total_service_fee = invoiceModel.total_service_fee == null ? BigDecimal.ZERO : invoiceModel.total_service_fee;
                            invoiceModel.total_amount_paid = invoiceModel.total_amount_paid == null ? BigDecimal.ZERO : invoiceModel.total_amount_paid;
                            invoiceModel.bkn_id = paymentModel.bkng_id;
                            invoiceModel.total_service_fee = invoiceModel.total_service_fee.add(model.total);
                            invoiceModel.total_amount_paid = invoiceModel.total_amount_paid.add(new BigDecimal(amountPaidTextField.getText().length() <= 0 ? "0.00" : amountPaidTextField.getText()));
                            invoiceModel.total_tip = new BigDecimal(tipTextField.getText().length() <= 0 ? "0.00" : tipTextField.getText());

                            BigDecimal total_amount_paid_new = new BigDecimal(amountPaidTextField.getText().length() <= 0 ? "0.00" : amountPaidTextField.getText());
                            _logger.info("Total amount paid: " + total_amount_paid_new);
                            _logger.info("Total service fee: " + invoiceModel.total_service_fee);
                            if(total_amount_paid_new.compareTo(invoiceModel.total_service_fee) < 0) {
                                JOptionPane.showMessageDialog(app, "Amount paid is less than the total amount due.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                bookingsData.updPayment(invoiceModel.bkn_id, invoiceModel.total_service_fee, total_amount_paid_new);
                                bookingsData.updBookingStatus(rowId, "paid");
                                _logger.info("Booking No. " + rowId + " has been paid");
                                payFormDialog.dispose();
                                InvoiceDialog dialog = new InvoiceDialog(app, invoiceModel.bkn_id);
                                dialog.addWindowListener(new WindowAdapter() {
                                    public void windowClosed(WindowEvent e) {
                                        refreshPage();
                                        _logger.info("Invoice dialog closed");
                                    }
                                });
                            }
                        }
                    });
                    payFormDialog.add(payButton, "growx, w 100%, h 75%");

                    JButton cancelButton = new JButton("Cancel");
                    cancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            payFormDialog.dispose();
                        }
                    });
                    payFormDialog.add(cancelButton, "growx, w 100%, h 75%");

                    payFormDialog.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            payFormDialog.dispose();
                        }
                    });
                    payFormDialog.pack();
                    payFormDialog.setLocationRelativeTo(app);
                    payFormDialog.setVisible(true);
                }

                private void refreshPage() {
                    NonRsvBookingsTable.this.removeAll();
                    NonRsvBookingsTable.this.init();
                    NonRsvBookingsTable.this.revalidate();
                    NonRsvBookingsTable.this.repaint();
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
