import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * LoginScreen.java
 * 
 * Provides the login UI for the payroll system.
 * Allows users to enter their username and password to log in.
 * Also provides a button to register a new account.
 */
public class LoginScreen extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {
        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Username
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        // Password
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> loginUser());
        panel.add(loginBtn);

        // Register Button
        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterScreen().setVisible(true);
        });
        panel.add(registerBtn);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Checks credentials and logs in the user.
     */
    private void loginUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "SELECT password FROM users WHERE username = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    if (HashUtil.checkPassword(password, storedHash)) {
                        JOptionPane.showMessageDialog(this, "Login successful!");
                        dispose();
                        new Dashboard(username).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid username or password.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "User not found.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Database connection failed.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
