package com.mycompany.unipathui;

import com.mycompany.baseClasses.Appointment;
import javax.swing.*;
import java.awt.*;

public class CounselorAcceptAppointmentPanel extends JPanel {
    //ATTRIBUTES
    private final JTextField dateField, timeField, methodField;
    private Appointment selectedAppointment;
    //CONSTRUCTOR
    public CounselorAcceptAppointmentPanel(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Σύνταξη Μηνύματος Αποδοχής", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        dateField = new JTextField();
        timeField = new JTextField();
        methodField = new JTextField();
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

        formPanel.add(new JLabel("(Προαιρετικό) Μήνυμα προς Μαθητή:"));
        formPanel.add(new JScrollPane(messageArea));

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton sendButton = new JButton("Αποδοχή Μηνύματος");
        JButton cancelButton = new JButton("Ακύρωση");
        JButton backToMain = new JButton("Αρχική Σελίδα");
        backToMain.setBackground(Color.decode("#B3FF66"));
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.decode("#FFCC66"));

        sendButton.setBackground(new Color(102, 255, 102));
        cancelButton.setBackground(new Color(255, 102, 102));
               
        sendButton.addActionListener(e -> {
            if (validateFields()) {
                if (selectedAppointment != null) {
                    selectedAppointment.setStatus("Εγκεκριμένο");
                }
                JOptionPane.showMessageDialog(this, "Το μήνυμα αποδοχής στάλθηκε με επιτυχία!");
                // Find the panel and make the refresh
                if (cardPanel instanceof JPanel) {
                    for (Component comp : cardPanel.getComponents()) {
                        if (comp instanceof CounselorAppointmentRequestsPanel) {
                            ((CounselorAppointmentRequestsPanel) comp).refresh();
                        break;
                        }
                    }
                }
                cardLayout.show(cardPanel, "appointments");
            }
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
    
    private boolean validateFields() {
        String date = dateField.getText().trim();
        String time = timeField.getText().trim();
        String method = methodField.getText().trim();
        
        if (date.isEmpty() || time.trim().isEmpty() || method.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Συμπληρώστε όλα τα υποχρεωτικά πεδία.");
            return false;
        }
        if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Η ημερομηνία πρέπει να είναι σε μορφή dd/MM/yyyy.", "Μη έγκυρη ημερομηνία", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!time.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Η ώρα πρέπει να είναι σε μορφή HH:mm.", "Μη έγκυρη ώρα", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    //To specify which Appointment is getting "approved"
    public void setSelectedAppointment(Appointment appointment) {
        this.selectedAppointment = appointment;
    }
}