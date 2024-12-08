package chefdonburi;

import Database.Database;
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
    private JButton btnRefresh;

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
        Image jframe = frameicon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        frmHome.setIconImage(jframe);
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

        // Create the table to display alerts
        String[] columns = { "Item ID", "Category", "Item Name", "Ending"};
        model = new DefaultTableModel(columns, 0); // Initialize table with no data
        table = new JTable(model);

        // Set table font and row height
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);

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

        // Add buttons to a button panel and position it at the bottom of the frame
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnRefresh);
        btnPanel.setBounds(50, 520, 1166, 50); // Manually set the position of the button panel
        pnlHome.add(btnPanel);

        loadAlerts(model); // Load the logs from the database

        // Add action listeners for the buttons
        btnRefresh.addActionListener(e -> loadAlerts(model));

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
            Reports reports = new Reports();
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
        try {
            connection = new Database().getConnection();
            String query = "UPDATE logs SET logoutTime = NOW() WHERE userID = ? AND logoutTime IS NULL";
            ps = connection.prepareStatement(query);
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

private void loadAlerts(DefaultTableModel model) {
    model.setRowCount(0); // Clear existing rows

    try {
        connection = new Database().getConnection(); // Assume Database is a class that handles DB connection

        // Query to check inventory from both tables for items below thresholds
        String inventoryQuery = """
            SELECT inventory.ID, inventory.CATEGORY, inventory.ITEMS, inventory.ACTUAL
            FROM inventory
            UNION ALL
            SELECT inventory2.ID, inventory2.CATEGORY, inventory2.ITEMS, inventory2.ACTUAL
            FROM inventory2
        """;

        ps = connection.prepareStatement(inventoryQuery);
        rs = ps.executeQuery();

        while (rs.next()) {
            int itemID = rs.getInt("ID");
            String itemName = rs.getString("ITEMS");
            String category = rs.getString("CATEGORY");
            String actualStr = rs.getString("ACTUAL");

            // Extract numeric value and unit
            double actual;
            String unit = "";
            try {
                actual = Double.parseDouble(actualStr.replaceAll("[^0-9.]", ""));
                unit = actualStr.replaceAll("[0-9.\\s]", "").trim().toLowerCase();
            } catch (NumberFormatException e) {
                actual = 0;
            }

            // Check thresholds based on unit
            boolean belowThreshold = switch (unit) {
                case "kg" -> actual <= 3;
                case "pcs" -> actual <= 20;
                case "" -> actual <= 20; // For unknown or no unit
                default -> false; // Ignore other units
            };

            if (belowThreshold) {
                // Check if the alert for this item already exists
                String checkAlertQuery = """
                    SELECT COUNT(*) FROM alerts WHERE itemID = ?
                """;
                try (PreparedStatement checkPs = connection.prepareStatement(checkAlertQuery)) {
                    checkPs.setInt(1, itemID);
                    ResultSet checkRs = checkPs.executeQuery();
                    checkRs.next();
                    int alertCount = checkRs.getInt(1);

                    // If no alert exists for this item, insert it
                    if (alertCount == 0) {
                        String insertAlertQuery = """
                            INSERT INTO alerts (itemID, category, itemName, actual)
                            VALUES (?, ?, ?, ?)
                        """;
                        try (PreparedStatement insertPs = connection.prepareStatement(insertAlertQuery)) {
                            insertPs.setInt(1, itemID);
                            insertPs.setString(2, category); // Insert the correct category
                            insertPs.setString(3, itemName); // Insert the correct itemName
                            insertPs.setDouble(4, actual);
                            insertPs.executeUpdate();
                        }
                    }
                }

                // Add the item to the table model, including the category
                model.addRow(new Object[] {
                    itemID,         // Item ID
                    category,       // Category
                    itemName,       // Item Name
                    actualStr       // Actual value with unit
                });
            }
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(frame, "Error loading alerts: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        closeConnections();
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



