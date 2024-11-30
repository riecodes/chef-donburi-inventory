package chefdonburi;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Font;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Document;
import com.lowagie.text.Cell;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.*;
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
        

        JButton btnPrintExpenses = new JButton("Print Expenses");
        btnPrintExpenses.setForeground(new Color(242, 245, 224));
        btnPrintExpenses.setBackground(new Color(223, 49, 42));
        

        panel.add(btnPrintInventory, gbc);
        gbc.gridy++;
        panel.add(btnPrintExpenses, gbc);

        frame.add(panel);
        frame.setVisible(true);

        btnPrintInventory.addActionListener(e -> generateInventoryReport());
        btnPrintExpenses.addActionListener(e -> printExpenses());
    }
    
    private void generateInventoryReport() {
        // Create a new document
        Document document = new Document();
    
        // Declare FileNotFoundException within try-catch block
        try {
            // Create a PDF writer to write to a file
            PdfWriter.getInstance(document, new FileOutputStream("inventory_report.pdf"));
            
            // Open the document to start writing
            document.open();
    
            // Add title to the document
            document.add(new Paragraph("Inventory Report", new Font(Font.HELVETICA, 16, Font.BOLD)));
            document.add(new Paragraph("Generated on: " + java.time.LocalDate.now(), new Font(Font.HELVETICA, 10, Font.ITALIC)));
            document.add(new Paragraph("\n"));
    
            // SQL query to fetch inventory data
            String query = "SELECT * FROM inventory ORDER BY CATEGORY, ITEM";
    
            // Create a table with 8 columns (including Scrap and Spoilage)
            PdfPTable table = new PdfPTable(8);  // 8 columns: Category, Item, Unit, Price, Quantity In, Quantity Out, Scrap, Spoilage, Actual
            table.setWidthPercentage(100);  // Table takes up the full width of the page
    
            // Add table headers (based on your structure)
            table.addCell("ITEM");
            table.addCell("UNIT");
            table.addCell("PRICE");
            table.addCell("IN");
            table.addCell("OUT");
            table.addCell("SCRAP");
            table.addCell("SPOILAGE");
            table.addCell("ACTUAL");
    
            // Initialize category tracking
            String currentCategory = null;
    
            try (Connection connection = new Database().getConnection()) {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                
                // Loop through the result set and add rows grouped by category
                while (rs.next()) {
                    String category = rs.getString("CATEGORY");
    
                    // Check if the category has changed
                    if (currentCategory == null || !currentCategory.equals(category)) {
                        // Add a merged cell for the category header
                        if (currentCategory != null) {
                            document.add(new Paragraph("\n"));  // Add a blank line before the next category
                        }
                        
                        // Add the category name as a centered header
                        Paragraph categoryHeader = new Paragraph(category, new Font(Font.HELVETICA, 12, Font.BOLD));
                        categoryHeader.setAlignment(Paragraph.ALIGN_CENTER);
                        document.add(categoryHeader);
                        
                        // Update the current category
                        currentCategory = category;
                    }
    
                    // Fetch data for each item under the current category
                    String item = rs.getString("ITEMS");
                    String unit = rs.getString("UNIT");
                    String price = rs.getString("PRICE");
                    String quantityIn = rs.getString("QUANTITY_IN");
                    String quantityOut = rs.getString("QUANTITY_OUT");
                    String scrap = rs.getString("SCRAP");  // Scrap value
                    String spoilage = rs.getString("SPOILAGE");  // Spoilage value
                    String actual = rs.getString("ACTUAL");
    
                    // Add a row for the item
                    table.addCell(item);
                    table.addCell(unit);
                    table.addCell(price);
                    table.addCell(quantityIn);
                    table.addCell(quantityOut);
                    table.addCell(scrap);  // Scrap value
                    table.addCell(spoilage);  // Spoilage value
                    table.addCell(actual);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    
            // Add the table to the document
            document.add(table);
    
            // Close the document to save the PDF
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();  // Handle file or document exceptions
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
