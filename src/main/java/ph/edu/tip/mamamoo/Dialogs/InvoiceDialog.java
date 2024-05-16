package ph.edu.tip.mamamoo.Dialogs;

import net.miginfocom.swing.MigLayout;
import ph.edu.tip.mamamoo.Data.BookingsData;
import ph.edu.tip.mamamoo.Models.InvoiceModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InvoiceDialog extends Dialog {
    private InvoiceModel model;
    private BookingsData bookingsData;
    public InvoiceDialog(Frame owner, int bkng_id) {
        super(owner);
        this.bookingsData = new BookingsData();
        this.model = bookingsData.getPaymentOfBooking(bkng_id);
        this.setTitle("Invoice");
        this.setLayout(new MigLayout("flowy, fillx", "[grow]", "[]10[]"));
        this.setResizable(false);

        JLabel dialogTitle = new JLabel("Invoice for Booking No. " + model.bkn_id);
        dialogTitle.setFont(new Font("Sans Serif", Font.BOLD, 18));
        this.add(dialogTitle, "growx, w 100%");
        JLabel roomNumberLabel = new JLabel("Room No.  " + model.room_num);
        this.add(roomNumberLabel, "growx, w 100%");


        JLabel paymentDetailsTitleLabel = new JLabel("Payment Details");
            paymentDetailsTitleLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
            this.add(paymentDetailsTitleLabel, "growx, w 100%");
        JPanel detailsLeftValueRightPanel = new JPanel();
            detailsLeftValueRightPanel.setLayout(new MigLayout("fillx, inset 0"));
            this.add(detailsLeftValueRightPanel, "growx, w 100%");
            JLabel guestNameLabel = new JLabel("Guest Name: "); // -----------------------------------
                detailsLeftValueRightPanel.add(guestNameLabel, "growx");
            JLabel guestNameValueLabel = new JLabel(model.guest_name);
                detailsLeftValueRightPanel.add(guestNameValueLabel, "growx, wrap");
            JLabel checkInLabel = new JLabel("Check-in: "); // -----------------------------------
                detailsLeftValueRightPanel.add(checkInLabel, "growx");
            JLabel checkInValueLabel = new JLabel(model.check_in.toString());
                detailsLeftValueRightPanel.add(checkInValueLabel, "growx, wrap");
            JLabel checkOutLabel = new JLabel("Check-out: "); // -----------------------------------
                detailsLeftValueRightPanel.add(checkOutLabel, "growx");
            JLabel checkOutValueLabel = new JLabel(model.check_in.toString());
                detailsLeftValueRightPanel.add(checkOutValueLabel, "growx, wrap");
            JLabel paymentDateTimeLabel = new JLabel("Payment Date-time: "); // -----------------------------------
                detailsLeftValueRightPanel.add(paymentDateTimeLabel, "growx");
            JLabel paymentDateTimeValueLabel = new JLabel(model.payment_datetime.toString());
                detailsLeftValueRightPanel.add(paymentDateTimeValueLabel, "growx, wrap");
            JLabel paymentCodeLabel = new JLabel("Payment Code: "); // -----------------------------------
                detailsLeftValueRightPanel.add(paymentCodeLabel, "growx");
            JLabel paymentCodeValueLabel = new JLabel(model.payment_code);
                detailsLeftValueRightPanel.add(paymentCodeValueLabel, "growx, wrap");
            JLabel reservationLabel = new JLabel("Reservation Fee (Paid): "); // -----------------------------------
                detailsLeftValueRightPanel.add(reservationLabel, "growx");
            JLabel reservationValueLabel = new JLabel("\u20B1  " + model.reservation_fee);
                detailsLeftValueRightPanel.add(reservationValueLabel, "growx, wrap");
            JLabel totalRatedCostLabel = new JLabel("Sub-total: "); // -----------------------------------
                detailsLeftValueRightPanel.add(totalRatedCostLabel, "growx");
            JLabel totalRatedCostValueLabel = new JLabel("\u20B1  " + (model.total_rated_cost == null ? "0.00" : model.total_rated_cost));
                detailsLeftValueRightPanel.add(totalRatedCostValueLabel, "growx, wrap");
            JLabel vatLabel = new JLabel("Value-added Tax (12%): "); // -----------------------------------
                detailsLeftValueRightPanel.add(vatLabel, "growx");
            JLabel vatValueLabel = new JLabel("\u20B1  " + model.vat);
                detailsLeftValueRightPanel.add(vatValueLabel, "growx, wrap");
            JLabel seniorPwdDiscountLabel = new JLabel("Senior/PWD Discount (-20%): "); // -----------------------------------
                detailsLeftValueRightPanel.add(seniorPwdDiscountLabel, "growx");
            JLabel seniorPwdDiscountValueLabel = new JLabel("\u20B1  " + (model.senior_pwd_discount == null ? "0.00" : model.senior_pwd_discount));
                detailsLeftValueRightPanel.add(seniorPwdDiscountValueLabel, "growx, wrap");
            JLabel voucherDiscountLabel = new JLabel("Voucher Discount (-5%): "); // -----------------------------------
                detailsLeftValueRightPanel.add(voucherDiscountLabel, "growx");
            JLabel voucherDiscountValueLabel = new JLabel("\u20B1  " + (model.voucher_discount == null ? "0.00" : model.voucher_discount));
                detailsLeftValueRightPanel.add(voucherDiscountValueLabel, "growx, wrap");
            JLabel totalServiceFeesLabel = new JLabel("Total Service Fee: "); // -----------------------------------
                detailsLeftValueRightPanel.add(totalServiceFeesLabel, "growx");
            JLabel totalServiceFeesValueLabel = new JLabel("\u20B1  " + (model.total_service_fee == null ? "0.00" : model.total_service_fee));
                detailsLeftValueRightPanel.add(totalServiceFeesValueLabel, "growx, wrap");

            JSeparator separator = new JSeparator();
                this.add(separator, "growx, w 100%");
            JPanel totalPanel = new JPanel();
                totalPanel.setLayout(new MigLayout("fillx, inset 0"));
                this.add(totalPanel, "growx, w 100%");
                JLabel totalAmountDueLabel = new JLabel("Total Amount Due: "); // -----------------------------------
                    totalPanel.add(totalAmountDueLabel, "growx");
                JLabel totalAmountDueValueLabel = new JLabel("\u20B1  " + (model.total_amount_due == null ? "0.00" : model.total_amount_due));
                    totalPanel.add(totalAmountDueValueLabel, "growx, wrap");
                JLabel totalAmountPaidLabel = new JLabel("Total Amount Paid: "); // -----------------------------------
                    totalPanel.add(totalAmountPaidLabel, "growx");
                JLabel totalAmountPaidValueLabel = new JLabel("\u20B1  " + (model.total_amount_paid == null ? "0.00" : model.total_amount_paid));
                    totalPanel.add(totalAmountPaidValueLabel, "growx, wrap");
                JLabel changeLabel = new JLabel("Change: "); // -----------------------------------
                    totalPanel.add(changeLabel, "growx");
                JLabel changeValueLabel = new JLabel("\u20B1  " + (model.total_change == null ? "0.00" : model.total_change));
                    totalPanel.add(changeValueLabel, "growx, wrap");
                JLabel totalRefundLabel = new JLabel("Refund: "); // -----------------------------------
                    totalPanel.add(totalRefundLabel, "growx");
                JLabel totalRefundValueLabel = new JLabel("\u20B1  " + (model.total_refund == null ? "0.00" : model.total_refund));
                    totalPanel.add(totalRefundValueLabel, "growx, wrap");
                JLabel tipLabel = new JLabel("Tips: "); // -----------------------------------
                    totalPanel.add(tipLabel, "growx");
                JLabel tipValueLabel = new JLabel("\u20B1  " + (model.total_tip == null ? "0.00" : model.total_tip));
                    totalPanel.add(tipValueLabel, "growx, wrap");


        JButton okayButton = new JButton("Okay");
        this.add(okayButton, "growx, w 100%");
        okayButton.addActionListener(e -> {
            dispose();
        });


        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        this.pack();
        this.setLocationRelativeTo(owner);
        this.setVisible(true);
    }
}
