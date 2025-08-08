import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * AddPayroll.java
 *
 * This class provides a form to add payroll records to the Payroll table.
 * It links a payroll entry to an existing employee using the Employee ID.
 */
public class AddPayroll extends JFrame {

    // UI Components
    private JTextField employeeIdField;
    private JTextField amountField;
    private JTextField payDateField;

    /**
     * Constructor - builds the Add Payroll form
     */
    public AddPayroll() {
        setTitle("Add Payroll");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this window
        setLocationRelativeTo(null); // Center on screen

        // Create form layout (4 rows, 2 columns, with spacing)
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        // ------------------ Employee ID ------------------
        panel.add(new JLabel("Employee ID:"));
        employeeIdField = new JTextField();
        panel.add(employeeIdField);

        // ------------------ Amount ------------------
        panel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        panel.add(amountField);

        // ------------------ Pay Date ------------------
        panel.add(new JLabel("Pay Date (YYYY-MM-DD):"));
        payDateField = new JTextField(LocalDate.now().toString()); // Default: today's date
        panel.add(payDateField);

        // ------------------ Save Button ------------------
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> savePayroll());
        panel.add(saveBtn);

        // ------------------ Close Button ------------------
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> dispose());
        panel.add(closeBtn);

        // Add panel to frame
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Saves payroll record into the database
     */
    private void savePayroll() {
        String employeeIdText = employeeIdField.getText().trim();
        String amountText = amountField.getText().trim();
        String payDate = payDateField.getText().trim();

        // Basic validation
        if (employeeIdText.isEmpty() || amountText.isEmpty() || payDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int employeeId = Integer.parseInt(employeeIdText);
            double amount = Double.parseDouble(amountText);

            // SQL to insert payroll record
            String sql = "INSERT INTO payroll (employee_id, amount, pay_date) VALUES (?, ?, ?)";

            // Try-with-resources ensures connection is closed automatically
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                // Set parameters for the query
                stmt.setInt(1, employeeId);
                stmt.setDouble(2, amount);
                stmt.setDate(3, java.sql.Date.valueOf(payDate));

                // Execute insert
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Payroll record added successfully.");
                dispose(); // Close form

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error saving payroll: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Employee ID or Amount format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method for standalone testing
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddPayroll().setVisible(true);
        });
    }
}
