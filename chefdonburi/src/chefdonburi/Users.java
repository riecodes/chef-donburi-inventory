package chefdonburi;

import Database.Database;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.security.crypto.bcrypt.BCrypt;

public final class Users implements ActionListener {
    private JFrame frmUserManagement;
    private JTable userTable;
    private DefaultTableModel model;
    private JButton btnAdd, btnEdit, btnDelete, btnRefresh;

    // Database connection variables
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public Users() {
        init();
    }

    void init() {
        frmUserManagement = new JFrame("User Management");
        ImageIcon frameicon = new ImageIcon("src\\images\\jframeicon.jpg");
        Image frame = frameicon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        frmUserManagement.setIconImage(frame);
        frmUserManagement.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        frmUserManagement.setSize(800, 600);
        frmUserManagement.setLocationRelativeTo(null);

        // Table model
        model = new DefaultTableModel(new String[]{"ID", "Username", "First Name", "Last Name", "Role"}, 0);
        userTable = new JTable(model);

        // Style the table header
        JTableHeader tableHeader = userTable.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14));
        tableHeader.setBackground(new Color(223, 49, 42));
        tableHeader.setForeground(new Color(242, 245, 224));

        // Style the table cells
        userTable.setFont(new Font("Arial", Font.PLAIN, 14));
        userTable.setRowHeight(30); // Adjust row height for better readability
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the text
        for (int i = 0; i < userTable.getColumnCount(); i++) {
            userTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(userTable);
        frmUserManagement.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel btnPanel = new JPanel();

        btnAdd = new JButton("Add User");
        btnAdd.setForeground(new Color(242, 245, 224));
        btnAdd.setBackground(new Color(223, 49, 42));
        btnAdd.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdd.addActionListener(this);
        btnPanel.add(btnAdd);

        btnEdit = new JButton("Edit User");
        btnEdit.setForeground(new Color(242, 245, 224));
        btnEdit.setBackground(new Color(223, 49, 42));
        btnEdit.setFont(new Font("Arial", Font.BOLD, 14));
        btnEdit.addActionListener(this);
        btnPanel.add(btnEdit);

        btnDelete = new JButton("Delete User");
        btnDelete.setForeground(new Color(242, 245, 224));
        btnDelete.setBackground(new Color(223, 49, 42));
        btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
        btnDelete.addActionListener(this);
        btnPanel.add(btnDelete);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setForeground(new Color(242, 245, 224));
        btnRefresh.setBackground(new Color(223, 49, 42));
        btnRefresh.setFont(new Font("Arial", Font.BOLD, 14));
        btnRefresh.addActionListener(this);
        btnPanel.add(btnRefresh);

        frmUserManagement.add(btnPanel, BorderLayout.SOUTH);

        loadUserTable(); // Load users into the table
        frmUserManagement.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            addUser();
        } else if (e.getSource() == btnEdit) {
            editUser();
        } else if (e.getSource() == btnDelete) {
            deleteUser();
        } else if (e.getSource() == btnRefresh) {
            loadUserTable();
        }
    }

    private void loadUserTable() {
        model.setRowCount(0); // Clear existing rows
        try {
            connection = new Database().getConnection();
            String query = "SELECT userID, userName, firstName, lastName, role FROM users";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("userID"),
                        rs.getString("userName"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("role")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frmUserManagement, "Error loading users: " + e.getMessage());
        } finally {
            closeConnections();
        }
    }

  

