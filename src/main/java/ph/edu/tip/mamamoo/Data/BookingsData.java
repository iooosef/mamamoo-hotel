package ph.edu.tip.mamamoo.Data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Models.*;
import ph.edu.tip.mamamoo.Utilities.DotEnvUtility;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class BookingsData {
    final Logger _logger = LogManager.getLogger();
    String connectionString;
    public BookingsData() {
        DotEnvUtility dotenv = DotEnvUtility.configure().load();
        connectionString = dotenv.get("HotelAppDBConnStr");
    }

    public void checkConnectionStatus() {
        try {
            try (Connection conn = DriverManager.getConnection(connectionString)) {
                _logger.info("Successfully connected to MS SQL Server & HotelAppDB!");
            }
        } catch (Exception e) {
            _logger.error("Error! Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    public ArrayList<BookingsTableModel> getAllNonReservationBookings() {
        ArrayList<BookingsTableModel> results = new ArrayList<BookingsTableModel>();
        BookingsTableModel resultRow;
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_getAllNonReservationBookings}";
            java.sql.CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);

            callableStatement.execute();

            java.sql.ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                resultRow = new BookingsTableModel();
                resultRow.bkng_id = resultSet.getInt("bkng_id");
                resultRow.room_id = resultSet.getInt("room_id");
                resultRow.bkng_datetime = resultSet.getTimestamp("booking_datetime");
                resultRow.checkin = resultSet.getTimestamp("check_in_datetime");
                resultRow.checkout = resultSet.getTimestamp("check_out_datetime");
                resultRow.guest_fname = resultSet.getString("guest_given_name");
                resultRow.guest_lname = resultSet.getString("guest_last_name");
                resultRow.guest_contact = resultSet.getString("contact_num");
                resultRow.status = resultSet.getString("status");
                results.add(resultRow);
            }
            _logger.info("Successfully retrieved all non-reservation bookings.");
        } catch (java.sql.SQLException e) {
            _logger.error("Error! Failed to get all non-reservation bookings.");
            e.printStackTrace();
        }
        return results;
    }

    public ArrayList<BookingsTableModel> getAllReservationBookings() {
        ArrayList<BookingsTableModel> results = new ArrayList<BookingsTableModel>();
        BookingsTableModel resultRow;
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_getAllReservationBookings}";
            java.sql.CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);

            callableStatement.execute();

            java.sql.ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                resultRow = new BookingsTableModel();
                resultRow.bkng_id = resultSet.getInt("bkng_id");
                resultRow.room_id = resultSet.getInt("room_id");
                resultRow.bkng_datetime = resultSet.getTimestamp("booking_datetime");
                resultRow.checkin = resultSet.getTimestamp("check_in_datetime");
                resultRow.checkout = resultSet.getTimestamp("check_out_datetime");
                resultRow.guest_fname = resultSet.getString("guest_given_name");
                resultRow.guest_lname = resultSet.getString("guest_last_name");
                resultRow.guest_contact = resultSet.getString("contact_num");
                resultRow.status = resultSet.getString("status");
                results.add(resultRow);
            }
            _logger.info("Successfully retrieved all reservation bookings.");
        } catch (java.sql.SQLException e) {
            _logger.error("Error! Failed to get all reservation bookings.");
            e.printStackTrace();
        }
        return results;
    }

    public BookingsTableModel getNonReservationBookingOf(int bkngID) {
        BookingsTableModel resultRow = new BookingsTableModel();
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_getNonReservationBookingOf(?)}";
            java.sql.CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.setInt(1, bkngID);

            callableStatement.execute();

            java.sql.ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                resultRow.bkng_id = resultSet.getInt("bkng_id");
                resultRow.room_id = resultSet.getInt("room_id");
                resultRow.bkng_datetime = resultSet.getTimestamp("booking_datetime");
                resultRow.checkin = resultSet.getTimestamp("check_in_datetime");
                resultRow.checkout = resultSet.getTimestamp("check_out_datetime");
                resultRow.guest_fname = resultSet.getString("guest_given_name");
                resultRow.guest_lname = resultSet.getString("guest_last_name");
                resultRow.guest_contact = resultSet.getString("contact_num");
                resultRow.status = resultSet.getString("status");
            }
            _logger.info("Successfully retrieved non-reservation bookings.");
        } catch (java.sql.SQLException e) {
            _logger.error("Error! Failed to get non-reservation bookings.");
            e.printStackTrace();
        }
        return resultRow;
    }

    public BookingsTableModel getReservationBookingOf(int bkngID) {
        BookingsTableModel resultRow = new BookingsTableModel();
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_getReservationBookingOf(?)}";
            java.sql.CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.setInt(1, bkngID);

            callableStatement.execute();

            java.sql.ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                resultRow.bkng_id = resultSet.getInt("bkng_id");
                resultRow.room_id = resultSet.getInt("room_id");
                resultRow.bkng_datetime = resultSet.getTimestamp("booking_datetime");
                resultRow.checkin = resultSet.getTimestamp("check_in_datetime");
                resultRow.checkout = resultSet.getTimestamp("check_out_datetime");
                resultRow.guest_fname = resultSet.getString("guest_given_name");
                resultRow.guest_lname = resultSet.getString("guest_last_name");
                resultRow.guest_contact = resultSet.getString("contact_num");
                resultRow.status = resultSet.getString("status");
            }
            _logger.info("Successfully retrieved non-reservation bookings.");
        } catch (java.sql.SQLException e) {
            _logger.error("Error! Failed to get non-reservation bookings.");
            e.printStackTrace();
        }
        return resultRow;
    }

    public PaymentModel getTotalRatedCost(int bkngID) {
        PaymentModel resultRow = new PaymentModel();
        String roomRateType = getRoomRateTypeOfBooking(bkngID);
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall;
            if (roomRateType.equals("Hourly")) {
                storedProcedureCall = "{call HotelAppDB.dbo.usp_getTotalHourlyCostPlusVAT(?)}";
            } else {
                storedProcedureCall = "{call HotelAppDB.dbo.usp_getTotalNightlyCostPlusVAT(?)}";
            }
            java.sql.CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.setInt(1, bkngID);

            callableStatement.execute();

            java.sql.ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                resultRow.room_num = resultSet.getString("room_num");
                resultRow.bkng_id = resultSet.getInt("bkng_id");
                resultRow.total_rated_amount = BigDecimal.valueOf(resultSet.getDouble("total_cost"));
                resultRow.vat = BigDecimal.valueOf(resultSet.getDouble("vat"));
                resultRow.senior_pwd_discount = BigDecimal.valueOf(resultSet.getDouble("senior_pwd_discount"));
                resultRow.voucher_discount = BigDecimal.valueOf(resultSet.getDouble("voucher_discount"));
                resultRow.check_out = resultSet.getTimestamp("check_out_datetime");
            }
            _logger.info("Successfully retrieved total hourly cost.");
        } catch (java.sql.SQLException e) {
            _logger.error("Error! Failed to get total hourly cost.");
            e.printStackTrace();
        }
        return resultRow;
    }

    public String getRoomRateTypeOfBooking(int bkngID) {
        String result = "";
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.getRoomRateTypeOfBooking(?)}";
            java.sql.CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.setInt(1, bkngID);

            callableStatement.execute();

            java.sql.ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                result = resultSet.getString("room_rate_type");
            }
            _logger.info("Successfully retrieved room rate type.");
        } catch (java.sql.SQLException e) {
            _logger.error("Error! Failed to get room rate type.");
            e.printStackTrace();
        }
        return result;
    }

    public ServiceFeeTotalModel getTotalServiceFeesOfBooking(int bkngID) {
        ServiceFeeTotalModel resultRow = new ServiceFeeTotalModel();
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_getTotalServiceFeesOfBooking(?)}";
            java.sql.CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.setInt(1, bkngID);

            callableStatement.execute();

            java.sql.ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                resultRow.bkng_id = resultSet.getInt("bkng_id");
                resultRow.total = BigDecimal.valueOf(resultSet.getDouble("total"));
                resultRow.room_num = resultSet.getString("room_num");
            }
            _logger.info("Successfully retrieved total service fees.");
        } catch (java.sql.SQLException e) {
            _logger.error("Error! Failed to get total service fees.");
            e.printStackTrace();
        }
        return resultRow;
    }

    public ArrayList<ServiceFeeModel> getAllServiceFeesOfBooking(int bkngID) {
        ArrayList<ServiceFeeModel> results = new ArrayList<ServiceFeeModel>();
        ServiceFeeModel resultRow;
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_getAllServiceFeesOfBooking(?)}";
            java.sql.CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.setInt(1, bkngID);

            callableStatement.execute();

            java.sql.ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                resultRow = new ServiceFeeModel();
                resultRow.bkng_id = resultSet.getInt("bkng_id");
                resultRow.room_num = resultSet.getString("room_num");
                resultRow.service_name = resultSet.getString("service_desc");
                resultRow.cost = BigDecimal.valueOf(resultSet.getDouble("service_cost"));
                results.add(resultRow);
            }
            _logger.info("Successfully retrieved all service fees.");
        } catch (java.sql.SQLException e) {
            _logger.error("Error! Failed to get all service fees.");
            e.printStackTrace();
        }
        return results;
    }

    public void insPayment(InvoiceModel model) {
        InvoiceModel resultRow = new InvoiceModel();
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_insPayment(?,?,?,?,?,?,?,?,?,?,?)}";
            java.sql.CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.setInt(1, model.bkn_id);
            callableStatement.setTimestamp(2, model.check_out);
            callableStatement.setBigDecimal(3, model.total_rated_cost);
            callableStatement.setBigDecimal(4, model.vat);
            callableStatement.setBigDecimal(5, model.senior_pwd_discount);
            callableStatement.setBigDecimal(6, model.voucher_discount);
            callableStatement.setBigDecimal(7, model.total_service_fee);
            callableStatement.setBigDecimal(8, model.total_amount_due);
            callableStatement.setBigDecimal(9, model.total_amount_paid);
            callableStatement.setBigDecimal(10, model.total_refund);
            callableStatement.setBigDecimal(11, model.total_tip);
            callableStatement.execute();
            _logger.info("Successfully inserted payment.");
        } catch (java.sql.SQLException e) {
            _logger.error("Error! Failed to insert and retrieve payment.");
            e.printStackTrace();
        }
        return;
    }

    public InvoiceModel getPaymentOfBooking(int bkngID) {
        InvoiceModel resultRow = new InvoiceModel();
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_getPayment(?)}";
            java.sql.CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.setInt(1, bkngID);

            callableStatement.execute();

            java.sql.ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                resultRow.bkn_id = bkngID;
                resultRow.room_num = resultSet.getString("room_num");
                resultRow.guest_name = resultSet.getString("guest_name");
                resultRow.check_in = resultSet.getTimestamp("check_in");
                resultRow.check_out = resultSet.getTimestamp("check_out");
                resultRow.reservation_fee = resultSet.getBigDecimal("reservation_fee");
                resultRow.payment_code = resultSet.getString("payment_code");
                resultRow.payment_datetime = resultSet.getTimestamp("payment_datetime");
                resultRow.reservation_fee = resultSet.getBigDecimal("reservation_fee");
                resultRow.total_rated_cost = resultSet.getBigDecimal("payment_total_rated_cost");
                resultRow.vat = resultSet.getBigDecimal("payment_vat");
                resultRow.senior_pwd_discount = resultSet.getBigDecimal("payment_senior_pwd_discount");
                resultRow.voucher_discount = resultSet.getBigDecimal("payment_voucher_discount");
                resultRow.total_service_fee = resultSet.getBigDecimal("payment_total_service_fee");
                resultRow.total_amount_due = resultSet.getBigDecimal("payment_total_amount_due");
                resultRow.total_amount_paid = resultSet.getBigDecimal("payment_total_amount_paid");
                resultRow.total_change = resultSet.getBigDecimal("payment_change");
                resultRow.total_refund = resultSet.getBigDecimal("payment_refund");
                resultRow.total_tip = resultSet.getBigDecimal("payment_tip");
            }
            _logger.info("Successfully retrieved payment.");
        } catch (java.sql.SQLException e) {
            _logger.error("Error! Failed to get payment.");
            e.printStackTrace();
        }
        return resultRow;
    }

    public void updPayment(int bkng_id, BigDecimal serviceFeesTotal, BigDecimal addedPayment) {
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_updPayment(?,?,?)}";
            java.sql.CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.setInt(1, bkng_id);
            callableStatement.setBigDecimal(2, serviceFeesTotal);
            callableStatement.setBigDecimal(3, addedPayment);
            callableStatement.execute();
            _logger.info("Successfully updated payment.");
        } catch (java.sql.SQLException e) {
            _logger.error("Error! Failed to update payment.");
            e.printStackTrace();
        }
        return;
    }

    public void updBookingStatus(int bkng_id, String status) {
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_updBookingStatus(?,?)}";
            java.sql.CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.setInt(1, bkng_id);
            callableStatement.setString(2, status);
            callableStatement.execute();
            _logger.info("Successfully updated booking status.");
        } catch (java.sql.SQLException e) {
            _logger.error("Error! Failed to update booking status.");
            e.printStackTrace();
        }
        return;
    }

    public ArrayList<PaymentTblModel> getAllPayments() {
        ArrayList<PaymentTblModel> results = new ArrayList<PaymentTblModel>();
        PaymentTblModel resultRow;
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_getAllPayments}";
            java.sql.CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.execute();
            java.sql.ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                resultRow = new PaymentTblModel();
                resultRow.bkng_id = resultSet.getInt("bkng_id");
                resultRow.payment_code = resultSet.getString("payment_code");
                resultRow.payment_datetime = resultSet.getTimestamp("payment_datetime");
                resultRow.total_rated_cost = BigDecimal.valueOf(resultSet.getDouble("payment_total_rated_cost"));
                resultRow.vat = BigDecimal.valueOf(resultSet.getDouble("payment_vat"));
                resultRow.senior_pwd_discount = BigDecimal.valueOf(resultSet.getDouble("payment_senior_pwd_discount"));
                resultRow.voucher_discount = BigDecimal.valueOf(resultSet.getDouble("payment_voucher_discount"));
                resultRow.total_service_fee = BigDecimal.valueOf(resultSet.getDouble("payment_total_service_fee"));
                resultRow.total_amount_due = BigDecimal.valueOf(resultSet.getDouble("payment_total_amount_due"));
                resultRow.total_paid = BigDecimal.valueOf(resultSet.getDouble("payment_total_amount_paid"));
                resultRow.total_change = BigDecimal.valueOf(resultSet.getDouble("payment_change"));
                resultRow.total_refund = BigDecimal.valueOf(resultSet.getDouble("payment_refund"));
                resultRow.total_tip = BigDecimal.valueOf(resultSet.getDouble("payment_tip"));
                results.add(resultRow);
            }
            _logger.info("Successfully retrieved all payments.");
        } catch (java.sql.SQLException e) {
            _logger.error("Error! Failed to get all payments.");
            e.printStackTrace();
        }
        return results;
    }
}
