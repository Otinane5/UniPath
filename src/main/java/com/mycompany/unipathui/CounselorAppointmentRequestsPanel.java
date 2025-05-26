package com.mycompany.unipathui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CounselorAppointmentRequestsPanel extends JPanel {

    public CounselorAppointmentRequestsPanel(CardLayout cardLayout, JPanel cardPanel, CounselorAppointmentDetailsPanel detailsPanel) {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Εκκρεμή Αιτήματα Ραντεβού", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // Δεδομένα για τώρα μόνο (από Βάση Δεδομένων κανονικά)
        String[] columns = {"Όνομα Μαθητή", "Πεδία Ενδιαφέροντος", "Κατάσταση"};
        Object[][] data = {
            {"Μαρία Ιωάννου", "Πληροφορική, Μαθηματικά", "Εκκρεμές", "6941234567", "maria@example.com", },
            {"Γιάννης Παπαδόπουλος", "Μηχανολογία, Φυσική", "Ακυρωμένο", "6971111111", "giannis@example.com", },
            {"Άννα Λεωνίδα", "Ιατρική, Βιολογία", "Εγκεκριμένο", "6987654321", "anna@example.com", }
        };
        
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        JButton viewDetailsButton = new JButton("Προβολή Λεπτομερειών");
        viewDetailsButton.setEnabled(false); // ενεργοποιείται μόνο όταν επιλεγεί γραμμή

        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String status = table.getValueAt(selectedRow, 2).toString();
                viewDetailsButton.setEnabled("Εκκρεμές".equals(status));
            }
            else {
                viewDetailsButton.setEnabled(false);
            }
        });

        viewDetailsButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String name = model.getValueAt(selectedRow, 0).toString();
                String interests = model.getValueAt(selectedRow, 1).toString();
                
                // τα έξτρα πεδία (μόνο από το original data array)
                String phone = data[selectedRow][3].toString();
                String email = data[selectedRow][4].toString();
                
                detailsPanel.showDetails(name, phone, email, interests);
                cardLayout.show(cardPanel, "appointmentDetails");
            }
        });

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
}
