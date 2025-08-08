import javax.swing.*;
import java.awt.*;

/**
 * Dashboard.java
 * 
 * This class represents the main menu screen shown after a successful login.
 * It provides buttons to navigate to other parts of the payroll system:
 * - Add Employee
 * - Add Payroll
 * - View Employees
 * - View Payrolls
 * - Logout
 */
public class Dashboard extends JFrame {

    /**
     * Constructor for Dashboard
     * @param username the name of the user who logged in
     */
    public Dashboard(String username) {
        // Set the window title to show logged-in user
        setTitle("Dashboard - Logged in as " + username);

        // Define window size
        setSize(400, 300);

        // Close the app when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window on screen
        setLocationRelativeTo(null);

        // Create a panel to hold the buttons
        JPanel panel = new JPanel();

        // Arrange buttons in a grid layout (5 rows, 1 column)
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        // ------------------ Add Employee Button ------------------
        JButton addEmployeeBtn = new JButton("Add Employee");
        addEmployeeBtn.addActionListener(e -> {
            // Open the AddEmployee window
            new AddEmployee().setVisible(true);
        });
        panel.add(addEmployeeBtn);

        // ------------------ Add Payroll Button -------------------
        JButton addPayrollBtn = new JButton("Add Payroll");
        addPayrollBtn.addActionListener(e -> {
            // Open the AddPayroll window
            new AddPayroll().setVisible(true);
        });
        panel.add(addPayrollBtn);

        // ------------------ View Employees Button ----------------
        JButton viewEmployeesBtn = new JButton("View Employees");
        viewEmployeesBtn.addActionListener(e -> {
            // Open the ViewEmployees window
            new ViewEmployees().setVisible(true);
        });
        panel.add(viewEmployeesBtn);

        // ------------------ View Payrolls Button -----------------
        JButton viewPayrollsBtn = new JButton("View Payrolls");
        viewPayrollsBtn.addActionListener(e -> {
            // Open the ViewPayrolls window
            new ViewPayrolls().setVisible(true);
        });
        panel.add(viewPayrollsBtn);

        // ------------------ Logout Button ------------------------
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> {
            // Close dashboard
            dispose();
            // Show login screen again
            new LoginScreen().setVisible(true);
        });
        panel.add(logoutBtn);

        // Add panel with buttons to the center of the frame
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Main method for standalone testing
     * Opens the dashboard directly with a test user.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Dashboard("admin").setVisible(true);
        });
    }
}
 