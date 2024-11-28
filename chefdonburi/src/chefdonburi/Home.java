package chefdonburi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public final class Home implements ActionListener {
    JFrame frmHome;
    JPanel pnlHome;
    JLabel lblWelcome;
    JButton btnUsers, btnInventory, btnExpenses, btnSuppliers, btnReports, btnLogout, btnLogs;
    
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnRefresh, btnDelete;

    // Database connection variables
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;
    // Background image
    private Image backgroundImage;

    // User ID (Passed from the Login class)
    private int userID;
    private String role; // Role to check if the user is admin or not

    // Constructor for the Home class, accepts the role and userID
    public Home(String role, int userID) {
        this.userID = userID; // Receive userID from login
        this.role = role; // Receive role from login (admin or user)

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("src\\Images\\home_bg.jpg"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading background image: " + e.getMessage());
        }
        init(); // Initialize the UI
    }

    public void init() {
        frmHome = new JFrame("Chef Donburi Home");
        ImageIcon frameicon = new ImageIcon("src\\Images\\jframeicon.jpg");
        Image frame = frameicon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        frmHome.setIconImage(frame);
        frmHome.setSize(1266, 668);
        frmHome.setLocationRelativeTo(null);
        frmHome.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent closing immediately

        // Add window listener to handle the close button action
        frmHome.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmAndLogout(); // Confirm logout and log event
            }
        });

        pnlHome = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        pnlHome.setLayout(null); // Set layout to null for manual positioning

        // Welcome label
        lblWelcome = new JLabel("Welcome to Inventory!");
        lblWelcome.setForeground(new Color(223, 49, 42));
        lblWelcome.setFont(new Font("Georgia", Font.BOLD, 40));
        lblWelcome.setSize(600, 50);
        lblWelcome.setLocation(370, 20); // Adjusted position
        pnlHome.add(lblWelcome);

        // Users button (only if role is not "user")
        ImageIcon usersicon = new ImageIcon("src\\Images\\user.png");
        Image users = usersicon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        if (!role.equalsIgnoreCase("user")) {
            btnUsers = new JButton("Users", new ImageIcon(users));
            btnUsers.setForeground(new Color(242, 245, 224));
            btnUsers.setBackground(new Color(223, 49, 42));
            btnUsers.setFont(new Font("Georgia", Font.BOLD, 20));
            btnUsers.setSize(180, 50);
            btnUsers.setLocation(50, 100); // Set location based on your desired layout
            btnUsers.addActionListener(this);
            pnlHome.add(btnUsers);
        }

        // Logs button (only for admin)
        if (role.equalsIgnoreCase("admin")) {
            ImageIcon logsicon = new ImageIcon("src\\Images\\logs.png");
            Image logs = logsicon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            btnLogs = new JButton("Logs", new ImageIcon(logs));
            btnLogs.setForeground(new Color(242, 245, 224));
            btnLogs.setBackground(new Color(223, 49, 42));
            btnLogs.setFont(new Font("Georgia", Font.BOLD, 20));
            btnLogs.setSize(180, 50);
            btnLogs.setLocation(50, 30); // Set location
            btnLogs.addActionListener(this);
            pnlHome.add(btnLogs);
        }

        // Inventory button
        ImageIcon inventoryicon = new ImageIcon("src\\Images\\inventory.png");
        Image inventory = inventoryicon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        btnInventory = new JButton("Inventory", new ImageIcon(inventory));
        btnInventory.setForeground(new Color(242, 245, 224));
        btnInventory.setBackground(new Color(223, 49, 42));
        btnInventory.setFont(new Font("Georgia", Font.BOLD, 20));
        btnInventory.setSize(180, 50);
        btnInventory.setLocation(250, 100); // Set location
        btnInventory.addActionListener(this);
        pnlHome.add(btnInventory);

        // Expenses button
        ImageIcon expensesicon = new ImageIcon("src\\Images\\expenses.png");
        Image expenses = expensesicon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        btnExpenses = new JButton("Expenses", new ImageIcon(expenses));
        btnExpenses.setForeground(new Color(242, 245, 224));
        btnExpenses.setBackground(new Color(223, 49, 42));
        btnExpenses.setFont(new Font("Georgia", Font.BOLD, 20));
        btnExpenses.setSize(180, 50);
        btnExpenses.setLocation(450, 100); // Set location
        btnExpenses.addActionListener(this);
        pnlHome.add(btnExpenses);

        // Suppliers button
        ImageIcon suppliersicon = new ImageIcon("src\\Images\\suppliers.png");
        Image suppliers = suppliersicon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        btnSuppliers = new JButton("Suppliers", new ImageIcon(suppliers));
        btnSuppliers.setForeground(new Color(242, 245, 224));
        btnSuppliers.setBackground(new Color(223, 49, 42));
        btnSuppliers.setFont(new Font("Georgia", Font.BOLD, 20));
        btnSuppliers.setSize(180, 50);
        btnSuppliers.setLocation(650, 100); // Set location
        btnSuppliers.addActionListener(this);
        pnlHome.add(btnSuppliers);

        // Reports button
        ImageIcon reportsicon = new ImageIcon("src\\Images\\reports.png");
        Image reports = reportsicon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        btnReports = new JButton("Reports", new ImageIcon(reports));
        btnReports.setForeground(new Color(242, 245, 224));
        btnReports.setBackground(new Color(223, 49, 42));
        btnReports.setFont(new Font("Georgia", Font.BOLD, 20));
        btnReports.setSize(180, 50);
        btnReports.setLocation(850, 100); // Set location
        btnReports.addActionListener(this);
        pnlHome.add(btnReports);

        // Logout button
        ImageIcon logouticon = new ImageIcon("src\\Images\\logout.png");
        Image logout = logouticon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        btnLogout = new JButton("Logout", new ImageIcon(logout));
        btnLogout.setForeground(new Color(242, 245, 224));
        btnLogout.setBackground(new Color(223, 49, 42));
        btnLogout.setFont(new Font("Georgia", Font.BOLD, 20));
        btnLogout.setSize(180, 50);
        btnLogout.setLocation(1050, 100); // Set location
        btnLogout.addActionListener(this);
        pnlHome.add(btnLogout);

        // Create the table to display logs
        String[] columns = {"Log ID", "Username", "Login Time", "Logout Time"};
        model = new DefaultTableModel(columns, 0); // Initialize table with no data
        table = new JTable(model);

        // Set table font and row height
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(10);

        // Center align table values
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set header font
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(table.getPreferredSize().width, 30));
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14)); // Bold Arial for header
        tableHeader.setBackground(new Color(223, 49, 42));
        tableHeader.setForeground(new Color(242, 245, 224));

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Set the position of the table below the buttons (adjust the Y position)
        scrollPane.setBounds(50, 200, 1166, 300); // Set the x, y, width, and height manually
        pnlHome.add(scrollPane); // Add scrollPane (which contains the table) to the panel

        // Create buttons: Refresh and Delete
        btnRefresh = new JButton("Refresh");
        btnRefresh.setForeground(new Color(242, 245, 224));
        btnRefresh.setBackground(new Color(223, 49, 42));
        btnRefresh.setFont(new Font("Arial", Font.BOLD, 14));

        btnDelete = new JButton("Delete");
        btnDelete.setForeground(new Color(242, 245, 224));
        btnDelete.setBackground(new Color(223, 49, 42));
        btnDelete.setFont(new Font("Arial", Font.BOLD, 14));

        // Add buttons to a button panel and position it at the bottom of the frame
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnRefresh);
        btnPanel.add(btnDelete);
        btnPanel.setBounds(50, 520, 1166, 50); // Manually set the position of the button panel
        pnlHome.add(btnPanel);

        loadAlerts(model); // Load the logs from the database

        // Add action listeners for the buttons
        btnRefresh.addActionListener(e -> loadAlerts(model));
        btnDelete.addActionListener(e -> deleteAlerts());

        frmHome.add(pnlHome);
        frmHome.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnUsers) {
            // Open the Users management window
            Users users = new Users();
        } else if (e.getSource() == btnLogs) {
            if (role.equalsIgnoreCase("admin")) {
                // Open the Logs window if the user is admin
                Logs logs = new Logs();
            } else {
                // Show error if the user is not an admin
                JOptionPane.showMessageDialog(frmHome, "You do not have permission to view the logs.");
            }
        } else if (e.getSource() == btnInventory) {
            Inventory inventory = new Inventory(userID);
        } else if (e.getSource() == btnExpenses) {
            Expenses expenses = new Expenses(userID);
        } else if (e.getSource() == btnSuppliers) {
            Suppliers suppliers = new Suppliers(userID);
        } else if (e.getSource() == btnReports) {
            JOptionPane.showMessageDialog(frmHome, "Reports Feature Not Implemented Yet.");
        } else if (e.getSource() == btnLogout) {
            confirmAndLogout();
        }
    }

    private void confirmAndLogout() {
        int confirmLogout = JOptionPane.showConfirmDialog(frmHome, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirmLogout == JOptionPane.YES_OPTION) {
            logUserLogout(); // Log the logout event
            frmHome.dispose(); // Close the home window when Logout button is clicked
            new Login().init(); // Go back to login screen
        }
    }

    private void logUserLogout() {
        try (Connection connection = new Database().getConnection()) {
            String query = "UPDATE logs SET logoutTime = NOW() WHERE userID = ? AND logoutTime IS NULL";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userID); // Set the userID
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Logout event logged successfully.");
            } else {
                System.out.println("Failed to log the logout event.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frmHome, "Error logging logout event: " + ex.getMessage());
        }
    }

