package ph.edu.tip.mamamoo.Dialogs;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import net.miginfocom.swing.MigLayout;
import ph.edu.tip.mamamoo.Components.NavButton;
import ph.edu.tip.mamamoo.Data.RoomsPageData;
import ph.edu.tip.mamamoo.Models.BookARoomCellModel;
import ph.edu.tip.mamamoo.Models.BookingsOfRoomModel;
import java.text.SimpleDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class BookARoomDialog extends Dialog {
    final Logger _logger = LogManager.getLogger();
    private BookARoomCellModel model;
    private RoomsPageData roomsPageData;
    public BookARoomDialog(Frame owner, BookARoomCellModel model) {
        super(owner, "", true);
        this.model = model;
        this.setTitle("Book at Room " + model.num);
        this.roomsPageData = new RoomsPageData();
        this.setSize(940, 440);
        this.setResizable(false);
        this.setLocationRelativeTo(owner);
        this.setLayout(new MigLayout("fillx, filly"));

        JPanel sidePanel = new JPanel(new MigLayout("flowy, fillx"));
        sidePanel.setBackground(new Color(247, 251, 253));
        sidePanel.setMaximumSize(new Dimension(330, 440));
        JScrollPane sidePanelScroll = new JScrollPane(sidePanel);
        sidePanelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sidePanelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(sidePanelScroll, "dock west, growy, w 300px");
        {
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
                            JLabel checkInLabel = new JLabel("Check In");
                            checkInLabel.setFont(new Font("Sans Serif", Font.BOLD, 14));
                            rightPanel.add(checkInLabel, "growx");

                            JLabel checkInDate = new JLabel(new SimpleDateFormat("MM/dd/yyyy").format(booking.checkOut));
                            rightPanel.add(checkInDate, "growx");

                            JLabel checkInTime = new JLabel(new SimpleDateFormat("hh:mm a").format(booking.checkOut));
                            rightPanel.add(checkInTime, "growx");
                        }
                    }
                }
            });
        }

        JPanel formPanel = new JPanel(new MigLayout("fillx, wrap"));
        formPanel.setBackground(Color.WHITE);
        JScrollPane formPanelScroll = new JScrollPane(formPanel);
        formPanelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        formPanelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(formPanelScroll, "grow, w 640px, h 100%");
        {
            JLabel roomLabel = new JLabel("Book Room " + model.num + " - " + model.type + " Room");
            roomLabel.setFont(new Font("Sans Serif", Font.BOLD, 32));
            formPanel.add(roomLabel);

            JPanel splitPanel = new JPanel(new MigLayout("fillx"));
            splitPanel.setBackground(null);
            formPanel.add(splitPanel, "growx");
            {
                JPanel leftPanel = new JPanel(new MigLayout("flowy, fillx, inset 0, gap 3"));
                leftPanel.setBackground(null);
                splitPanel.add(leftPanel, "flowy, growx, w 50%");
                // { -----------------------------------------------
                JLabel fNameLabel = new JLabel("First Name");
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

                JLabel checkInDate = new JLabel("Check-in Date");
                    checkInDate.setFont(new Font("Sans Serif", Font.PLAIN, 14));
                    leftPanel.add(checkInDate, "growx");
                DatePicker checkInDatePicker = new DatePicker();
                    checkInDatePicker.getComponentDateTextField().setFont(new Font("Sans Serif", Font.PLAIN, 14));
                    leftPanel.add(checkInDatePicker, "growx");

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
                JLabel lNameLabel = new JLabel("Last Name");
                    lNameLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
                    rightPanel.add(lNameLabel, "growx");
                JTextField lNameField = new JTextField("");
                    lNameField.setBorder(BorderFactory.createCompoundBorder(
                            lNameField.getBorder(),
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
                    lNameField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
                    rightPanel.add(lNameField, "growx");

                JLabel contactLabel = new JLabel("Contact Number");
                    contactLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
                    rightPanel.add(contactLabel, "growx");
                JTextField contactField = new JTextField("");
                    contactField.setBorder(BorderFactory.createCompoundBorder(
                            contactField.getBorder(),
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
                    contactField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
                    rightPanel.add(contactField, "growx");

                JLabel checkOutDate = new JLabel("Check-out Date");
                    checkOutDate.setFont(new Font("Sans Serif", Font.PLAIN, 14));
                    rightPanel.add(checkOutDate, "growx");
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
            }

            NavButton bookButton = new NavButton("Book Room");
            bookButton.setFont(new Font("Sans Serif", Font.BOLD, 16));
            bookButton.setHorizontalAlignment(SwingConstants.CENTER);
            formPanel.add(bookButton, "growx, gap 10 8 10 8");

        }


        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
