package database;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection instance;
    private final Connection connection;

    private DBConnection() {
//        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms_system", "root", "Yasith@1.");
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("config.properties")); // from project root

            String url = props.getProperty("DB_URL");
            String user = props.getProperty("DB_ROOT");
            String pass = props.getProperty("DB_PASS");

            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load DB config or connect to DB");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static  DBConnection getInstance() throws SQLException {
        return instance == null ? instance = new DBConnection() : instance;
    }
}
