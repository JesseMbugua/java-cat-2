import javax.swing.*;
import java.awt.*;

/**
 * Dashboard.java
 * 
 * Main menu screen shown after successful login.
 * Provides navigation to:
 * - Add Employee
 * - Add Payroll
 * - View Employees
 * - View Payrolls
 * - Logout
 */
public class Dashboard extends JFrame {

    /**
     * Constructor for Dashboard
     * @param username The name of the logged-in user
     */
    public Dashboard(String username) {
        // Set up window title and size
        setTitle("Dashboard - Logged in as " + username);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window

        // Create button panel with vertical layout
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

        // ------------------ Add Employee ------------------
        JButton addEmployeeBtn = new JButton("Add Employee");
        addEmployeeBtn.addActionListener(e -> new AddEmployee().setVisible(true));
        panel.add(addEmployeeBtn);

        // ------------------ Add Payroll -------------------
        JButton addPayrollBtn = new JButton("Add Payroll");
        addPayrollBtn.addActionListener(e -> new AddPayroll().setVisible(true));
        panel.add(addPayrollBtn);

        // ------------------ View Employees ----------------
        JButton viewEmployeesBtn = new JButton("View Employees");
        viewEmployeesBtn.addActionListener(e -> new ViewEmployees().setVisible(true));
        panel.add(viewEmployeesBtn);

        // ------------------ View Payrolls -----------------
        JButton viewPayrollsBtn = new JButton("View Payrolls");
        viewPayrollsBtn.addActionListener(e -> new ViewPayrolls().setVisible(true));
        panel.add(viewPayrollsBtn);

        // ------------------ Logout ------------------------
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> {
            dispose(); // Close dashboard
            new LoginScreen().setVisible(true); // Return to login
        });
        panel.add(logoutBtn);

        // Add panel to frame
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Standalone test entry point
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard("admin").setVisible(true));
    }
}
