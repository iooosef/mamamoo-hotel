package ph.edu.tip.mamamoo.Models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PaymentTblModel {
    public int bkng_id;
    public String payment_code;
    public Timestamp payment_datetime;
    public BigDecimal total_rated_cost;
    public BigDecimal vat;
    public BigDecimal senior_pwd_discount;
    public BigDecimal voucher_discount;
    public BigDecimal total_service_fee;
    public BigDecimal total_amount_due;
    public BigDecimal total_paid;
    public BigDecimal total_change;
    public BigDecimal total_refund;
    public BigDecimal total_tip;
}