private void addUser() {
    // Define a custom font size
   

    // Create input fields for user data
    JTextField txtUserName = new JTextField();
    JTextField txtFirstName = new JTextField();
    JTextField txtLastName = new JTextField();
    JComboBox<String> cmbRole = new JComboBox<>(new String[]{"Admin", "User"});
    JPasswordField txtPassword = new JPasswordField();
 
    // Create an error label (initially hidden)
    JLabel errorLabel = new JLabel();
    errorLabel.setForeground(Color.RED);
    errorLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font for the error label
    errorLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the error label
    errorLabel.setVisible(false); // Initially, the label is hidden

    // Add all fields to a single array for JOptionPane
    Object[] message = {
        "Username:", txtUserName,
        "First Name:", txtFirstName,
        "Last Name:", txtLastName,
        "Role:", cmbRole,
        "Password:", txtPassword,
         errorLabel // Add error label to message
    };

    boolean isValid = false; // Flag to track if validation is successful

    while (!isValid) {
        int option = JOptionPane.showConfirmDialog(frmUserManagement, message, "Add User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
            return; // Exit the method if Cancel or X is pressed
        }

        // Reset error label visibility and text
        errorLabel.setVisible(false);
        errorLabel.setText("");

        // Validation checks
        if (txtUserName.getText().trim().isEmpty() ||
                txtFirstName.getText().trim().isEmpty() ||
                txtLastName.getText().trim().isEmpty() ||
                txtPassword.getPassword().length == 0) {
            errorLabel.setText("All fields are required.");
            errorLabel.setVisible(true); // Show the error label
            continue; // Continue the loop and keep the dialog open
        }

        // Check if the username already exists
        if (isUsernameTaken(txtUserName.getText())) {
            errorLabel.setText("This username is already taken.");
            errorLabel.setVisible(true); // Show the error label
            continue; // Continue the loop and keep the dialog open
        }

        // If validation passes, proceed with user creation
        String hashedPassword = BCrypt.hashpw(new String(txtPassword.getPassword()), BCrypt.gensalt()); // Hash the password

        try {
            connection = new Database().getConnection();
            String query = "INSERT INTO users (userName, firstname, lastname, role, userPassword) VALUES (?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, txtUserName.getText());
            ps.setString(2, txtFirstName.getText());
            ps.setString(3, txtLastName.getText());
            ps.setString(4, cmbRole.getSelectedItem().toString());
            ps.setString(5, hashedPassword); // Store hashed password
            ps.executeUpdate();
            JOptionPane.showMessageDialog(frmUserManagement, "User added successfully.");
            loadUserTable();
            isValid = true; // Set flag to exit loop
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frmUserManagement, "Error adding user: " + e.getMessage());
        } finally {
            closeConnections();
        }
    }
}




    private boolean isUsernameTaken(String username) {
        try {
            connection = new Database().getConnection();
            String query = "SELECT COUNT(*) FROM users WHERE userName = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // If count > 0, username is taken
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frmUserManagement, "Error checking username: " + e.getMessage());
        } finally {
            closeConnections();
        }
        return false;
    }

    
    
