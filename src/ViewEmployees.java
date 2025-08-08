import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ViewEmployees.java
 *
 * Displays all employees in a JTable by fetching records from the Employee table.
 */
public class ViewEmployees extends JFrame {

    private JTable employeeTable; // Table to display employees
    private DefaultTableModel tableModel; // Model for the JTable

    /**
     * Constructor - builds the employee list window
     */
    public ViewEmployees() {
        setTitle("Employee List");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this window
        setLocationRelativeTo(null); // Center on screen

        // Column names for the table
        String[] columnNames = {"ID", "Name", "Position", "Department"};

        // Table model (non-editable cells)
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Prevent editing
            }
        };

        // Create table with the model
        employeeTable = new JTable(tableModel);

        // Add table to scroll pane so it's scrollable
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        // Add scroll pane to frame
        add(scrollPane, BorderLayout.CENTER);

        // Load data from the database
        loadEmployeeData();
    }

    /**
     * Loads employee data from the database into the JTable
     */
    private void loadEmployeeData() {
        String sql = "SELECT id, name, position, department FROM employee";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Clear old rows
            tableModel.setRowCount(0);

            // Add rows to the table model
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String position = rs.getString("position");
                String department = rs.getString("department");

                tableModel.addRow(new Object[]{id, name, position, department});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading employees: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method for standalone testing
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewEmployees().setVisible(true);
        });
    }
}
