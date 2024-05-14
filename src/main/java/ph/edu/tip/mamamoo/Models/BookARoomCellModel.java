package ph.edu.tip.mamamoo.Models;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class BookARoomCellModel {
    public int id;
    public String num;
    public String name;
    public String type;
    public String desc;
    public String rate;
    public BigDecimal price;
    public Image image;
    public ArrayList<RoomAmenitiesModel> amenities;
}
