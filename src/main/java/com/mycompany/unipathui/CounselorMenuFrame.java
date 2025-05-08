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
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("UniPath", SwingConstants.LEFT);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        JLabel counselorNameLabel = new JLabel("Κώστας Παπαδόπουλος", SwingConstants.LEFT);

        JPanel titleBox = new JPanel();
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));
        titleBox.setOpaque(false);
        titleBox.add(titleLabel);
        titleBox.add(counselorNameLabel);

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

        Dimension buttonSize = new Dimension(300, 50);

        JButton profileButton = new JButton("Προβολή προφίλ");
        profileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileButton.setPreferredSize(buttonSize);
        profileButton.setMaximumSize(buttonSize);
        profileButton.setBackground(new Color(100, 149, 237));
        profileButton.setForeground(Color.WHITE);

        JButton appointmentButton = new JButton("Προβολή αιτήσεων ραντεβού");
        appointmentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        appointmentButton.setPreferredSize(buttonSize);
        appointmentButton.setMaximumSize(buttonSize);
        appointmentButton.setBackground(new Color(25, 25, 112));
        appointmentButton.setForeground(Color.WHITE);

        JButton departmentButton = new JButton("Προβολή λίστας τμημάτων");
        departmentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        departmentButton.setPreferredSize(buttonSize);
        departmentButton.setMaximumSize(buttonSize);
        departmentButton.setBackground(new Color(25, 25, 112));
        departmentButton.setForeground(Color.WHITE);

        menuPanel.add(profileButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(appointmentButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        menuPanel.add(departmentButton);

        add(cardPanel, BorderLayout.CENTER);

        // --- BOTTOM ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton logoutButton = new JButton("Αποσύνδεση");
        logoutButton.setBackground(new Color(255, 204, 204));
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
                new LoginFrame(); // Άνοιγμα login από την αρχή
            }
        });

        setVisible(true);
    }
}
