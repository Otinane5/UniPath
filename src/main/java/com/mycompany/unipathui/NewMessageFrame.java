package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class NewMessageFrame extends JFrame {
    public NewMessageFrame(MessageBoxFrame parent) {
        setTitle("Νέο Μήνυμα");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Ετικέτα: Παραλήπτης
        JLabel toLabel = new JLabel("Παραλήπτης:");
        toLabel.setBounds(30, 30, 100, 25);
        add(toLabel);

        JTextField toField = new JTextField();
        toField.setBounds(130, 30, 300, 25);
        add(toField);

        // Ετικέτα: Θέμα
        JLabel subjectLabel = new JLabel("Θέμα:");
        subjectLabel.setBounds(30, 70, 100, 25);
        add(subjectLabel);

        JTextField subjectField = new JTextField();
        subjectField.setBounds(130, 70, 300, 25);
        add(subjectField);

        // Ετικέτα: Περιεχόμενο
        JLabel contentLabel = new JLabel("Περιεχόμενο:");
        contentLabel.setBounds(30, 110, 100, 25);
        add(contentLabel);

        JTextArea contentArea = new JTextArea();
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        contentScrollPane.setBounds(130, 110, 300, 150);
        add(contentScrollPane);

        // Κουμπί Αποστολής
        JButton sendButton = new JButton("Αποστολή");
        sendButton.setBackground(Color.decode("#B3FF66"));
        sendButton.setBounds(130, 280, 100, 30);
        add(sendButton);

        // Κουμπί Ακύρωσης
        JButton cancelButton = new JButton("Ακύρωση");
        cancelButton.setBackground(Color.decode("#FFCC66"));
        cancelButton.setBounds(240, 280, 100, 30);
        add(cancelButton);

        // Λειτουργία αποστολής
        sendButton.addActionListener(e -> {
            String recipient = toField.getText();
            String subject = subjectField.getText();
            String content = contentArea.getText();

            if (!recipient.isEmpty() && !subject.isEmpty() && !content.isEmpty()) {
                parent.addMessage(subject, content);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Συμπλήρωσε όλα τα πεδία.");
            }
        });

        // Λειτουργία ακύρωσης
        cancelButton.addActionListener(e -> dispose());
    }
}
