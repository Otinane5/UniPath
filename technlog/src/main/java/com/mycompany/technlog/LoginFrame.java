package com.mycompany.technlog;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public LoginFrame() {
        setTitle("UniPath - Σύνδεση");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Αρχικό panel επιλογής ρόλου
        JPanel roleSelectionPanel = new JPanel();
        roleSelectionPanel.setLayout(new BoxLayout(roleSelectionPanel, BoxLayout.Y_AXIS));
        roleSelectionPanel.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        JLabel titleLabel = new JLabel("Σύνδεση ως:");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton studentButton = new JButton("Μαθητής");
        JButton counselorButton = new JButton("Σύμβουλος");
        JButton universityButton = new JButton("Πανεπιστήμιο");

        studentButton.setEnabled(false);     // Μόνο το σύμβουλος λειτουργεί
        universityButton.setEnabled(false);

        Dimension buttonSize = new Dimension(200, 40);
        for (JButton b : new JButton[]{studentButton, counselorButton, universityButton}) {
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setMaximumSize(buttonSize);
        }

        roleSelectionPanel.add(titleLabel);
        roleSelectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        roleSelectionPanel.add(studentButton);
        roleSelectionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        roleSelectionPanel.add(counselorButton);
        roleSelectionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        roleSelectionPanel.add(universityButton);

        mainPanel.add(roleSelectionPanel, "roleSelection");

        // Panel σύνδεσης συμβούλου
        CounselorLoginPanel counselorLoginPanel = new CounselorLoginPanel(this);
        mainPanel.add(counselorLoginPanel, "counselorLogin");
        
        counselorLoginPanel.clearFields();  // <== ΝΕΟ
        cardLayout.show(mainPanel, "roleSelection");

        add(mainPanel);
        setVisible(true);

        counselorButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "counselorLogin");
        });
    }

    public void showRoleSelectionPanel() {
        cardLayout.show(mainPanel, "roleSelection");
    }
}
