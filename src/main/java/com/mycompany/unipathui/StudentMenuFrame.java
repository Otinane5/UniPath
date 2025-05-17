package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class StudentMenuFrame extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public StudentMenuFrame() {
        setTitle("UniPath - Αρχικό Μενού μαθητή");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout(10, 10));

        // --- TOP PANEL ---
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //Προαιρετικό padding
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1; // κεντρική στήλη
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;

        //Τίτλοι (στο κέντρο)
        JLabel titleLabel = new JLabel("UniPath");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel studentNameLabel = new JLabel("Ανδρέας Χρήστου");
        studentNameLabel.setFont(new Font("Arial", Font.ITALIC,14));
        studentNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        studentNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Τίτλοι σε κάθετη στοίχιση
        JPanel titleBox = new JPanel();
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));
        titleBox.setOpaque(false);
        titleBox.add(titleLabel);
        titleBox.add(studentNameLabel);
        
        //Προσθήκη τίτλων στο κέντρο
        topPanel.add(titleBox, gbc);

        //Κουμπί Μηνυμάτων (δεξιά)
        JButton messagesButton = new JButton("Τα μηνύματά μου");
        messagesButton.setPreferredSize(new Dimension(160, 30)); //Σταθερό μέγεθος
        
        ImageIcon envelopeIcon = new ImageIcon(getClass().getResource("/icons/envelope.png"));
        Image envelopeImage = envelopeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        messagesButton.setIcon(new ImageIcon(envelopeImage));
        
        //Τοποθέτηση κουμπιού δεξιά
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        topPanel.add(messagesButton, gbc);
        
        //Dummy "αόρατο" panel αριστερά για να εξισορροπήσει το βάρος
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.0;
        topPanel.add(Box.createHorizontalStrut(160), gbc); // ίδιο πλάτος με το κουμπί
        
        //Προσθήκη panel στο frame
        add(topPanel, BorderLayout.NORTH);

        // --- CENTER ---
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
        
        // Προσθήκη τίτλου
        JLabel actionTitle = new JLabel("Επιλογή Ενέργειας:");
        actionTitle.setFont(new Font("Arial", Font.BOLD, 18));
        actionTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Κέντρο
        menuPanel.add(actionTitle);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Απόσταση από κουμπιά
        
        Dimension buttonSize = new Dimension(350, 50);

        JButton quizButton = new JButton("Κάνε το Quiz επαγγελματικού προσανατολισμού");
        quizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizButton.setPreferredSize(buttonSize);
        quizButton.setMaximumSize(buttonSize);
        quizButton.setBackground(Color.GREEN);

        JButton viewdepartmentsButton = new JButton("Δες την λίστα των τμημάτων");
        viewdepartmentsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewdepartmentsButton.setPreferredSize(buttonSize);
        viewdepartmentsButton.setMaximumSize(buttonSize);
        viewdepartmentsButton.setBackground(Color.CYAN);

        JButton councelorcontactButton = new JButton("Επικοινώνησε με σύμβουλο");
        councelorcontactButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        councelorcontactButton.setPreferredSize(buttonSize);
        councelorcontactButton.setMaximumSize(buttonSize);
        councelorcontactButton.setBackground(Color.CYAN);

        menuPanel.add(quizButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        menuPanel.add(viewdepartmentsButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        menuPanel.add(councelorcontactButton);

        add(cardPanel, BorderLayout.CENTER);

        // --- BOTTOM ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton logoutButton = new JButton("Αποσύνδεση");
        logoutButton.setBackground(Color.decode("#FF6666"));
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- Panels ---
        DepartmentListUI deplistPanel = new DepartmentListUI(
                () -> cardLayout.show(cardPanel, "menu"),
                () -> cardLayout.show(cardPanel, "showDepartment"),
                () -> cardLayout.show(cardPanel, "applicationForm")
        );        
         
        StarterQuiz quizStarterPanel = new StarterQuiz(
                () -> cardLayout.show(cardPanel, "menu"),
                () -> cardLayout.show(cardPanel, "startQuiz")
        );          
           
        QuizUI quizPanel = new QuizUI(
            () -> cardLayout.show(cardPanel, "doQuiz"),
            () -> System.out.println("Επόμενη ερώτηση"),  
            () -> System.out.println("Προηγούμενη ερώτηση")
        );
        
        cardPanel.add(menuPanel, "menu");
        cardPanel.add(deplistPanel, "seeListOfDepartments");
        cardPanel.add(quizStarterPanel, "doQuiz");
        cardPanel.add(quizPanel, "startQuiz");

       // Action Listeners
       quizButton.addActionListener(e -> cardLayout.show(cardPanel, "doQuiz"));
       viewdepartmentsButton.addActionListener(e -> cardLayout.show(cardPanel, "seeListOfDepartments"));

        logoutButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Είστε σίγουρος ότι θέλετε να αποσυνδεθείτε;",
                    "Επιβεβαίωση Αποσύνδεσης",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                dispose(); // Κλείσιμο αυτού του frame
                new LoginFrame().setVisible(true); // Άνοιγμα login από την αρχή
            }
        });

        setVisible(true);
    }
}
