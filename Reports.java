package chefdonburi;

import Database.Database;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Font;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.text.SimpleDateFormat;
import java.util.Date;




public class Reports {
    private double totalPrice;

    JFrame frame;
    JButton btnPrintInventory, btnPrintExpenses;
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public Reports() {
        this.totalPrice = totalPrice;
        init();
    }

    private void init() {
        ImageIcon frameicon = new ImageIcon("src\\images\\jframeicon.jpg");
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
        
        
        ImageIcon printicon = new ImageIcon("src\\Images\\print.png");
        Image print = printicon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnPrintInventory = new JButton("Print Inventory", new ImageIcon(print));
        btnPrintInventory.setForeground(new Color(242, 245, 224));
        btnPrintInventory.setBackground(new Color(223, 49, 42));
        btnPrintInventory.setSize(180, 50);
        
        
        ImageIcon printicon1 = new ImageIcon("src\\Images\\print.png");
        Image print1 = printicon1.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnPrintExpenses = new JButton("Print Expenses", new ImageIcon(print1));
        btnPrintExpenses.setForeground(new Color(242, 245, 224));
        btnPrintExpenses.setBackground(new Color(223, 49, 42));
        btnPrintExpenses.setSize(180, 50);
        

        panel.add(btnPrintInventory, gbc);
        gbc.gridy++;
        panel.add(btnPrintExpenses, gbc);

        frame.add(panel);
        frame.setVisible(true);

        btnPrintInventory.addActionListener(e -> generateInventoryReport());
        btnPrintExpenses.addActionListener(e -> generateExpensesReport());
    }




// Inside the Reports class, update the generateInventoryReport() and generateExpensesReport() methods

private void generateInventoryReport() {
    // Create a new document with landscape orientation and A4 paper size (Long size)
    Document document = new Document(com.lowagie.text.PageSize.A4.rotate());

    try {
        // Get the current date in a readable format
        String currentDate = new SimpleDateFormat("MMMM dd, yyyy").format(new Date());

        // Use JFileChooser to allow the user to choose a destination to save the report
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Inventory Report");

        // Set file filter to only show PDF files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
        fileChooser.setFileFilter(filter);

        // Show Save dialog
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return; // If user cancels, exit the method
        }

        // Get the selected file path
        String outputFilePath = fileChooser.getSelectedFile().getAbsolutePath();

        // Make sure the file has a .pdf extension
        if (!outputFilePath.endsWith(".pdf")) {
            outputFilePath += ".pdf";
        }

        // Create a PDF writer to write to the selected file
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

        // Add the current date to the right side of the header
        PdfPCell cell2 = new PdfPCell(new Phrase("DATE: " + currentDate, new Font(Font.HELVETICA, 13, Font.ITALIC)));
        cell2.setBorder(PdfPCell.NO_BORDER);
        cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);

        headerTable.addCell(cell1);
        headerTable.addCell(cell2);

        document.add(headerTable);
        document.add(new Phrase("\n"));

        // SQL query to fetch inventory data
        String queryInventory = "SELECT * FROM inventory ORDER BY CATEGORY, ITEMS";

        try {
            connection = new Database().getConnection();
            ps = connection.prepareStatement(queryInventory); // Use PreparedStatement for parameterized queries
            rs = ps.executeQuery(); 

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
            ResultSet rs2 = ps.executeQuery(queryInventory2);

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

public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
}

private void generateExpensesReport() {
    Document document = new Document(com.lowagie.text.PageSize.A4.rotate()); // Landscape orientation

    try {
        String currentDate = new SimpleDateFormat("MMMM dd, yyyy").format(new Date());

        // File selection dialog
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Expenses Report");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
        fileChooser.setFileFilter(filter);
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return; // Exit if user cancels
        }

        String outputFilePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!outputFilePath.endsWith(".pdf")) {
            outputFilePath += ".pdf"; // Ensure file ends with .pdf
        }

        // Create PDF writer instance
        PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
        document.open(); // Open the document for writing

