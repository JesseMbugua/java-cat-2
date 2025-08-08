import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class provides a form to add new employees to the Employee table.
 * It connects to the database and inserts the entered data when the user clicks "Save".
 */
public class AddEmployee extends JFrame {

    // UI Components
    private JTextField nameField;
    private JTextField positionField;
    private JTextField departmentField;
    private JTextField salaryField;

    /**
     * Constructor - builds the Add Employee form
     */
    public AddEmployee() {
        setTitle("Add Employee");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        setLocationRelativeTo(null); // Center on screen

        // Create form panel with labels and text fields
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        // ------------------ Name ------------------
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        // ------------------ Position ------------------
        panel.add(new JLabel("Position:"));
        positionField = new JTextField();
        panel.add(positionField);

        // ------------------ Department ------------------
        panel.add(new JLabel("Department:"));
        departmentField = new JTextField();
        panel.add(departmentField);

        // ------------------ Salary ------------------
        panel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        panel.add(salaryField);

        // ------------------ Save Button ------------------
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> saveEmployee());
        panel.add(saveBtn);

        // ------------------ Close Button ------------------
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> dispose());
        panel.add(closeBtn);

        // Add panel to frame
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Saves the employee data into the database
     */
    private void saveEmployee() {
        String name = nameField.getText().trim();
        String position = positionField.getText().trim();
        String department = departmentField.getText().trim();
        String salaryText = salaryField.getText().trim();

        // Basic validation
        if (name.isEmpty() || position.isEmpty() || department.isEmpty() || salaryText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double salary = Double.parseDouble(salaryText);

            // SQL to insert employee record
            String sql = "INSERT INTO employees (name, position, department, salary) VALUES (?, ?, ?, ?)";

            // Try-with-resources ensures connection closes automatically
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                // Set the parameter values
                stmt.setString(1, name);
                stmt.setString(2, position);
                stmt.setString(3, department);
                stmt.setDouble(4, salary);

                // Execute the insert
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Employee added successfully.");
                dispose(); // Close the window

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error saving employee: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid salary format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method for standalone testing
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddEmployee().setVisible(true);
        });
    }
}
