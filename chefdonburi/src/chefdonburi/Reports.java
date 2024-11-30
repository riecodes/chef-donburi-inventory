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
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
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
        btnPrintExpenses.addActionListener(e -> generateExpensesReport());
    }

    private void generateInventoryReport() {
        // Create a new document with landscape orientation and A4 paper size (Long size)
        Document document = new Document(com.lowagie.text.PageSize.A4.rotate());
    
        try {
            String outputFilePath = "src/chefdonburi/inventory_report.pdf";
        
            // Create a PDF writer to write to the file
            PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
            
            // Open the document to start writing
            document.open();
    
            // Add title to the document
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setWidths(new float[]{70, 30});
    
            PdfPCell cell1 = new PdfPCell(new Phrase("CHEF DONBURI – FOOD DELIVERY SERVICES", new Font(Font.HELVETICA, 16, Font.BOLD)));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
    
            PdfPCell cell2 = new PdfPCell(new Phrase("DATE: _________________", new Font(Font.HELVETICA, 13, Font.ITALIC)));
            cell2.setBorder(PdfPCell.NO_BORDER);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
    
            headerTable.addCell(cell1);
            headerTable.addCell(cell2);
    
            document.add(headerTable);
            document.add(new Phrase("\n"));
    
            // SQL query to fetch inventory data
            String queryInventory = "SELECT * FROM inventory ORDER BY CATEGORY, ITEMS";
            
            try (Connection connection = new Database().getConnection()) {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(queryInventory);
                
                // Create a table with 9 columns for the first inventory
                PdfPTable table1 = new PdfPTable(9);
                table1.setWidthPercentage(100); // Set table width
    
                // Define column widths
                table1.setWidths(new float[] {3, 2, 2, 2, 2, 2, 2, 2, 2}); // Adjust column widths
    
                // Add table headers
                table1.addCell("ITEM");
                table1.addCell("UNIT");
                table1.addCell("PRICE");
                table1.addCell("BEGINNING");
                table1.addCell("IN");
                table1.addCell("OUT");
                table1.addCell("SCRAP");
                table1.addCell("SPOILAGE");
                table1.addCell("ACTUAL");
    
                String currentCategory = "";
    
                // Loop through the result set and add each row to the table
                while (rs.next()) {
                    String category = rs.getString("CATEGORY");
                    String item = rs.getString("ITEMS");
                    String unit = rs.getString("UNIT");
                    String price = rs.getString("PRICE");
                    String beginning = rs.getString("BEGINNING");
                    String quantityIn = rs.getString("QUANTITY_IN");
                    String quantityOut = rs.getString("QUANTITY_OUT");
                    String scrap = rs.getString("SCRAP");
                    String spoilage = rs.getString("SPOILAGE");
                    String actual = rs.getString("ACTUAL");
    
                    // Add category header when category changes
                    if (!category.equals(currentCategory)) {
                        // Add a row with the category name in bold, spanning all columns
                        PdfPCell categoryCell = new PdfPCell(new Phrase(category, new Font(Font.HELVETICA, 12, Font.BOLD)));
                        categoryCell.setColspan(9); // Span across all columns
                        categoryCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        categoryCell.setBackgroundColor(Color.LIGHT_GRAY); // Set background color
                        table1.addCell(categoryCell);
    
                        // Update current category
                        currentCategory = category;
                    }
    
                    // Add each item and its details to the table
                    table1.addCell(item);
                    table1.addCell(unit);
                    table1.addCell(price);
                    table1.addCell(beginning);
                    table1.addCell(quantityIn);
                    table1.addCell(quantityOut);
                    table1.addCell(scrap);
                    table1.addCell(spoilage);
                    table1.addCell(actual);
                }
    
                // Add the first table to the document
                document.add(table1);
    
                // Add a space before the second table
                document.add(new Phrase("\n"));
    
                // SQL query to fetch inventory2 data (same as inventory, with ITEM and SF columns)
                String queryInventory2 = "SELECT * FROM inventory2 ORDER BY CATEGORY, ITEM";
                ResultSet rs2 = stmt.executeQuery(queryInventory2);
    
                // Create a table with 8 columns for the second inventory (inventory2)
                PdfPTable table2 = new PdfPTable(8);
                table2.setWidthPercentage(100); // Set table width
    
                // Define column widths for the second table
                table2.setWidths(new float[] {3, 2, 2, 2, 2, 2, 2, 2}); // Adjust column widths
    
                // Add table headers
                table2.addCell("ITEM");
                table2.addCell("PRICE");
                table2.addCell("SF");
                table2.addCell("BEGINNING");
                table2.addCell("IN");
                table2.addCell("OUT");
                table2.addCell("SPOILAGE");
                table2.addCell("ACTUAL");
    
                // Loop through the second result set and add each row to the table
                while (rs2.next()) {
                    String category2 = rs2.getString("CATEGORY");
                    String item2 = rs2.getString("ITEM");
                    String price2 = rs2.getString("PRICE");
                    String sf = rs2.getString("SF");
                    String beginning2 = rs2.getString("BEGINNING");
                    String quantityIn2 = rs2.getString("QUANTITY_IN");
                    String quantityOut2 = rs2.getString("QUANTITY_OUT");                    
                    String spoilage2 = rs2.getString("SPOILAGE");
                    String actual2 = rs2.getString("ACTUAL");
    
                    // Add category header when category changes
                    if (!category2.equals(currentCategory)) {
                        // Add a row with the category name in bold, spanning all columns
                        PdfPCell categoryCell2 = new PdfPCell(new Phrase(category2, new Font(Font.HELVETICA, 12, Font.BOLD)));
                        categoryCell2.setColspan(8); // Span across all columns
                        categoryCell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        categoryCell2.setBackgroundColor(Color.LIGHT_GRAY); // Set background color
                        table2.addCell(categoryCell2);
    
                        // Update current category
                        currentCategory = category2;
                    }
    
                    // Add each item and its details to the table
                    table2.addCell(item2);
                    table2.addCell(price2);
                    table2.addCell(sf);                    
                    table2.addCell(beginning2);
                    table2.addCell(quantityIn2);
                    table2.addCell(quantityOut2);                    
                    table2.addCell(spoilage2);
                    table2.addCell(actual2);
                }
    
                // Add the second table to the document
                document.add(table2);
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
    
            // Close the document to save the PDF
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }
    
    

    



    private void generateExpensesReport() {
        // Create a new document with landscape orientation and A4 paper size (Long size)
        Document document = new Document(com.lowagie.text.PageSize.A4.rotate());
    
        try {
            String outputFilePath = "src/chefdonburi/expenses_report.pdf";
        
            // Create a PDF writer to write to the file
            PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
            
            // Open the document to start writing
            document.open();
    
            // Add title to the document
            // Add "CHEF DONBURI – FOOD DELIVERY SERVICES" as header
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setWidths(new float[]{70, 30});
    
            PdfPCell cell1 = new PdfPCell(new Phrase("CHEF DONBURI – FOOD DELIVERY SERVICES", new Font(Font.HELVETICA, 16, Font.BOLD)));
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
    
            PdfPCell cell2 = new PdfPCell(new Phrase("DATE: _________________", new Font(Font.HELVETICA, 13, Font.ITALIC)));
            cell2.setBorder(PdfPCell.NO_BORDER);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
    
            headerTable.addCell(cell1);
            headerTable.addCell(cell2);
    
            document.add(headerTable);
            document.add(new Phrase("\n"));
    
            // SQL query to fetch expenses data
            String query = "SELECT * FROM expenses ORDER BY DATE_TIME DESC";
            
            try (Connection connection = new Database().getConnection()) {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                
                // Create a table with 6 columns
                PdfPTable table = new PdfPTable(6);
                table.setWidthPercentage(100); // Set table width
    
                // Define column widths (adjust as necessary)
                table.setWidths(new float[] {3, 2, 2, 2, 3, 2}); // Adjust column widths here
                
                // Add table headers
                table.addCell("ITEM NAME");
                table.addCell("ITEM PRICE");
                table.addCell("NUMBER UNIT");
                table.addCell("SOURCE");
                table.addCell("MODE OF PAYMENT");
                table.addCell("DATE/TIME");
    
                // Loop through the result set and add each row to the table
                while (rs.next()) {
                    String itemName = rs.getString("ITEM_NAME");
                    String itemPrice = rs.getString("ITEM_PRICE");
                    String numberUnit = rs.getString("NUMBER_UNIT");
                    String source = rs.getString("SOURCE");
                    String modeOfPayment = rs.getString("MODE_OF_PAYMENT");
                    String dateTime = rs.getString("DATE_TIME");
    
                    // Add each expense record to the table
                    table.addCell(itemName);
                    table.addCell(itemPrice);
                    table.addCell(numberUnit);
                    table.addCell(source);
                    table.addCell(modeOfPayment);
                    table.addCell(dateTime);
                }
    
                // Add the table to the document
                document.add(table);
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
    
            // Close the document to save the PDF
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }
    

}