        // Add the title and date header
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);
        headerTable.setWidths(new float[]{70, 30});

        PdfPCell titleCell = new PdfPCell(new Phrase("CHEF DONBURI – EXPENSES REPORT", new Font(Font.HELVETICA, 16, Font.BOLD)));
        titleCell.setBorder(PdfPCell.NO_BORDER);
        titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        PdfPCell dateCell = new PdfPCell(new Phrase("DATE: " + currentDate, new Font(Font.HELVETICA, 13, Font.ITALIC)));
        dateCell.setBorder(PdfPCell.NO_BORDER);
        dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

        headerTable.addCell(titleCell);
        headerTable.addCell(dateCell);
        document.add(headerTable);

        // Add some space
        document.add(new Phrase("\n"));

        // SQL query to fetch expenses data
        String queryExpenses = "SELECT ITEM_NAME, ITEM_PRICE, NUMBER_UNIT, SOURCE, MODE_OF_PAYMENT, DATE_TIME FROM expenses ORDER BY DATE_TIME";
        try {
            connection = new Database().getConnection();
            ps = connection.prepareStatement(queryExpenses);
            rs = ps.executeQuery();

            double totalPrice = 0.0;

            // Create the expenses table with 6 columns
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{3, 2, 2, 2, 2, 3});

            // Add headers to the table (bolded)
            Font boldFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            table.addCell(createHeaderCell("ITEM", boldFont));
            table.addCell(createHeaderCell("PRICE", boldFont));
            table.addCell(createHeaderCell("UNIT", boldFont));
            table.addCell(createHeaderCell("SOURCE", boldFont));
            table.addCell(createHeaderCell("PAYMENT MODE", boldFont));
            table.addCell(createHeaderCell("DATE TIME", boldFont));

            // Iterate over the result set and add rows to the table
            while (rs.next()) {
                String itemName = rs.getString("ITEM_NAME");
                double itemPrice = rs.getDouble("ITEM_PRICE");
                String numberUnit = rs.getString("NUMBER_UNIT");
                String source = rs.getString("SOURCE");
                String modeOfPayment = rs.getString("MODE_OF_PAYMENT");
                String dateTime = rs.getString("DATE_TIME");

                // Add each value to the corresponding table cell
                table.addCell(createCell(itemName));
                table.addCell(createCell(String.format("%.2f", itemPrice)));
                table.addCell(createCell(numberUnit));
                table.addCell(createCell(source));
                table.addCell(createCell(modeOfPayment));
                table.addCell(createCell(dateTime));

                // Add to total price
                totalPrice += itemPrice;
            }

            // Add the table to the document
            document.add(table);

            // Add the Total Price row
        PdfPTable totalPriceTable = new PdfPTable(1);
        totalPriceTable.setWidthPercentage(100);
        totalPriceTable.setWidths(new float[]{70, 30});

        PdfPCell totalPriceCell = new PdfPCell(new Phrase("Total Price: " + totalPrice, new Font(Font.HELVETICA, 12, Font.BOLD)));
        totalPriceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalPriceCell.setBorder(PdfPCell.NO_BORDER); // Optional: Remove border for cleaner look

        totalPriceTable.addCell(totalPriceCell);
        document.add(totalPriceTable);


        } catch (SQLException e) {
            System.err.println("Error fetching data: " + e.getMessage());
            e.printStackTrace();
        }

        // Close the document and finalize the PDF
        document.close();
        System.out.println("Report generated successfully.");

    } catch (DocumentException | FileNotFoundException e) {
        System.err.println("Error generating report: " + e.getMessage());
        e.printStackTrace();
    }
}


// Helper function to create table cells with custom height
private PdfPCell createCell(String content) {
    PdfPCell cell = new PdfPCell(new Phrase(content));
    cell.setFixedHeight(20f); // Adjust the height of the row as needed
    return cell;
}

// Helper function to create header cells with bold font
private PdfPCell createHeaderCell(String content, Font font) {
    PdfPCell cell = new PdfPCell(new Phrase(content, font));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setFixedHeight(25f); // Header row height
    return cell;
}
}