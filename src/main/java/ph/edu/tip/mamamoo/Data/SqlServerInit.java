package ph.edu.tip.mamamoo.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ph.edu.tip.mamamoo.Utilities.DotEnvUtility;

public class SqlServerInit {
    public static void TestSqlConn() {
        final Logger _logger = LogManager.getLogger();

        DotEnvUtility dotenv = DotEnvUtility.configure().load();
        String connectionString = dotenv.get("testConnStr");

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
