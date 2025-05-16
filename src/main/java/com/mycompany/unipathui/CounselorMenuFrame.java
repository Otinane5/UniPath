package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class CounselorMenuFrame extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;

    public CounselorMenuFrame() {
        setTitle("UniPath - Αρχικό Μενού συμβούλου");
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
        
        JLabel counselorNameLabel = new JLabel("Κώστας Παπαδόπουλος");
        counselorNameLabel.setFont(new Font("Arial", Font.ITALIC,14));
        counselorNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        counselorNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Τίτλοι σε κάθετη στοίχιση
        JPanel titleBox = new JPanel();
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));
        titleBox.setOpaque(false);
        titleBox.add(titleLabel);
        titleBox.add(counselorNameLabel);
        
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

        Dimension buttonSize = new Dimension(300, 50);

        JButton profileButton = new JButton("Προβολή Προφίλ");
        profileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileButton.setPreferredSize(buttonSize);
        profileButton.setMaximumSize(buttonSize);
        profileButton.setBackground(Color.GREEN);

        JButton appointmentButton = new JButton("Προβολή Αιτήσεων Ραντεβού");
        appointmentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        appointmentButton.setPreferredSize(buttonSize);
        appointmentButton.setMaximumSize(buttonSize);
        appointmentButton.setBackground(Color.CYAN);

        JButton departmentButton = new JButton("Προβολή Λίστας Τμημάτων Πανεπιστημίων");
        departmentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        departmentButton.setPreferredSize(buttonSize);
        departmentButton.setMaximumSize(buttonSize);
        departmentButton.setBackground(Color.CYAN);
        
        menuPanel.add(profileButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(appointmentButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        menuPanel.add(departmentButton);

        add(cardPanel, BorderLayout.CENTER);

        // --- BOTTOM ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton logoutButton = new JButton("Αποσύνδεση");
        logoutButton.setBackground(Color.decode("#FF6666"));
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- Panels --- 
        CounselorProfilePanel profilePanel = new CounselorProfilePanel(
                () -> cardLayout.show(cardPanel, "menu"),
                () -> cardLayout.show(cardPanel, "editProfile")
        );

        // Panels
        CounselorAcceptAppointmentPanel acceptPanel = new CounselorAcceptAppointmentPanel(cardLayout, cardPanel);
        CounselorRejectAppointmentPanel rejectPanel = new CounselorRejectAppointmentPanel(cardLayout, cardPanel);
        CounselorAppointmentDetailsPanel detailsPanel = new CounselorAppointmentDetailsPanel(cardLayout, cardPanel, acceptPanel, rejectPanel);
        CounselorAppointmentRequestsPanel appointmentPanel = new CounselorAppointmentRequestsPanel(cardLayout, cardPanel, detailsPanel);
        CounselorEditProfilePanelUI editPanel = new CounselorEditProfilePanelUI(
                () -> cardLayout.show(cardPanel, "menu"),
                () -> cardLayout.show(cardPanel, "profile")
        );

        cardPanel.add(menuPanel, "menu");
        cardPanel.add(appointmentPanel, "appointments");
        cardPanel.add(detailsPanel, "appointmentDetails");
        cardPanel.add(acceptPanel, "acceptAppointment");
        cardPanel.add(rejectPanel, "rejectAppointment");
        cardPanel.add(profilePanel, "profile");
        cardPanel.add(editPanel, "editProfile");

        // Action Listeners
        appointmentButton.addActionListener(e -> cardLayout.show(cardPanel, "appointments"));
        profileButton.addActionListener(e -> cardLayout.show(cardPanel, "profile"));
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
