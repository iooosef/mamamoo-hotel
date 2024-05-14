package ph.edu.tip.mamamoo.Data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Models.BookARoomCellModel;
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
                resultRow.roomId = resultSet.getInt("room_id");
                resultRow.roomNum = resultSet.getString("room_num");
                resultRow.roomName = resultSet.getString("room_name");
                resultRow.roomType = resultSet.getString("room_type_name");
                resultRow.roomDesc = resultSet.getString("room_desc");
                resultRow.roomRate = resultSet.getString("room_rate_type");
                resultRow.roomPrice = resultSet.getBigDecimal("room_rate");
                results.add(resultRow);
            }
            _logger.info("Successfully retrieved all rooms info.");
        } catch (SQLException e) {
            _logger.error("Error! Failed to get all rooms info.");
            e.printStackTrace();
        }
        for (BookARoomCellModel room : results) {
            room.roomImage = getRoomThumbnail(room.roomId);
            room.roomAmenities = getRoomAmenitiesInRoom(room.roomId);
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
}
