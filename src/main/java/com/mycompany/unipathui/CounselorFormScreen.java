package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class CounselorFormScreen extends JFrame {
    public CounselorFormScreen(String studentName, String counselorName) {
        setTitle("Φόρμα Ραντεβού");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("UniPath", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(250, 10, 200, 30);
        add(title);

        JLabel nameLabel = new JLabel(studentName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        nameLabel.setBounds(250, 40, 200, 20);
        add(nameLabel);

        JButton messagesButton = new JButton("Τα μηνύματά μου");
        ImageIcon envelopeIcon = new ImageIcon(getClass().getResource("/icons/envelope.png"));
        Image envelopeImage = envelopeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        messagesButton.setIcon(new ImageIcon(envelopeImage));
        messagesButton.setBounds(520, 10, 150, 30);
        add(messagesButton);

        JLabel formTitle = new JLabel("Φόρμα Ραντεβού", SwingConstants.CENTER);
        formTitle.setFont(new Font("Arial", Font.BOLD, 16));
        formTitle.setBounds(200, 80, 300, 25);
        add(formTitle);

        JLabel counselorLabel = new JLabel("Σύμβουλος: " + counselorName);
        counselorLabel.setBounds(200, 110, 300, 25);
        add(counselorLabel);

        JLabel firstNameLabel = new JLabel("Όνομα:");
        firstNameLabel.setBounds(150, 150, 100, 25);
        add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(250, 150, 300, 25);
        add(firstNameField);

        JLabel lastNameLabel = new JLabel("Επίθετο:");
        lastNameLabel.setBounds(150, 190, 100, 25);
        add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(250, 190, 300, 25);
        add(lastNameField);

        JLabel phoneLabel = new JLabel("Τηλέφωνο:");
        phoneLabel.setBounds(150, 230, 100, 25);
        add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(250, 230, 300, 25);
        add(phoneField);

        JLabel emailLabel = new JLabel("e-mail:");
        emailLabel.setBounds(150, 270, 100, 25);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(250, 270, 300, 25);
        add(emailField);

        JLabel interestLabel = new JLabel("Πεδίο ενδιαφέροντος:");
        interestLabel.setBounds(120, 310, 150, 25);
        add(interestLabel);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(2, 3));
        radioPanel.setBounds(250, 310, 300, 50);

        ButtonGroup group = new ButtonGroup();
        for (int i = 1; i <= 6; i++) {
            JRadioButton btn = new JRadioButton("Πεδίο " + i);
            group.add(btn);
            radioPanel.add(btn);
        }
        add(radioPanel);

        JButton cancelBtn = new JButton("Ακύρωση");
        cancelBtn.setBackground(Color.RED);
        cancelBtn.setBounds(250, 370, 120, 30);
        add(cancelBtn);

        JButton submitBtn = new JButton("Υποβολή");
        submitBtn.setBackground(Color.GREEN);
        submitBtn.setBounds(390, 370, 120, 30);
        add(submitBtn);

        JButton logoutBtn = new JButton("Αποσύνδεση");
        logoutBtn.setBounds(30, 420, 150, 30);
        logoutBtn.setBackground(Color.decode("#FF6666"));
        add(logoutBtn);

        JButton homeBtn = new JButton("Αρχική Σελίδα");
        homeBtn.setBounds(270, 420, 150, 30);
        homeBtn.setBackground(Color.decode("#B3FF66"));
        add(homeBtn);

        JButton backBtn = new JButton("Πίσω");
        backBtn.setBounds(510, 420, 150, 30);
        backBtn.setBackground(Color.decode("#FFCC66"));
        add(backBtn);

        cancelBtn.addActionListener(e -> dispose());
        backBtn.addActionListener(e -> dispose());
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        homeBtn.addActionListener(e -> {
            dispose();
            new StudentMenuFrame().setVisible(true);
        });
    }
}
