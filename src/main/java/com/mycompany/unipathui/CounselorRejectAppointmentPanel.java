package com.mycompany.unipathui;

import com.mycompany.baseClasses.Appointment;

import javax.swing.*;
import java.awt.*;

public class CounselorRejectAppointmentPanel extends JPanel {
    //ATTRIBUTES
    private Appointment selectedAppointment;
    //CONSTRUCTOR
    public CounselorRejectAppointmentPanel(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Σύνταξη Μηνύματος Απόρριψης", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JTextArea messageArea = new JTextArea(10, 30);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setBorder(BorderFactory.createTitledBorder("Προαιρετικό σχόλιο"));

        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton sendButton = new JButton("Αποστολή");
        JButton cancelButton = new JButton("Ακύρωση Απόρριψης");
        JButton backToMain = new JButton("Αρχική Σελίδα");
        backToMain.setBackground(Color.decode("#B3FF66"));
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.decode("#FFCC66"));

        
        sendButton.setBackground(new Color(102, 255, 102));
        cancelButton.setBackground(new Color(255, 102, 102));
                
        sendButton.addActionListener(e -> {
            if (selectedAppointment != null) {
                selectedAppointment.setStatus("Ακυρωμένο");
            }
            JOptionPane.showMessageDialog(this, "Το μήνυμα αποδοχής στάλθηκε με επιτυχία!");
            // Βρες το panel και κάνε refresh
            if (cardPanel instanceof JPanel) {
                for (Component comp : cardPanel.getComponents()) {
                    if (comp instanceof CounselorAppointmentRequestsPanel) {
                        ((CounselorAppointmentRequestsPanel) comp).refresh();
                        break;
                    }
                }
            }
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
    //To specify which Appointment is getting "approved"
    public void setSelectedAppointment(Appointment appointment) {
        this.selectedAppointment = appointment;
    }
}