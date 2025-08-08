import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * RegisterScreen.java
 * 
 * This class provides a user interface for registering a new account.
 * Users must enter a username, password, and confirm their password.
 * Once registered, they can log in from the LoginScreen.
 */
public class RegisterScreen extends JFrame {

    // UI Components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public RegisterScreen() {
        setTitle("Register New Account");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel to hold input fields
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Username
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        // Password
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        // Confirm Password
        panel.add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        // Register Button
        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(e -> registerUser());
        panel.add(registerBtn);

        // Back to Login Button
        JButton backBtn = new JButton("Back to Login");
        backBtn.addActionListener(e -> {
            dispose(); // Close registration screen
            new LoginScreen().setVisible(true); // Show login screen
        });
        panel.add(backBtn);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Registers a new user into the database.
     */
    private void registerUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Validate inputs
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.");
            return;
        }

        // Insert into DB
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, HashUtil.hashPassword(password)); // hash before saving

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Registration successful! You can now log in.");
                dispose();
                new LoginScreen().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Database connection failed.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    /**
     * Main method for standalone testing.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegisterScreen().setVisible(true);
        });
    }
}
