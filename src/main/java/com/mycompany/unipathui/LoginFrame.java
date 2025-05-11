package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

//LoginFrame: Αρχικό παράθυρο εφαρμογής όπου ο χρήστης
//επιλέγει ρόλο (Μαθητής, Σύμβουλος, Πανεπιστήμιο).
//Μετά την επιλογή, εμφανίζεται το αντίστοιχο login panel.

public class LoginFrame extends JFrame {
    //ATTRIBUTES
    private final CardLayout cardLayout;
    private final JPanel contentPanel;
    //CONSTRUCTOR
    public LoginFrame() {
        //Παραμετροποίηση Παραθύρου
        setTitle("UniPath - Σύνδεση");
        setSize(500, 400); //πρέπει να αλλαχτεί παντού και να είναι ίδιο σε όλη την εφαρμογή
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        //Επικεφαλίδα (σταθερό κομμάτι)
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        headerPanel.setBackground(new Color(240, 248, 255));

        JLabel titleLabel = new JLabel("UniPath", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcomeLabel = new JLabel("Καλώς Ορίσατε!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(welcomeLabel);

        add(headerPanel, BorderLayout.NORTH);
        
        // Κεντρικό Panel με CardLayout για εναλλαγή περιεχομένου
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        
        // Προσθήκη panels στο CardLayout
        contentPanel.add(createRoleSelectionPanel(), "roleSelection");
        contentPanel.add(new CounselorLoginPanel(this), "counselorLogin");
        contentPanel.add(new UniversityLoginPanel(this), "UniversityLogin");
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Αρχική οθόνη
        cardLayout.show(contentPanel, "roleSelection");
        setVisible(true);
    }
    //METHODS
    //Δημιουργεί το αρχικό panel επιλογής ρόλου με τίτλους και κουμπιά.
    private JPanel createRoleSelectionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));
        
        //Τίτλοι
        JLabel loginAs = new JLabel("Σύνδεση ως:", SwingConstants.CENTER);
        loginAs.setFont(new Font("Arial", Font.ITALIC, 16));
        loginAs.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Κουμπιά επιλογής ρόλου
        JButton studentButton = new JButton("Μαθητής");
        JButton counselorButton = new JButton("Σύμβουλος");
        JButton universityButton = new JButton("Πανεπιστήμιο");
        
        studentButton.setBackground(Color.GREEN);
        counselorButton.setBackground(Color.GREEN);
        universityButton.setBackground(Color.GREEN);
        
        //Κουμπί εξόδου από το σύστημα
        JButton exit = new JButton("Έξοδος από το σύστημα");
        exit.setBackground(Color.RED);
        
        //Μέγεθος και στοίχιση κουμπιών ρόλου
        Dimension buttonSize = new Dimension(200, 35);
        for (JButton btn : new JButton[]{studentButton, counselorButton, universityButton, exit}) {
            btn.setMaximumSize(buttonSize);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        //Προσθήκη κουμπιών
        panel.add(loginAs);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(studentButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(counselorButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(universityButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(exit);
        
        //Λειτουργικότητα κουμπιών
        studentButton.addActionListener(e -> {
            // TODO: Εμφάνιση του student login panel
            JOptionPane.showMessageDialog(this, "Login Μαθητή δεν έχει υλοποιηθεί ακόμα.");
        });
        counselorButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "counselorLogin");
        });
        universityButton.addActionListener(e -> {
            // TODO: Εμφάνιση του university login panel
            cardLayout.show(contentPanel, "UniversityLogin");
        });
        exit.addActionListener(e -> System.exit(0));
        
        return panel;
    }
    //Επιστρέφει τον χρήστη στο panel επιλογής ρόλου.
    public void showRoleSelectionPanel() {
        cardLayout.show(contentPanel, "roleSelection");
    }
    //Εκκίνηση εφαρμογής
    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}