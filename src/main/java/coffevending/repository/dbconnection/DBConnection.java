package coffevending.repository.dbconnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private final String DB_PROPERTIES = "resources/database/db.properties";

    public Connection getDbConnection() throws SQLException, IOException {
        Properties property = new Properties();
        property.load(new FileInputStream(DB_PROPERTIES));
        Connection db = DriverManager.getConnection(property.getProperty("url"), property);
        return db;
    }
}