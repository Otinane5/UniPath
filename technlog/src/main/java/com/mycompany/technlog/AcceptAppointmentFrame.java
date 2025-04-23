package com.mycompany.technlog;

import javax.swing.*;
import java.awt.*;

public class AcceptAppointmentFrame extends JFrame {

    public AcceptAppointmentFrame(String counselorName) {
        setTitle("UniPath - Αποδοχή Αιτήματος");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout(10, 10));

        // Top panel με τίτλο UniPath και το όνομα του συμβούλου
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("UniPath", SwingConstants.LEFT);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        JLabel counselorLabel = new JLabel(counselorName, SwingConstants.LEFT);

        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(counselorLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Center panel με τίτλο και πεδίο μηνύματος
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel messageTitle = new JLabel("Μήνυμα αποδοχής ραντεβού");
        messageTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        messageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JTextArea messageArea = new JTextArea();
        messageArea.setLineWrap(true); // Αναδίπλωση κειμένου
        messageArea.setWrapStyleWord(true); // Σπάει τις λέξεις σωστά
        messageArea.setBackground(Color.WHITE); // Ίσως καλύτερο από το light gray τώρα που γράφουμε
        messageArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        messageArea.setMargin(new Insets(10, 10, 10, 10));
        messageArea.setPreferredSize(new Dimension(400, 100)); // Περιορισμένο πλάτος
        
        // Wrapper Panel για το κεντράρισμα
        JPanel messageWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messageWrapper.add(messageArea);

        // Κουμπιά αποδοχή και ακύρωση αιτήματος
        JPanel actionButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton cancelRequestButton = new JButton("Ακύρωση αιτήματος");
        cancelRequestButton.setBackground(new Color(255, 102, 102));  // Κόκκινο
        cancelRequestButton.setForeground(Color.BLACK);               // Μαύρο κείμενο
        JButton confirmRequestButton = new JButton("Αποδοχή αιτήματος");
        confirmRequestButton.setBackground(new Color(102, 255, 102)); // Πράσινο
        confirmRequestButton.setForeground(Color.BLACK);              // Μαύρο κείμενο
        
        actionButtons.add(cancelRequestButton);
        actionButtons.add(confirmRequestButton);

        centerPanel.add(messageTitle);
        centerPanel.add(messageWrapper);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(actionButtons);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel με γνωστά κουμπιά
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton logoutButton = new JButton("Αποσύνδεση");
        logoutButton.setBackground(new Color(255, 204, 204));

        JButton homeButton = new JButton("Αρχική σελίδα");
        homeButton.setBackground(new Color(204, 255, 204));

        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(new Color(255, 229, 180));

        bottomPanel.add(logoutButton);
        bottomPanel.add(homeButton);
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
