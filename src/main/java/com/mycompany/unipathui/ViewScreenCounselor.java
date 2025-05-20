package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class ViewScreenCounselor extends JFrame {

    public ViewScreenCounselor() {
        setTitle("Προφίλ Συμβούλου");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Κεντρικός τίτλος
        JLabel title = new JLabel("UniPath", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(300, 10, 200, 30);
        add(title);

        // Κουμπί Μηνυμάτων
        JButton messagesButton = new JButton("Τα μηνύματά μου");
        ImageIcon envelopeIcon = new ImageIcon(getClass().getResource("/icons/envelope.png"));
        Image envelopeImage = envelopeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        messagesButton.setIcon(new ImageIcon(envelopeImage));
        messagesButton.setBounds(630, 10, 150, 30);
        add(messagesButton);
        messagesButton.addActionListener(e -> {
            new MessageBoxFrame().setVisible(true);
        });

        // Τίτλος συμβούλων
        JLabel advisorTitle = new JLabel("Διαθέσιμοι Σύμβουλοι:");
        advisorTitle.setFont(new Font("Arial", Font.BOLD, 16));
        advisorTitle.setBounds(40, 80, 300, 25);
        add(advisorTitle);

        // Panel με λίστα συμβούλων
        JPanel advisorsPanel = new JPanel();
        advisorsPanel.setLayout(new GridLayout(0, 1, 10, 10));
        advisorsPanel.setBackground(Color.LIGHT_GRAY);

        String[][] advisors = {
            {"Μαρία Παπαδοπούλου", "2101234567"},
            {"Γιάννης Δημητρίου", "2107654321"},
            {"Άννα Καραγιάννη", "2109988776"},
            {"Νίκος Βασιλείου", "2103332211"},
            {"Κατερίνα Πατρικίου", "2105556677"}
        };

        for (String[] advisor : advisors) {
            JPanel row = new JPanel(new GridLayout(3, 2, 5, 5));
            row.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel name = new JLabel(advisor[0]);
            JLabel phone = new JLabel(advisor[1]);
            JButton profileBtn = new JButton("Προβολή προφίλ");
            JButton appointmentBtn = new JButton("Ραντεβού");

            profileBtn.setBackground(Color.decode("#E6B3FF")); // light purple
            appointmentBtn.setBackground(Color.GREEN);

            // Numeric input (spinner) for review
            JLabel reviewLabel = new JLabel("Βαθμολόγηση:");
            SpinnerModel model = new SpinnerNumberModel(0, 0, 5, 1);
            JSpinner reviewSpinner = new JSpinner(model);

            // Submit button for review
            JButton submitReviewBtn = new JButton("Υποβολή");
            submitReviewBtn.addActionListener(e -> {
                System.out.println("pressed button!");
            });

            row.add(name);
            row.add(profileBtn);
            row.add(phone);
            row.add(appointmentBtn);
            row.add(reviewLabel);
            JPanel reviewPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            reviewPanel.add(reviewSpinner);
            reviewPanel.add(submitReviewBtn);
            row.add(reviewPanel);

            advisorsPanel.add(row);
        }

        // Scroll για το advisorsPanel
        JScrollPane scrollPane = new JScrollPane(advisorsPanel);
        scrollPane.setBounds(40, 110, 720, 250);
        add(scrollPane);

        // --- Κουμπιά κάτω ---
        JButton logoutBtn = new JButton("Αποσύνδεση");
        logoutBtn.setBackground(Color.decode("#FF6666"));
        logoutBtn.setBounds(40, 400, 180, 30);
        add(logoutBtn);

        JButton homeBtn = new JButton("Αρχική Σελίδα");
        homeBtn.setBackground(Color.decode("#B3FF66"));
        homeBtn.setBounds(300, 400, 180, 30);
        add(homeBtn);

        JButton backBtn = new JButton("Πίσω");
        backBtn.setBackground(Color.decode("#FFCC66"));
        backBtn.setBounds(580, 400, 180, 30);
        add(backBtn);

        // Νέο κουμπί: "Προτάσεις Για Εσένα"
        JButton suggestionsBtn = new JButton("Προτάσεις Για Εσένα");
        suggestionsBtn.setBackground(Color.decode("#66CCFF")); // Light blue
        suggestionsBtn.setBounds(300, 360, 180, 30);
        add(suggestionsBtn);

        suggestionsBtn.addActionListener(e -> {
            System.out.println("pressed!");
        });

        // Λειτουργίες κουμπιών
        logoutBtn.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this, "Είστε σίγουρος ότι θέλετε να αποσυνδεθείτε;", "Επιβεβαίωση", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });

        homeBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        backBtn.addActionListener(e -> {
            dispose();
            new StudentMenuFrame().setVisible(true);
        });
    }
}
