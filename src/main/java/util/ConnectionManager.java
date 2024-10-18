package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String DB_URL = PropertiesUtil.get("db.url");
    private static final String DB_LOGIN = PropertiesUtil.get("db.login");
    private static final String DB_PASSWORD = PropertiesUtil.get("db.password");

    
    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

            throw new RuntimeException("Configuration not found!", e);
        }
    }

    private ConnectionManager() {
    }

    public static Connection get() {
        try {
            return DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
