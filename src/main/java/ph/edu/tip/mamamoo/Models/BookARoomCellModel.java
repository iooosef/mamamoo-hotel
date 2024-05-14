package ph.edu.tip.mamamoo.Models;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class BookARoomCellModel {
    public int roomId;
    public String roomNum;
    public String roomName;
    public String roomType;
    public String roomDesc;
    public String roomRate;
    public BigDecimal roomPrice;
    public Image roomImage;
    public ArrayList<RoomAmenitiesModel> roomAmenities;
}
