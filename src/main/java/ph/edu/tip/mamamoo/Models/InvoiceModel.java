package ph.edu.tip.mamamoo.Models;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

public class InvoiceModel {
    public int bkn_id;
    public String room_num;
    public String guest_name;
    public Timestamp check_in;
    public Timestamp check_out;
    public Timestamp payment_datetime;
    public String payment_code;
    public BigDecimal reservation_fee;
    public BigDecimal total_rated_cost;
    public BigDecimal vat;
    public BigDecimal senior_pwd_discount;
    public BigDecimal voucher_discount;
    public BigDecimal total_service_fee;
    public BigDecimal total_amount_due;
    public BigDecimal total_amount_paid;
    public BigDecimal total_change;
    public BigDecimal total_refund;
    public BigDecimal total_tip;

}
