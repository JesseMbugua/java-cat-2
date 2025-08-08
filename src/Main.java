/**
 * Main.java
 * Entry point for the Employee Payroll Management System.
 * Currently starts from the RegisterScreen so new users can sign up first.
 */
public class Main {
    public static void main(String[] args) {
        // Optional: Test DB connection before launching UI
        if (DBConnection.getConnection() != null) {
            System.out.println("Database connection successful!");
        } else {
            System.out.println("Database connection failed. Please check settings.");
        }

        // Launch RegisterScreen first
        javax.swing.SwingUtilities.invokeLater(() -> {
            new RegisterScreen().setVisible(true);
        });
    }
}
