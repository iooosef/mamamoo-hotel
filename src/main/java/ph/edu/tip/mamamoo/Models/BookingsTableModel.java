package ph.edu.tip.mamamoo.Models;

import java.sql.Timestamp;

public class BookingsTableModel {
    public int bkng_id;
    public int room_id;
    public Timestamp bkng_datetime;
    public Timestamp checkin;
    public Timestamp checkout;
    public String guest_fname;
    public String guest_lname;
    public String guest_contact;
    public String status;
}
