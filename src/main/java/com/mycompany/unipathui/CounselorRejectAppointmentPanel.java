package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class CounselorRejectAppointmentPanel extends JPanel {

    public CounselorRejectAppointmentPanel(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Σύνταξη Μηνύματος Απόρριψης", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JTextArea messageArea = new JTextArea(10, 30);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setBorder(BorderFactory.createTitledBorder("Προαιρετικό σχόλιο"));

        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton sendButton = new JButton("Αποστολή");
        JButton cancelButton = new JButton("Ακύρωση Απόρριψης");
        JButton backToMain = new JButton("Πίσω Στο Αρχικό Μενού");
        JButton backButton = new JButton("Πίσω");
        
        sendButton.setBackground(new Color(102, 255, 102));
        cancelButton.setBackground(new Color(255, 102, 102));
        backToMain.setBackground(new Color(224, 224, 224));
        backButton.setBackground(new Color(255, 229, 180));
        
        sendButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Το μήνυμα απόρριψης στάλθηκε!");
            cardLayout.show(cardPanel, "appointments");
        });
        cancelButton.addActionListener(e -> cardLayout.show(cardPanel, "appointments"));
        backToMain.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "appointmentDetails"));

        buttonPanel.add(sendButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(backToMain);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}