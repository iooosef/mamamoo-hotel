package ph.edu.tip.mamamoo.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SqlServerInit {
    public static void TestSqlConn() {
        final Logger _logger = LogManager.getLogger();

        String connectionString =
                "jdbc:sqlserver://DESKTOP-39FLT9L\\IOOOSEFDB;DatabaseName=testConn;IntegratedSecurity=true;trustServerCertificate=true";
        try{
            try (Connection conn = DriverManager.getConnection(connectionString)) {
                _logger.info("Successfully connected to MS SQL Server!");
            }
        } catch (SQLException e) {
            _logger.error("Error! Failed to connect to the database.");
            e.printStackTrace();
        }

    }
}