private void editUser() {
    int selectedRow = userTable.getSelectedRow();
    if (selectedRow >= 0) {
        int userId = (int) model.getValueAt(selectedRow, 0);
        String currentUserName = (String) model.getValueAt(selectedRow, 1);
        String currentFirstName = (String) model.getValueAt(selectedRow, 2);
        String currentLastName = (String) model.getValueAt(selectedRow, 3);
        String currentRole = (String) model.getValueAt(selectedRow, 4);

        JTextField txtUserName = new JTextField(currentUserName);
        JTextField txtFirstName = new JTextField(currentFirstName);
        JTextField txtLastName = new JTextField(currentLastName);
        JComboBox<String> cmbRole = new JComboBox<>(new String[]{"Admin", "User"});
        cmbRole.setSelectedItem(currentRole);
        JPasswordField txtCurrentPassword = new JPasswordField();
        JPasswordField txtNewPassword = new JPasswordField();

        // Create an error label (initially hidden)
        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font for the error label
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the error label
        errorLabel.setVisible(false); // Initially, the label is hidden

        // Add all fields to a single array for JOptionPane, including the error label
        Object[] message = {
            "Username:", txtUserName,
            "First Name:", txtFirstName,
            "Last Name:", txtLastName,
            "Role:", cmbRole,
            "Current Password:", txtCurrentPassword,
            "New Password (Leave blank to keep unchanged):", txtNewPassword,
            errorLabel // Add error label to message
        };

        boolean isValid = false; // Flag to track if validation is successful

        while (!isValid) {
            int option = JOptionPane.showConfirmDialog(frmUserManagement, message, "Edit User", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                return; // Exit the method if Cancel or X is pressed
            }

            // Reset error label visibility and text
            errorLabel.setVisible(false);
            errorLabel.setText("");

            String newUserName = txtUserName.getText().trim();

            // Check if the username is taken
            if (!newUserName.equals(currentUserName) && isUsernameTaken(newUserName)) {
                errorLabel.setText("This username is already taken.");
                errorLabel.setVisible(true); // Show the error label
                continue; // Keep the dialog open
            }

            // Check if fields are empty
            if (newUserName.isEmpty() || txtFirstName.getText().trim().isEmpty() || txtLastName.getText().trim().isEmpty()) {
                errorLabel.setText("Username, First Name, and Last Name are required.");
                errorLabel.setVisible(true); // Show the error label
                continue; // Keep the dialog open
            }

            // Check if current password is provided
            String currentPassword = new String(txtCurrentPassword.getPassword()).trim();
            if (currentPassword.isEmpty()) {
                errorLabel.setText("Current password is required.");
                errorLabel.setVisible(true); // Show the error label
                continue; // Keep the dialog open
            }

            // Check if the current password is correct
            if (!isCurrentPasswordCorrect(userId, currentPassword)) {
                errorLabel.setText("Current password is incorrect.");
                errorLabel.setVisible(true); // Show the error label
                continue; // Keep the dialog open
            }

            // Handle password update logic
            String password = new String(txtNewPassword.getPassword()).trim();
            String hashedPassword = password.isEmpty() ? getCurrentPassword(userId) : BCrypt.hashpw(password, BCrypt.gensalt()); // Hash the password

            // If validation passes, proceed with user update logic
            try {
                connection = new Database().getConnection();
                String query = "UPDATE users SET userName = ?, firstname = ?, lastname = ?, role = ?, userPassword = ? WHERE userID = ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, newUserName);
                ps.setString(2, txtFirstName.getText());
                ps.setString(3, txtLastName.getText());
                ps.setString(4, cmbRole.getSelectedItem().toString());
                ps.setString(5, hashedPassword); // Store hashed password
                ps.setInt(6, userId); // Update the correct user

                ps.executeUpdate();
                JOptionPane.showMessageDialog(frmUserManagement, "User updated successfully."); // Show success message
                loadUserTable();
                isValid = true; // Exit the loop when validation is successful
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(frmUserManagement, "Error updating user: " + e.getMessage());
            } finally {
                closeConnections();
            }
        }
    } else {
        JOptionPane.showMessageDialog(frmUserManagement, "Please select a user to edit.");
    }
}

// Method to check if the current password is correct
private boolean isCurrentPasswordCorrect(int userId, String currentPassword) {
    try {
        connection = new Database().getConnection();
        String query = "SELECT userPassword FROM users WHERE userID = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, userId);
        rs = ps.executeQuery();

        if (rs.next()) {
            String storedPassword = rs.getString("userPassword");
            return BCrypt.checkpw(currentPassword, storedPassword); // Compare the entered password with the stored hashed password
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(frmUserManagement, "Error checking current password: " + e.getMessage());
    } finally {
        closeConnections();
    }
    return false; // Return false if there's an error or password doesn't match
}

private String getCurrentPassword(int userId) {
    try {
        connection = new Database().getConnection();
        String query = "SELECT userPassword FROM users WHERE userID = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, userId);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            return rs.getString("userPassword"); // Return the current password
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(frmUserManagement, "Error retrieving current password: " + e.getMessage());
    } finally {
        closeConnections();
    }
    return null; // Return null if there's an issue
}





    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow >= 0) {
            int userId = (int) model.getValueAt(selectedRow, 0);
            try {
                connection = new Database().getConnection();
                String query = "DELETE FROM users WHERE userID = ?";
                ps = connection.prepareStatement(query);
                ps.setInt(1, userId);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(frmUserManagement, "User deleted successfully.");
                loadUserTable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(frmUserManagement, "Error deleting user: " + e.getMessage());
            } finally {
                closeConnections();
            }
        } else {
            JOptionPane.showMessageDialog(frmUserManagement, "Please select a user to delete.");
        }
    }


  private void closeConnections() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frmUserManagement, "Error closing resources: " + e.getMessage());
        }
    }
}