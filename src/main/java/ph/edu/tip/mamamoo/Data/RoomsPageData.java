package ph.edu.tip.mamamoo.Data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Models.BookARoomCellModel;
import ph.edu.tip.mamamoo.Models.BookingsOfRoomModel;
import ph.edu.tip.mamamoo.Models.RoomAmenitiesModel;
import ph.edu.tip.mamamoo.Utilities.DotEnvUtility;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class RoomsPageData {
    final Logger _logger = LogManager.getLogger();
    String connectionString;
    public RoomsPageData() {
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

    public ArrayList<BookARoomCellModel> getBookARoomCellsInfo() {
        ArrayList<BookARoomCellModel> results = new ArrayList<BookARoomCellModel>();
        BookARoomCellModel resultRow;
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_getAllRoomsInfo}";
            CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);

            callableStatement.execute();

            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                resultRow = new BookARoomCellModel();
                resultRow.id = resultSet.getInt("room_id");
                resultRow.num = resultSet.getString("room_num");
                resultRow.name = resultSet.getString("room_name");
                resultRow.type = resultSet.getString("room_type_name");
                resultRow.desc = resultSet.getString("room_desc");
                resultRow.rate = resultSet.getString("room_rate_type");
                resultRow.price = resultSet.getBigDecimal("room_rate");
                results.add(resultRow);
            }
            _logger.info("Successfully retrieved all rooms info.");
        } catch (SQLException e) {
            _logger.error("Error! Failed to get all rooms info.");
            e.printStackTrace();
        }
        for (BookARoomCellModel room : results) {
            room.image = getRoomThumbnail(room.id);
            room.amenities = getRoomAmenitiesInRoom(room.id);
        }

        return results;
    }

    public ArrayList<RoomAmenitiesModel> getRoomAmenitiesInRoom(int roomId) {
        ArrayList<RoomAmenitiesModel> results = new ArrayList<RoomAmenitiesModel>();
        RoomAmenitiesModel resultRow;
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_getAllAmenitiesInRoom(?)}";
            CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.setInt(1, roomId);

            callableStatement.execute();

            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                resultRow = new RoomAmenitiesModel();
                resultRow.roomAmenityName = resultSet.getString("amnty_name");
                resultRow.roomAmenityCount = resultSet.getInt("amnty_count");
                resultRow.roomAmenityDescription = resultSet.getString("amnty_desc");
                results.add(resultRow);
            }
            _logger.info("Successfully retrieved all room amenities in room.");
        } catch (SQLException e) {
            _logger.error("Error! Failed to get all room amenities in room.");
            e.printStackTrace();
        }
        return results;
    }

    public Image getRoomThumbnail(int roomId) {
        Image result = null;
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_getRoomThumbnail(?)}";
            CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.setInt(1, roomId);

            callableStatement.execute();

            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                byte[] imageBytes = resultSet.getBytes("room_pic_img");
                result = Toolkit.getDefaultToolkit().createImage(imageBytes);
            }
            _logger.info("Successfully retrieved room thumbnail.");
        } catch (SQLException e) {
            _logger.error("Error! Failed to get room thumbnail.");
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<BookingsOfRoomModel> getBookingsOfRoom(int roomId) {
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            String storedProcedureCall = "{call HotelAppDB.dbo.usp_getBookingsOfRoom(?)}";
            CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
            callableStatement.setInt(1, roomId);

            callableStatement.execute();

            ResultSet resultSet = callableStatement.getResultSet();
            ArrayList<BookingsOfRoomModel> results = new ArrayList<BookingsOfRoomModel>();
            BookingsOfRoomModel resultRow;
            while (resultSet.next()) {
                resultRow = new BookingsOfRoomModel();
                resultRow.id = resultSet.getInt("bkng_id");
                resultRow.checkIn = resultSet.getTimestamp("check_in_datetime");
                resultRow.checkOut = resultSet.getTimestamp("check_out_datetime");
                resultRow.status = resultSet.getString("status");
                results.add(resultRow);
            }
            _logger.info("Successfully retrieved bookings of room.");
            return results;
        } catch (SQLException e) {
            _logger.error("Error! Failed to get bookings of room.");
            e.printStackTrace();
            return null;
        }
    }
}
