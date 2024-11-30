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
        btnPrintExpenses.addActionListener(e -> printExpenses());
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
            // add this CHEF DONBURI – FOOD DELIVERY SERVICES											
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
            String query = "SELECT * FROM inventory ORDER BY CATEGORY, ITEMS";
            
            try (Connection connection = new Database().getConnection()) {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                
                // Create a table with 8 columns (removed category column)
                PdfPTable table = new PdfPTable(9);
                table.setWidthPercentage(100); // Set table width

                // Define column widths (adjust as necessary)
                table.setWidths(new float[] {3, 2, 2, 2, 2, 2, 2, 2, 2}); // Adjust column widths here
                
                // Add table headers
                table.addCell("ITEM");
                table.addCell("UNIT");
                table.addCell("PRICE");
                table.addCell("BEGINNING");
                table.addCell("IN");
                table.addCell("OUT");
                table.addCell("SCRAP");
                table.addCell("SPOILAGE");
                table.addCell("ACTUAL");

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
                        table.addCell(categoryCell);

                        // Update current category
                        currentCategory = category;
                    }

                    // Add each item and its details to the table
                    table.addCell(item);
                    table.addCell(unit);
                    table.addCell(price);
                    table.addCell(beginning);
                    table.addCell(quantityIn);
                    table.addCell(quantityOut);
                    table.addCell(scrap);
                    table.addCell(spoilage);
                    table.addCell(actual);
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
    

    



    private void printExpenses() {
        // Code to print expenses
    }

}
