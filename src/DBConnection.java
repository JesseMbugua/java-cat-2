import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/EmployeeDB";
    static final String USER = "postgres";
    static final String PASSWORD = "ShzT8gh6";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load the PostgreSQL JDBC Driver explicitly
            Class.forName("org.postgresql.Driver");

            // Establish connection
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to db success");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed");
            e.printStackTrace();
        }
        return conn;
    }
}
