package com.mycompany.unipathui;

import com.mycompany.baseClasses.Appointment;
import javax.swing.*;
import java.awt.*;

//Panel for Counselor watching the details of an appointment: shows
//the specific appointment a student wants to make with the counselor.

public class CounselorAppointmentDetailsPanel extends JPanel {
    //ATTRIBUTES
    private final JLabel nameLabel;
    private final JLabel phoneLabel;
    private final JLabel emailLabel;
    private final JTextArea interestsArea;
    private Appointment appointment;
    //CONSTRUCTOR
    public CounselorAppointmentDetailsPanel(CardLayout cardLayout, JPanel cardPanel, CounselorAcceptAppointmentPanel acceptPanel, CounselorRejectAppointmentPanel rejectPanel) {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Λεπτομέρειες Αιτήματος", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        nameLabel = new JLabel("Όνομα Μαθητή: ");
        phoneLabel = new JLabel("Τηλέφωνο: ");
        emailLabel = new JLabel("Email: ");
        interestsArea = new JTextArea();
        interestsArea.setEditable(false);
        interestsArea.setLineWrap(true);
        interestsArea.setWrapStyleWord(true);
        interestsArea.setBorder(BorderFactory.createTitledBorder("Πεδία Ενδιαφέροντος"));
        
        // Center align the labels for it to be "prettier"
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        phoneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        interestsArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        detailsPanel.add(nameLabel);
        detailsPanel.add(phoneLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailsPanel.add(emailLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailsPanel.add(interestsArea);

        add(detailsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton acceptAppointment = new JButton("Αποδοχή");
        JButton rejectAppointment = new JButton("Απόρριψη");
        JButton backToMain = new JButton("Αρχική Σελίδα");
        backToMain.setBackground(Color.decode("#B3FF66"));
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.decode("#FFCC66"));
        
        acceptAppointment.setBackground(new Color(102, 255, 102));
        rejectAppointment.setBackground(new Color(255, 102, 102));
        
        acceptAppointment.addActionListener(e -> {
            acceptPanel.setSelectedAppointment(appointment);
            cardLayout.show(cardPanel, "acceptAppointment");
        });
        rejectAppointment.addActionListener(e -> {
            rejectPanel.setSelectedAppointment(appointment);
            cardLayout.show(cardPanel, "rejectAppointment");
                });
        backToMain.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "appointments"));
        
        buttonPanel.add(acceptAppointment);
        buttonPanel.add(rejectAppointment);
        buttonPanel.add(backToMain);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    //Method to show the details of an appointment
    public void showDetails(String name, String phone, String email, String interests) {
        nameLabel.setText("Όνομα Μαθητή: " + name);
        phoneLabel.setText("Τηλέφωνο: " + phone);
        emailLabel.setText("Email: " + email);
        interestsArea.setText(interests);
    }
    //To show which is the appointment the Councelor is wathing the details of
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}