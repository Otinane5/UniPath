package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class CounselorAcceptAppointmentPanel extends JPanel {

    public CounselorAcceptAppointmentPanel(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Σύνταξη Μηνύματος Αποδοχής", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JTextField dateField = new JTextField();
        JTextField timeField = new JTextField();
        JTextField methodField = new JTextField();
        JTextArea messageArea = new JTextArea(5, 30);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);

        formPanel.add(new JLabel("Ημερομηνία Ραντεβού:"));
        formPanel.add(dateField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        formPanel.add(new JLabel("Ώρα Ραντεβού:"));
        formPanel.add(timeField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        formPanel.add(new JLabel("Τρόπος Επικοινωνίας:"));
        formPanel.add(methodField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        formPanel.add(new JLabel("Μήνυμα προς Μαθητή:"));
        formPanel.add(new JScrollPane(messageArea));

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton sendButton = new JButton("Αποδοχή Μηνύματος");
        JButton cancelButton = new JButton("Ακύρωση");
        JButton backToMain = new JButton("Πίσω Στο Αρχικό Μενού");
        backToMain.setBackground(Color.decode("#B3FF66"));
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.decode("#FFCC66"));

        sendButton.setBackground(new Color(102, 255, 102));
        cancelButton.setBackground(new Color(255, 102, 102));
        //backToMain.setBackground(new Color(224, 224, 224));
        //backButton.setBackground(new Color(255, 229, 180));
       
        sendButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Το μήνυμα αποδοχής στάλθηκε με επιτυχία!");
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