// Load the alerts from the database
private void loadAlerts(DefaultTableModel model) {
    model.setRowCount(0); // Clear existing rows

    try {
        connection = new Database().getConnection();  // Assume Database is a class that handles DB connection

        // First, check the inventory for items that are below threshold (5kg or 5pcs)
        String inventoryQuery = """
            SELECT inventory.itemID, inventory.itemName, inventory.actual
            FROM inventory
            WHERE inventory.actual < 5
        """;
        
        ps = connection.prepareStatement(inventoryQuery);
        rs = ps.executeQuery();

        while (rs.next()) {
            // For each item that meets the threshold condition, insert an alert
            int itemID = rs.getInt("itemID");
            String itemName = rs.getString("itemName");
            double actual = rs.getDouble("actual");

            // Insert into the alerts table
            String insertAlertQuery = """
                INSERT INTO alerts (itemID, itemName, actual, alertTime, resolved)
                VALUES (?, ?, ?, NOW(), 0)
            """;
            try (PreparedStatement insertPs = connection.prepareStatement(insertAlertQuery)) {
                insertPs.setInt(1, itemID);
                insertPs.setString(2, itemName);
                insertPs.setDouble(3, actual);
                insertPs.executeUpdate();
            } catch (SQLException e) {
                // Handle any issues inserting into the alerts table
                e.printStackTrace();
            }
        }

        // Now, load the alerts into the table
        String loadAlertsQuery = """
            SELECT alerts.alertID, alerts.itemID, alerts.itemName, alerts.actual, alerts.alertTime, alerts.resolved
            FROM alerts
        """;

        ps = connection.prepareStatement(loadAlertsQuery);
        rs = ps.executeQuery();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy   HH:mm:ss");

        while (rs.next()) {
            // Format the alert time
            String alertTime = rs.getTimestamp("alertTime") != null 
                    ? dateFormat.format(rs.getTimestamp("alertTime")) 
                    : "N/A";

            // Retrieve the resolved status and display it as 'Yes' or 'No'
            String resolved = rs.getBoolean("resolved") ? "Yes" : "No";

            model.addRow(new Object[]{
                    rs.getInt("alertID"),          // Alert ID
                    rs.getString("itemName"),      // Item Name
                    rs.getDouble("actual"),        // Actual value
                    alertTime,                     // Formatted alert time
                    resolved                       // Resolved status (Yes/No)
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(frame, "Error loading alerts: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        closeConnections();
    }
}

    // Delete the selected log from the database
    private void deleteAlerts() {
        int selectedRow = table.getSelectedRow(); // Get the selected row
        if (selectedRow >= 0) {
            int alertID = (int) model.getValueAt(selectedRow, 0); // Get the Log ID from the selected row

            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this log?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    connection = new Database().getConnection();  // Get database connection
                    String query = "DELETE FROM alerts WHERE alertID = ?";
                    ps = connection.prepareStatement(query);
                    ps.setInt(1, alertID); // Set the Log ID in the query
                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(frame, "Log deleted successfully.");
                        loadAlerts(model); // Refresh the table
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to delete the log.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(frame, "Error deleting alert: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    closeConnections();
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select an alert to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void closeConnections() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error closing database connection: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



