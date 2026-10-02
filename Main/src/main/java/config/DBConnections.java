package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnections {

    private static final String URL =
            "jdbc:mysql://localhost:3306/college";

    private static final String USERNAME = "root";

    private static final String PASSWORD =
            "vamsi@143";

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USERNAME,
                PASSWORD
        );
    }
}