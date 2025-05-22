package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class NewMessageFrame extends JFrame {
    public NewMessageFrame(MessageBoxFrame parent) {
        setTitle("Νέο Μήνυμα");
        setSize(500, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Παραλήπτης
        JLabel toLabel = new JLabel("Παραλήπτης:");
        toLabel.setBounds(30, 30, 100, 25);
        add(toLabel);

        JTextField toField = new JTextField();
        toField.setBounds(130, 30, 300, 25);
        add(toField);

        // Θέμα
        JLabel subjectLabel = new JLabel("Θέμα:");
        subjectLabel.setBounds(30, 70, 100, 25);
        add(subjectLabel);

        JTextField subjectField = new JTextField();
        subjectField.setBounds(130, 70, 300, 25);
        add(subjectField);

        // Περιεχόμενο
        JLabel contentLabel = new JLabel("Περιεχόμενο:");
        contentLabel.setBounds(30, 110, 100, 25);
        add(contentLabel);

        JTextArea contentArea = new JTextArea();
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        contentScrollPane.setBounds(130, 110, 300, 150);
        add(contentScrollPane);

        // Ημερομηνία
        JLabel dateLabel = new JLabel("Ημερομηνία:");
        dateLabel.setBounds(30, 280, 100, 25);
        add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setBounds(130, 280, 300, 25);
        add(dateField);

        // Ώρα
        JLabel timeLabel = new JLabel("Ώρα:");
        timeLabel.setBounds(30, 320, 100, 25);
        add(timeLabel);

        JTextField timeField = new JTextField();
        timeField.setBounds(130, 320, 300, 25);
        add(timeField);

        // Αποστολή
        JButton sendButton = new JButton("Αποστολή");
        sendButton.setBackground(Color.decode("#B3FF66"));
        sendButton.setBounds(130, 400, 100, 30);
        add(sendButton);

        // Ακύρωση
        JButton cancelButton = new JButton("Ακύρωση");
        cancelButton.setBackground(Color.decode("#FFCC66"));
        cancelButton.setBounds(240, 400, 100, 30);
        add(cancelButton);

        // Λειτουργία αποστολής
        sendButton.addActionListener(e -> {
            String recipient = toField.getText();
            String subject = subjectField.getText();
            String content = contentArea.getText();
            String date = dateField.getText();
            String time = timeField.getText();

            if (!recipient.isEmpty() && !subject.isEmpty() && !content.isEmpty() && !date.isEmpty() && !time.isEmpty()) {
                String dateTime = date + " " + time;
                String fullMessage = "Προς: " + recipient + "\n"
                                   + "Θέμα: " + subject + "\n"
                                   + "Ημερομηνία/Ώρα: " + dateTime + "\n\n"
                                   + content;

                parent.addMessage(subject, fullMessage);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Συμπλήρωσε όλα τα πεδία (και ημερομηνία/ώρα).");
            }
        });

        // Λειτουργία ακύρωσης
        cancelButton.addActionListener(e -> dispose());
    }
}
