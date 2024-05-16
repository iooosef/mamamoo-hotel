package ph.edu.tip.mamamoo.Models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PaymentModel {
    public int bkng_id;
    public String room_num;
    public BigDecimal reservation_fee;
    public BigDecimal total_rated_amount;
    public BigDecimal vat;
    public BigDecimal senior_pwd_discount;
    public BigDecimal voucher_discount;
    public Timestamp check_out;
}
