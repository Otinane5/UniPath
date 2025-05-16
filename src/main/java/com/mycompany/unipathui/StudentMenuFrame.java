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
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("UniPath", SwingConstants.LEFT);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        JLabel studentNameLabel = new JLabel("Ανδρέας Χρήστου", SwingConstants.LEFT);

        JPanel titleBox = new JPanel();
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));
        titleBox.setOpaque(false);
        titleBox.add(titleLabel);
        titleBox.add(studentNameLabel);

        topPanel.add(titleBox, BorderLayout.WEST);
        JButton messagesButton = new JButton("Τα μηνύματά μου");
        topPanel.add(messagesButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // --- CENTER ---
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        Dimension buttonSize = new Dimension(350, 50);

        JButton quizButton = new JButton("Κάνε το Quiz επαγγελματικού προσανατολισμού");
        quizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizButton.setPreferredSize(buttonSize);
        quizButton.setMaximumSize(buttonSize);
        quizButton.setBackground(new Color(100, 149, 237));
        quizButton.setBackground(Color.GREEN);

        JButton viewdepartmentsButton = new JButton("Δες την λίστα των τμημάτων");
        viewdepartmentsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewdepartmentsButton.setPreferredSize(buttonSize);
        viewdepartmentsButton.setMaximumSize(buttonSize);
        viewdepartmentsButton.setBackground(new Color(25, 25, 112));
        viewdepartmentsButton.setBackground(Color.CYAN);

        JButton councelorcontactButton = new JButton("Επικοινώνησε με σύμβουλο");
        councelorcontactButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        councelorcontactButton.setPreferredSize(buttonSize);
        councelorcontactButton.setMaximumSize(buttonSize);
        councelorcontactButton.setBackground(new Color(25, 25, 112));
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
                
        cardPanel.add(menuPanel, "menu");
        cardPanel.add(deplistPanel, "seeListOfDepartments");
        cardPanel.add(quizStarterPanel, "doQuiz");

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
