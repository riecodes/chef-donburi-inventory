package chefdonburi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChefDonburi {

    private JFrame frmSplash;
    private JPanel pnlSplash;
    private ImageIcon imgLogo;
    private JLabel lblLogo, lblStoreName, lblTitle1, lblTitle2, lblYear;

    public void init() {
        // Create the frame and panel
        frmSplash = new JFrame();
        frmSplash.setUndecorated(true);  // Remove the window borders
        frmSplash.setSize(400, 250);  // Set frame size
        frmSplash.setLocationRelativeTo(null);  // Center the frame
        pnlSplash = new JPanel();
        pnlSplash.setLayout(null);
        pnlSplash.setBackground(new Color(223, 49, 42));
        frmSplash.setContentPane(pnlSplash);

        // Load and set the logo (path should be adjusted)
        imgLogo = new ImageIcon(new ImageIcon("src\\Images\\splash screen.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        lblLogo = new JLabel(imgLogo);
        lblLogo.setSize(100, 100);  // Set logo size
        lblLogo.setLocation(70, 80);  // Set logo position
        pnlSplash.add(lblLogo);
        

        // Set company name label
        lblStoreName = new JLabel("Chef Donburi");
        lblStoreName.setForeground(new Color(242, 245, 224)); // Light background color
        lblStoreName.setFont(new Font("Arial", Font.PLAIN, 10));  // Increased font size
        lblStoreName.setSize(300, 30);
        lblStoreName.setLocation(330, 10);  // Adjusted location
        pnlSplash.add(lblStoreName);

        // Set application name labels
        lblTitle1 = new JLabel("Chef Donburi");
        lblTitle1.setForeground(new Color(242, 245, 224));
        lblTitle1.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle1.setSize(200, 60);
        lblTitle1.setLocation(170, 80);
        pnlSplash.add(lblTitle1);

        lblTitle2 = new JLabel("Inventory System");
        lblTitle2.setForeground(new Color(242, 245, 224));
        lblTitle2.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle2.setSize(200, 60);
        lblTitle2.setLocation(170, 110);  // Aligned it below lblTitle1
        pnlSplash.add(lblTitle2);

        // Set year/version label
        lblYear = new JLabel("2024 Version");
        lblYear.setForeground(new Color(242, 245, 224));
        lblYear.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblYear.setSize(100, 30);
        lblYear.setLocation(325, 210);
        pnlSplash.add(lblYear);

        // Show the splash screen
        frmSplash.setVisible(true);

        // Pause the splash screen for 5 seconds
        try {
            Thread.sleep(1000); // Increased sleep time to 5 seconds
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(frmSplash, "Error: " + e.getMessage());
        } finally {
            frmSplash.dispose();  // Close the splash screen after pause
        }

        // Here you can initialize the Login window after splash screen
        new Login().init();  // Initialize login window
    }

    public static void main(String[] args) {
        new ChefDonburi().init();  // Start the application
    }
}


