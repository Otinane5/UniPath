package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

//LoginFrame: Αρχικό παράθυρο εφαρμογής όπου ο χρήστης
//επιλέγει ρόλο (Μαθητής, Σύμβουλος, Πανεπιστήμιο).
//Μετά την επιλογή, εμφανίζεται το αντίστοιχο login panel.

public class LoginFrame extends JFrame {
    //ATTRIBUTES
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    //CONSTRUCTOR
    public LoginFrame() {
        //Τίτλος Παραθύρου
        setTitle("UniPath - Σύνδεση");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        // Αρχικοποίηση του κύριου panel με CardLayout για εναλλαγή μεταξύ ρόλων
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Προσθήκη panels στο CardLayout
        mainPanel.add(createRoleSelectionPanel(), "roleSelection");
        CounselorLoginPanel counselorLoginPanel = new CounselorLoginPanel(this);
        mainPanel.add(counselorLoginPanel, "counselorLogin");

        // Εμφάνιση πρώτου panel
        cardLayout.show(mainPanel, "roleSelection");
        add(mainPanel);
        setVisible(true);
    }
    //METHODS
    //Δημιουργεί το αρχικό panel επιλογής ρόλου με τίτλους και κουμπιά.
    private JPanel createRoleSelectionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        //Τίτλοι
        JPanel headerPanel = new JPanel(null);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));
        
        JLabel title = new JLabel("UniPath", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel welcome=new JLabel("Καλώς Ορίσατε!", SwingConstants.CENTER);
        welcome.setFont(new Font("Arial", Font.PLAIN,18));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        headerPanel.add(title);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(welcome);
        
        //Κουμπιά επιλογής ρόλου
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 80, 30, 80));
        
        JLabel loginAs = new JLabel("Σύνδεση ως:", SwingConstants.CENTER);
        loginAs.setFont(new Font("Arial", Font.ITALIC,14));
        loginAs.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton studentButton = new JButton("Μαθητής");
        JButton counselorButton = new JButton("Σύμβουλος");
        JButton universityButton = new JButton("Πανεπιστήμιο");
        
        //Μέγεθος και στοίχιση κουμπιών ρόλου
        Dimension buttonSize = new Dimension(200, 35);
        for (JButton btn : new JButton[]{studentButton, counselorButton, universityButton}) {
            btn.setMaximumSize(buttonSize);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        //Προσθήκη κουμπιών
        buttonPanel.add(loginAs);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(studentButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(counselorButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(universityButton);
        
        //Λειτουργικότητα κουμπιών
        studentButton.addActionListener(e -> {
            // TODO: Εμφάνιση του student login panel
            JOptionPane.showMessageDialog(this, "Login Μαθητή δεν έχει υλοποιηθεί ακόμα.");
        });
        counselorButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "counselorLogin");
        });
        universityButton.addActionListener(e -> {
            // TODO: Εμφάνιση του university login panel
            JOptionPane.showMessageDialog(this, "Login Πανεπιστημίου δεν έχει υλοποιηθεί ακόμα.");
        });
        
        //Δάταξη panel
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }
    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        SwingUtilities.invokeLater(LoginFrame::new);
    }
    public void showRoleSelectionPanel() {
        cardLayout.show(mainPanel, "roleSelection");
    }
}
