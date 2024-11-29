package chefdonburi;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class Reports {

    private JFrame frame;
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public Reports() {
        init();
    }

    private void init() {
        ImageIcon frameicon = new ImageIcon("C:\\Users\\geramy\\Downloads\\images-20241029T152348Z-001\\images\\logochef.png");
        Image jframe = frameicon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        frame = new JFrame("Reports");
        frame.setIconImage(jframe);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);

        JButton btnPrintInventory = new JButton("Print Inventory");
        btnPrintInventory.setForeground(new Color(242, 245, 224));
        btnPrintInventory.setBackground(new Color(223, 49, 42));
        btnPrintInventory.setFont(new Font("Arial", Font.BOLD, 14));

        JButton btnPrintExpenses = new JButton("Print Expenses");
        btnPrintExpenses.setForeground(new Color(242, 245, 224));
        btnPrintExpenses.setBackground(new Color(223, 49, 42));
        btnPrintExpenses.setFont(new Font("Arial", Font.BOLD, 14));

        panel.add(btnPrintInventory, gbc);
        gbc.gridy++;
        panel.add(btnPrintExpenses, gbc);

        frame.add(panel);
        frame.setVisible(true);

        btnPrintInventory.addActionListener(e -> printInventory());
        btnPrintExpenses.addActionListener(e -> printExpenses());
    }

    private void printInventory() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Category", "Items", "Unit", "Price", "Beginning", "In", "Out", "Scrap", "Spoilage", "Ending", "Last Edited By", "Last Edited On"}, 0);
        DefaultTableModel model2 = new DefaultTableModel(new String[]{"ID", "Category", "Item", "Price", "SF", "Beginning", "In", "Out", "Spoilage", "Ending", "LastEditedBy", "LastEditedOn"}, 0);

        // Fetch data from Inventory table
        try {
            connection = new Database().getConnection();
            ps = connection.prepareStatement("SELECT * FROM Inventory");
            rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("ID"), rs.getString("Category"), rs.getString("Items"), rs.getString("Unit"), rs.getDouble("Price"), rs.getInt("Beginning"), rs.getInt("In"), rs.getInt("Out"), rs.getInt("Scrap"), rs.getInt("Spoilage"), rs.getInt("Ending"), rs.getString("Last Edited By"), rs.getString("Last Edited On")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Fetch data from Inventory2 table
        try {
            connection = new Database().getConnection();
            ps = connection.prepareStatement("SELECT * FROM Inventory2");
            rs = ps.executeQuery();
            while (rs.next()) {
                model2.addRow(new Object[]{rs.getInt("ID"), rs.getString("Category"), rs.getString("Item"), rs.getDouble("Price"), rs.getString("SF"), rs.getInt("Beginning"), rs.getInt("In"), rs.getInt("Out"), rs.getInt("Spoilage"), rs.getInt("Ending"), rs.getString("LastEditedBy"), rs.getString("LastEditedOn")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Print the data
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            @Override
            public int print(java.awt.Graphics g, PageFormat pf, int page) throws PrinterException {
                if (page > 0) {
                    return NO_SUCH_PAGE;
                }

                // Set landscape orientation
                pf.setOrientation(PageFormat.LANDSCAPE);

                // Set paper size to long
                Paper paper = new Paper();
                paper.setSize(595, 842); // A4 size in points
                paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
                pf.setPaper(paper);

                // Print the data in two columns
                int y = 20;
                int x = 20;
                int columnWidth = (int) (pf.getImageableWidth() / 2) - 40;
                g.setFont(new Font("Arial", Font.PLAIN, 10));

                // Print Inventory table data
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (y > pf.getImageableHeight() - 20) {
                        y = 20;
                        x += columnWidth + 40;
                    }
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        g.drawString(model.getValueAt(i, j).toString(), x + j * 50, y);
                    }
                    y += 15;
                }

                // Print Inventory2 table data
                for (int i = 0; i < model2.getRowCount(); i++) {
                    if (y > pf.getImageableHeight() - 20) {
                        y = 20;
                        x += columnWidth + 40;
                    }
                    for (int j = 0; j < model2.getColumnCount(); j++) {
                        g.drawString(model2.getValueAt(i, j).toString(), x + j * 50, y);
                    }
                    y += 15;
                }

                return PAGE_EXISTS;
            }
        });

        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    private void printExpenses() {
        // Code to print expenses
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
