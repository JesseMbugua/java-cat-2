import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * LoginScreen.java
 * 
 * A simple login screen for the Employee Payroll System.
 * Connects directly to the database and checks username/password in plain text.
 * (⚠ Storing plain text passwords is NOT secure — for demo purposes only.)
 */
public class LoginScreen extends JFrame {

    // UI components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginScreen() {
        setTitle("Employee Payroll System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Create and add components
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);

        // Blank placeholder to balance the grid
        add(new JLabel(""));

        // Handle login button click
        loginButton.addActionListener(e -> handleLogin());

        setVisible(true);
    }

    /**
     * Checks if the entered username/password match a record in the database.
     */
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Basic validation
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password.");
            return;
        }

        // Query the database
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose(); // Close login window
                new Dashboard(); // Open dashboard
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }
}
