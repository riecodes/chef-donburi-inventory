package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3308/chefdonburiinventory";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Update password if needed

    public Connection getConnection() throws SQLException {
    Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
    System.out.println("Database connection established: " + (conn != null));
    return conn;
}

}
