package DB;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MySQLConnect {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/onlinestore?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Touchnet#1";

    private Connection connection;
    private Properties properties;

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }

    @SneakyThrows
    public Connection connect() {
        if (connection == null) {
            connection = DriverManager.getConnection(DATABASE_URL, getProperties());
        }
        return connection;
    }

    @SneakyThrows
    public void disconnect() {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}
