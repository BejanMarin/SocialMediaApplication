package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    String url = "jdbc:mysql://localhost:3306/javaDatabaseCRUD_SOCIAL_M_APP";
    String username = "root";
    String password = "Just4you22";

    private static ConnectionSingleton instance = null;
    private Connection connection = null;

    private ConnectionSingleton() {

    }

    public static ConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new ConnectionSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                System.out.println("Invalid login data");
            }
        }
        return connection;
    }
}
