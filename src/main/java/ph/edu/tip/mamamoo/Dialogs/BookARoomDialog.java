package ph.edu.tip.mamamoo.Dialogs;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import net.miginfocom.swing.MigLayout;
import ph.edu.tip.mamamoo.Components.NavButton;
import ph.edu.tip.mamamoo.Data.BookingsData;
import ph.edu.tip.mamamoo.Data.RoomsPageData;
import ph.edu.tip.mamamoo.Models.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.MultiPageApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;

public class BookARoomDialog extends Dialog {
    final Logger _logger = LogManager.getLogger();
    private BookARoomCellModel model;
    private RoomsPageData roomsPageData;
    private BookingsData bookingsData = new BookingsData();
    private MultiPageApp app;

    public BookARoomDialog(Frame owner, BookARoomCellModel model, MultiPageApp app) {
        super(owner, "", true);
        this.app = app;
        this.model = model;
        this.setTitle("Book at Room " + model.num);
        this.roomsPageData = new RoomsPageData();
        this.setSize(940, 500);
        this.setResizable(false);
        this.setLayout(new MigLayout("fillx, filly"));

        JPanel sidePanel = new JPanel(new MigLayout("flowy, fillx"));
        sidePanel.setBackground(new Color(247, 251, 253));
        sidePanel.setMaximumSize(new Dimension(330, 440));
        JScrollPane sidePanelScroll = new JScrollPane(sidePanel);
        sidePanelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sidePanelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(sidePanelScroll, "dock west, growy, w 300px");
        {
            _logger.info("MODEL DOT ID:" + model.id);
            ArrayList<BookingsOfRoomModel> bookings = roomsPageData.getBookingsOfRoom(model.id);
            bookings.forEach( booking -> {
                JPanel bookingPanel = new JPanel(new MigLayout("flowy, fillx"));
                bookingPanel.setBackground(Color.WHITE);
                bookingPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
                sidePanel.add(bookingPanel, "growx");
                {
                    JLabel bookingId = new JLabel("Booking # " + booking.id);
                    bookingPanel.add(bookingId);

                    JPanel splitPanel = new JPanel(new MigLayout("fillx, inset 0"));
                    splitPanel.setBackground(null);
                    bookingPanel.add(splitPanel, "growx");
                    {
                        JPanel leftPanel = new JPanel(new MigLayout("flowy, inset 0, gap 3"));
                        leftPanel.setBackground(null);
                        splitPanel.add(leftPanel, "flowy, growx, w 50%");
                        {
                            JLabel checkInLabel = new JLabel("Check In");
                            checkInLabel.setFont(new Font("Sans Serif", Font.BOLD, 14));
                            leftPanel.add(checkInLabel, "growx");

                            JLabel checkInDate = new JLabel(new SimpleDateFormat("MM/dd/yyyy").format(booking.checkIn));
                            leftPanel.add(checkInDate, "growx");

                            JLabel checkInTime = new JLabel(new SimpleDateFormat("hh:mm a").format(booking.checkIn));
                            leftPanel.add(checkInTime, "growx");
                        }
                        JPanel rightPanel = new JPanel(new MigLayout("flowy, inset 0, gap 3"));
                        rightPanel.setBackground(null);
                        splitPanel.add(rightPanel, "flowy, growx, w 50%");
                        {
                            JLabel checkInLabel = new JLabel("Check Out");
                            checkInLabel.setFont(new Font("Sans Serif", Font.BOLD, 14));
                            rightPanel.add(checkInLabel, "growx");

                            JLabel checkInDate = new JLabel(booking.checkOut != null ? new SimpleDateFormat("MM/dd/yyyy").format(booking.checkOut) : "N/A");
                            rightPanel.add(checkInDate, "growx");

                            JLabel checkInTime = new JLabel(booking.checkOut != null ? new SimpleDateFormat("hh:mm a").format(booking.checkOut) : "N/A");
                            rightPanel.add(checkInTime, "growx");
                        }
                    }
                }
            });
        }

        JPanel formPanel = new JPanel(new MigLayout("fillx, wrap"));
        formPanel.setBackground(Color.WHITE);
        JScrollPane formPanelScroll = new JScrollPane(formPanel);
        formPanelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        formPanelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(formPanelScroll, "grow, w 640px, h 100%");
        JLabel roomLabel = new JLabel("Book Room " + model.num + " - " + model.type + " Room");
        roomLabel.setFont(new Font("Sans Serif", Font.BOLD, 32));
        formPanel.add(roomLabel);

        JPanel splitPanel = new JPanel(new MigLayout("fillx"));
        splitPanel.setBackground(null);
        formPanel.add(splitPanel, "growx");
        JPanel leftPanel = new JPanel(new MigLayout("flowy, fillx, inset 0, gap 3"));
        leftPanel.setBackground(null);
        splitPanel.add(leftPanel, "flowy, growx, w 50%");
        // { -----------------------------------------------
        JLabel fNameLabel = new JLabel("First Name *");
        fNameLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        leftPanel.add(fNameLabel, "growx");
        JTextField fNameField = new JTextField("");
        fNameField.setBorder(BorderFactory.createCompoundBorder(
                fNameField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        fNameField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        leftPanel.add(fNameField, "growx");

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        leftPanel.add(emailLabel, "growx");
        JTextField emailField = new JTextField("");
        emailField.setBorder(BorderFactory.createCompoundBorder(
                emailField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        emailField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        leftPanel.add(emailField, "growx");

        JLabel checkInDate = new JLabel("Check-in Date *");
        checkInDate.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        leftPanel.add(checkInDate, "growx");
        DatePicker checkInDatePicker = new DatePicker();
        checkInDatePicker.getComponentDateTextField().setFont(new Font("Sans Serif", Font.PLAIN, 14));
        leftPanel.add(checkInDatePicker, "growx");

        JLabel checkOutDate = new JLabel("Check-out Date");
        checkOutDate.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        leftPanel.add(checkOutDate, "growx");
        DatePicker checkOutPicker = new DatePicker();
        checkOutPicker.getComponentDateTextField().setFont(new Font("Sans Serif", Font.PLAIN, 14));
        leftPanel.add(checkOutPicker, "growx");

        JLabel voucherLabel = new JLabel("Voucher Code");
        voucherLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        leftPanel.add(voucherLabel, "growx");
        JTextField voucherField = new JTextField("");
        voucherField.setBorder(BorderFactory.createCompoundBorder(
                voucherField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        voucherField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        leftPanel.add(voucherField, "growx");
        //  ----------------------------------------------- }

        JPanel rightPanel = new JPanel(new MigLayout("flowy, fillx, inset 0, gap 3"));
        rightPanel.setBackground(null);
        splitPanel.add(rightPanel, "flowy, growx, w 50%");
        // { -----------------------------------------------
        JLabel lNameLabel = new JLabel("Last Name *");
        lNameLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        rightPanel.add(lNameLabel, "growx");
        JTextField lNameField = new JTextField("");
        lNameField.setBorder(BorderFactory.createCompoundBorder(
                lNameField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        lNameField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        rightPanel.add(lNameField, "growx");

        JLabel contactLabel = new JLabel("Contact Number *");
        contactLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        rightPanel.add(contactLabel, "growx");
        JTextField contactField = new JTextField("");
        contactField.setBorder(BorderFactory.createCompoundBorder(
                contactField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contactField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        rightPanel.add(contactField, "growx");

        JLabel checkInTime = new JLabel("Check-in Time *");
        checkInTime.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        rightPanel.add(checkInTime, "growx");
        TimePicker checkInTimePicker = new TimePicker();
        checkInTimePicker.getComponentTimeTextField().setFont(new Font("Sans Serif", Font.PLAIN, 14));
        rightPanel.add(checkInTimePicker, "growx");

        JLabel checkOutTime = new JLabel("Check-out Time");
        checkOutTime.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        rightPanel.add(checkOutTime, "growx");
        TimePicker checkOutTimePicker = new TimePicker();
        checkOutTimePicker.getComponentTimeTextField().setFont(new Font("Sans Serif", Font.PLAIN, 14));
        rightPanel.add(checkOutTimePicker, "growx");

        JLabel seniorPwdLabel = new JLabel("Senior Citizen/PWD ID Number");
        seniorPwdLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        rightPanel.add(seniorPwdLabel, "growx");
        JTextField seniorPwdField = new JTextField("");
        seniorPwdField.setBorder(BorderFactory.createCompoundBorder(
                seniorPwdField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        seniorPwdField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        rightPanel.add(seniorPwdField, "growx");
        //  ----------------------------------------------- }

        NavButton bookButton = new NavButton("Book Room");
        bookButton.setFont(new Font("Sans Serif", Font.BOLD, 16));
        bookButton.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(bookButton, "growx, gap 10 10 8 2");
        bookButton.addActionListener(e -> {
            _logger.info("&&&&&&&&&" + model.num + " " + model.id);
            if (fNameField.getText().isEmpty() || lNameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "First and Last Name are required", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (contactField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Contact number is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (checkInDatePicker.getDateStringOrEmptyString().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Check-in date is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (checkInTimePicker.getTimeStringOrEmptyString().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Check-in time is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                BookARoomModel inputModel = new BookARoomModel();
                inputModel.room_id = model.id;
                inputModel.check_in_date = Date.valueOf(checkInDatePicker.getDate());
                inputModel.check_in_time = Time.valueOf(checkInTimePicker.getTime());
                inputModel.check_out_date = checkOutPicker.getDate() != null ? Date.valueOf(checkOutPicker.getDate()) : null;
                inputModel.check_out_time = checkOutTimePicker.getTime() != null ? Time.valueOf(checkOutTimePicker.getTime()) : null;
                inputModel.fname = fNameField.getText();
                inputModel.lname = lNameField.getText();
                inputModel.email = emailField.getText();
                inputModel.contact_num = contactField.getText();
                inputModel.voucher = voucherField.getText();
                inputModel.senior_pwd_code = seniorPwdField.getText();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(inputModel.check_in_date);
                calendar.set(Calendar.HOUR_OF_DAY, inputModel.check_in_time.getHours());
                calendar.set(Calendar.MINUTE, inputModel.check_in_time.getMinutes());
                calendar.set(Calendar.SECOND, inputModel.check_in_time.getSeconds());
                calendar.set(Calendar.MILLISECOND, 0);
                Timestamp check_in_datetime = new Timestamp(calendar.getTimeInMillis());
                Timestamp check_out_datetime = null;
                if (inputModel.check_out_date != null && inputModel.check_out_time != null) {
                    calendar.setTime(inputModel.check_out_date);
                    calendar.set(Calendar.HOUR_OF_DAY, inputModel.check_out_time.getHours());
                    calendar.set(Calendar.MINUTE, inputModel.check_out_time.getMinutes());
                    calendar.set(Calendar.SECOND, inputModel.check_out_time.getSeconds());
                    calendar.set(Calendar.MILLISECOND, 0);
                    check_out_datetime = new Timestamp(calendar.getTimeInMillis());
                }

                if(roomsPageData.hasConflict(model.id, check_in_datetime, check_out_datetime)) {
                    JOptionPane.showMessageDialog(this, "Room " + model.num + " is already booked on the selected date and time", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                roomsPageData.insBooking(inputModel, "reserved");
                JOptionPane.showMessageDialog(this, "Room " + model.num + " has been successfully booked", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        NavButton bookAndPayButton = new NavButton("Book and Pay");
        bookAndPayButton.setFont(new Font("Sans Serif", Font.BOLD, 16));
        bookAndPayButton.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(bookAndPayButton, "growx, gap 10 10 2 8");
        bookAndPayButton.addActionListener(e -> {
            if (fNameField.getText().isEmpty() || lNameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "First and Last Name are required", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (contactField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Contact number is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (checkInDatePicker.getDateStringOrEmptyString().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Check-in date is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (checkInTimePicker.getTimeStringOrEmptyString().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Check-in time is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                BookARoomModel inputModel = new BookARoomModel();
                inputModel.room_id = model.id;
                inputModel.check_in_date = Date.valueOf(checkInDatePicker.getDate());
                inputModel.check_in_time = Time.valueOf(checkInTimePicker.getTime());
                inputModel.check_out_date = checkOutPicker.getDate() != null ? Date.valueOf(checkOutPicker.getDate()) : null;
                inputModel.check_out_time = checkOutTimePicker.getTime() != null ? Time.valueOf(checkOutTimePicker.getTime()) : null;
                inputModel.fname = fNameField.getText();
                inputModel.lname = lNameField.getText();
                inputModel.email = emailField.getText();
                inputModel.contact_num = contactField.getText();
                inputModel.voucher = voucherField.getText();
                inputModel.senior_pwd_code = seniorPwdField.getText();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(inputModel.check_in_date);
                calendar.set(Calendar.HOUR_OF_DAY, inputModel.check_in_time.getHours());
                calendar.set(Calendar.MINUTE, inputModel.check_in_time.getMinutes());
                calendar.set(Calendar.SECOND, inputModel.check_in_time.getSeconds());
                calendar.set(Calendar.MILLISECOND, 0);
                Timestamp check_in_datetime = new Timestamp(calendar.getTimeInMillis());
                Timestamp check_out_datetime = null;
                if (inputModel.check_out_date != null && inputModel.check_out_time != null) {
                    calendar.setTime(inputModel.check_out_date);
                    calendar.set(Calendar.HOUR_OF_DAY, inputModel.check_out_time.getHours());
                    calendar.set(Calendar.MINUTE, inputModel.check_out_time.getMinutes());
                    calendar.set(Calendar.SECOND, inputModel.check_out_time.getSeconds());
                    calendar.set(Calendar.MILLISECOND, 0);
                    check_out_datetime = new Timestamp(calendar.getTimeInMillis());
                }

                if(roomsPageData.hasConflict(model.id, check_in_datetime, check_out_datetime)) {
                    JOptionPane.showMessageDialog(this, "Room " + model.num + " is already booked on the selected date and time", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                roomsPageData.insBooking(inputModel, "reserved");
                JOptionPane.showMessageDialog(this, "Room " + model.num + " has been successfully booked", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                showBookAndPayDialog(model.id);
            }
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

    private void showBookAndPayDialog(int bkng_id) {
        PaymentModel paymentModel = bookingsData.getTotalRatedCost(bkng_id);
        InvoiceModel invoiceModel = new InvoiceModel();
        ArrayList<ServiceFeeModel> serviceFees = bookingsData.getAllServiceFeesOfBooking(bkng_id);
        ServiceFeeTotalModel serviceFeeTotalModel = bookingsData.getTotalServiceFeesOfBooking(bkng_id);
        serviceFeeTotalModel.total = (serviceFeeTotalModel.total == null ? BigDecimal.ZERO : serviceFeeTotalModel.total);

        Dialog payFormDialog = new Dialog(app);
        payFormDialog.setTitle("Pre-pay Reservation");
        payFormDialog.setLayout(new MigLayout("flowy, fillx, debug", "[grow]", "[]10[]"));
        payFormDialog.setResizable(false);

        JLabel dialogTitle = new JLabel("Pay Booking No. " + bkng_id);
        dialogTitle.setFont(new Font("Sans Serif", Font.BOLD, 18));
        payFormDialog.add(dialogTitle, "growx, w 100%");
        JLabel roomNumberLabel = new JLabel("Room No.  " + paymentModel.room_num);
        payFormDialog.add(roomNumberLabel, "growx, w 100%");

        JLabel paymentDetailsTitleLabel = new JLabel("Payment Details");
        paymentDetailsTitleLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
        payFormDialog.add(paymentDetailsTitleLabel, "growx, w 100%");
        JPanel detailsLeftValueRightPanel = new JPanel();
        detailsLeftValueRightPanel.setLayout(new MigLayout("fillx, inset 0, debug"));
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
                    bookingsData.updBookingStatus(bkng_id, "paid");
                    _logger.info("Booking No. " + bkng_id + " has been paid");
                    payFormDialog.dispose();
                    InvoiceDialog dialog = new InvoiceDialog(app, invoiceModel.bkn_id);
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

}
