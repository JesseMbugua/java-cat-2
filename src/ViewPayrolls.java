import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ViewPayrolls.java
 *
 * Displays all payroll records in a JTable by fetching from the Payroll table.
 */
public class ViewPayrolls extends JFrame {

    private JTable payrollTable; // Table to display payroll records
    private DefaultTableModel tableModel; // Model for the JTable

    /**
     * Constructor - builds the payroll list window
     */
    public ViewPayrolls() {
        setTitle("Payroll List");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this window
        setLocationRelativeTo(null); // Center on screen

        // Column names for the table
        String[] columnNames = {"ID", "Employee ID", "Amount", "Pay Date"};

        // Table model (non-editable cells)
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Prevent editing
            }
        };

        // Create table with the model
        payrollTable = new JTable(tableModel);

        // Add table to scroll pane so it's scrollable
        JScrollPane scrollPane = new JScrollPane(payrollTable);

        // Add scroll pane to frame
        add(scrollPane, BorderLayout.CENTER);

        // Load data from the database
        loadPayrollData();
    }

    /**
     * Loads payroll data from the database into the JTable
     */
    private void loadPayrollData() {
        String sql = "SELECT id, employee_id, amount, pay_date FROM payroll";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Clear old rows
            tableModel.setRowCount(0);

            // Add rows to the table model
            while (rs.next()) {
                int id = rs.getInt("id");
                int employeeId = rs.getInt("employee_id");
                double amount = rs.getDouble("amount");
                String payDate = rs.getString("pay_date");

                tableModel.addRow(new Object[]{id, employeeId, amount, payDate});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading payrolls: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method for standalone testing
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewPayrolls().setVisible(true);
        });
    }
}
