package ph.edu.tip.mamamoo.Pages;

import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Components.NavButton;
import ph.edu.tip.mamamoo.Data.RoomsPageData;
import ph.edu.tip.mamamoo.Dialogs.BookARoomDialog;
import ph.edu.tip.mamamoo.Models.BookARoomCellModel;
import ph.edu.tip.mamamoo.MultiPageApp;
import ph.edu.tip.mamamoo.Pages.Shared.MainPanel;
import ph.edu.tip.mamamoo.Pages.Shared.NavBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

public class RoomsPage extends JPanel {
    final Logger _logger = LogManager.getLogger();
    private MultiPageApp app;
    private NavBar navBar;
    private MainPanel mainPanel;
    private ArrayList<BookARoomCellModel> rooms;
    public RoomsPage(MultiPageApp app){
        this.app = app;
        this.setLayout(new MigLayout("fillx, inset 0"));
        navBar = new NavBar(app, this);
        this.add(navBar, "dock west");

        this.mainPanel = new MainPanel("Book a Room");
        this.add(mainPanel, "grow, w 100%, h 100%");

        RoomsPageData roomsPageData = new RoomsPageData();
        roomsPageData.checkConnectionStatus();
        rooms = roomsPageData.getBookARoomCellsInfo();

        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 columns, gaps between cells
        gridPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        gridPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollPane, "grow");
        {
            rooms.forEach(room -> {
                NavButton cell = new NavButton(null,
                        null,
                        new Color(228, 228, 228),
                        new Color(238, 238, 238),
                        Color.BLACK,
                        Color.BLACK);
//                cell.setPreferredSize(new Dimension(100, 300));
                cell.setLayout(new MigLayout("flowy, gap 0, inset 0, w 100%, h 100%"));
                cell.setBorder(null);
                cell.setMargin(null);
                gridPanel.add(cell);

                Image scaledImage = room.image.getScaledInstance(245, -1, Image.SCALE_SMOOTH);
                JLabel roomThumbnail = new JLabel(new ImageIcon(scaledImage));
                roomThumbnail.setBorder(null);
                cell.add(roomThumbnail, "grow");

                JLabel roomLabel = new JLabel(room.name);
                roomLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
                cell.add(roomLabel, "gap 5 5 5 0");

                JPanel roomInfoPanel = new JPanel(new MigLayout("gap 0, inset 0 5 0 5"));
                roomInfoPanel.setBackground(null);
                cell.add(roomInfoPanel, "growx");
                {
                    JLabel roomTypeLbl = new JLabel(room.num + ": " + room.type);
                    roomTypeLbl.setFont(new Font("Sans Serif", Font.ITALIC, 14));
                    roomInfoPanel.add(roomTypeLbl, "pushx, alignx left");

                    JPanel rateInfoPanel = new JPanel(new MigLayout("gap 0, inset 0"));
                    rateInfoPanel.setBackground(null);
                    roomInfoPanel.add(rateInfoPanel, "pushx, alignx right");
                    {
                        JLabel roomPriceLabel = new JLabel(String.format("\u20B1 %.2f", room.price));
                        roomPriceLabel.setFont(new Font("Sans Serif", Font.BOLD, 14));
                        rateInfoPanel.add(roomPriceLabel, "pushx, alignx right");

                        JLabel roomRateLabel = new JLabel(" / " + room.rate);
                        roomRateLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
                        rateInfoPanel.add(roomRateLabel, "pushx, alignx right");

                    }
                }

                JLabel roomDescLabel = new JLabel(room.desc);
                roomDescLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
                roomDescLabel.setMaximumSize(new Dimension(230, roomDescLabel.getPreferredSize().height));
                cell.add(roomDescLabel, "growx, gapx 5 5, gapy 2.5 5");

                JPanel amenitiesPanel = new JPanel(new MigLayout("wrap 3"));
                amenitiesPanel.setBackground(null);
                cell.add(amenitiesPanel, "growx");
                {
                    room.amenities.forEach(amenity -> {
                        JTextArea amenityLabel = new JTextArea();
                        if (
                            containsIgnoreCase(amenity.roomAmenityName, "bed") ||
                            containsIgnoreCase(amenity.roomAmenityName, "bath")
                            ) {
                            amenityLabel = new JTextArea(amenity.roomAmenityCount + " " + amenity.roomAmenityName);
                            amenityLabel.setBackground(null);
                            amenityLabel.setEditable(false);
                            amenityLabel.setLineWrap(true);
                            amenityLabel.setWrapStyleWord(true);
                            amenityLabel.setFont(new Font("Sans Serif", Font.PLAIN, 12));
                            amenityLabel.setMaximumSize(new Dimension(60, amenityLabel.getPreferredSize().height*2));
                            amenitiesPanel.add(amenityLabel);
                        }
                    });
                }
                //add action listener starting frame for each cell
                cell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BookARoomDialog bookARoomDialog = new BookARoomDialog(app, room);
                        bookARoomDialog.setVisible(true);
                    }
                });
            });
        }
    }

    public NavBar getNavBar() {
        return navBar;
    }
}
