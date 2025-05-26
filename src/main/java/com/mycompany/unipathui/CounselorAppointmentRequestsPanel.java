package com.mycompany.unipathui;

import com.mycompany.baseClasses.Appointment;
import javax.swing.*;
import java.awt.*;
import java.util.List;

//Panel for Counselor watching the appointments: shows all the available
//appointments student want to make with the counselor.

public class CounselorAppointmentRequestsPanel extends JPanel {
    //ATTRIBUTES
    private final JPanel appointmentsListPanel = new JPanel();
    private final JButton viewDetailsButton = new JButton("Προβολή Λεπτομερειών");
    private Appointment selectedAppointment = null;
    //CONSTRUCTOR
    public CounselorAppointmentRequestsPanel(CardLayout cardLayout, JPanel cardPanel, CounselorAppointmentDetailsPanel detailsPanel) {
        setLayout(new BorderLayout(10, 10));
        
        // --- TOP OF THE PANEL ---
        JLabel title = new JLabel("Εκκρεμή Αιτήματα Ραντεβού", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // --- CENTER PANEL ---
        appointmentsListPanel.setLayout(new BoxLayout(appointmentsListPanel, BoxLayout.Y_AXIS));
        appointmentsListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(appointmentsListPanel);
        add(scrollPane, BorderLayout.CENTER);
        
        // Making of the view Details Button
        viewDetailsButton.addActionListener(e -> {
            if (selectedAppointment != null && "Εκκρεμές".equals(selectedAppointment.status)) {
                detailsPanel.setAppointment(selectedAppointment);
                detailsPanel.showDetails(selectedAppointment.fullName, selectedAppointment.phone, selectedAppointment.email, selectedAppointment.interests);
                cardLayout.show(cardPanel, "appointmentDetails");
            }
            else {
                JOptionPane.showMessageDialog(this, "Το ραντεβού δεν είναι πλέον εκκρεμές.", "Μη διαθέσιμη ενέργεια", JOptionPane.WARNING_MESSAGE);
            }
        });
        // --- BOTTOM PANEL ---
        viewDetailsButton.setEnabled(false); // enabled only when a line is chosen
        loadAppointments(appointmentsListPanel);        
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton backToMain = new JButton("Αρχική Σελίδα");
        backToMain.setBackground(Color.decode("#B3FF66"));
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.decode("#FFCC66"));
        
        backToMain.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));

        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(backToMain);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    //Method to load the appointments on screen: it takes the list from the "Appointment" class
    //and shows in a list the name, the status and the field of interest os students who want to
    //communicate with a counselor (if there are no appointments, it shows nothing).
    private void loadAppointments(JPanel panel) {
        List<Appointment> appointments = Appointment.appointmentsForCounselor;

        if (appointments.isEmpty()) {
            JLabel emptyLabel = new JLabel("Δεν υπάρχουν διαθέσιμα ραντεβού.");
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(emptyLabel);
            return;
        }
        
        String[] columnNames = {"Ονοματεπώνυμο", "Πεδίο Ενδιαφέροντος", "Κατάσταση"};
        Object[][] data = new Object[appointments.size()][3];
        
        for (int i = 0; i < appointments.size(); i++) {
            Appointment app = appointments.get(i);
            data[i][0] = app.fullName;
            data[i][1] = app.interests;
            data[i][2] = app.status;
        }
        
        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // μονή επιλογή
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setGridColor(Color.LIGHT_GRAY);
        
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(600, 300));

        panel.setLayout(new BorderLayout());
        panel.add(tableScroll, BorderLayout.CENTER);
        
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Appointment app = appointments.get(selectedRow);
                if ("Εκκρεμές".equals(app.status)) {
                    viewDetailsButton.setEnabled(true);
                    selectedAppointment = app;
                }
                else {
                    viewDetailsButton.setEnabled(false);
                    selectedAppointment = null;
                }
            }
            else {
                viewDetailsButton.setEnabled(false);
                selectedAppointment = null;
            }
        });
    }
    //Method to refresh the panel when a counselor approves or declines an appointment
    //in order to show the new status of the appointment
    public void refresh() {
        appointmentsListPanel.removeAll(); // Clears previous table
        viewDetailsButton.setEnabled(false);
        loadAppointments(appointmentsListPanel);
        appointmentsListPanel.revalidate(); // Update layout
        appointmentsListPanel.repaint();    // Update view
    }
}