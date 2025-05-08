package com.mycompany.unipathui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CounselorAppointmentRequestsPanel extends JPanel {

    public CounselorAppointmentRequestsPanel(CardLayout cardLayout, JPanel cardPanel, CounselorAppointmentDetailsPanel detailsPanel) {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Εκκρεμή Αιτήματα Ραντεβού", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // Δεδομένα για τώρα μόνο (από Βάση Δεδομένων κανονικά)
        String[] columns = {"Όνομα Μαθητή", "Πεδία Ενδιαφέροντος"};
        Object[][] data = {
            {"Μαρία Ιωάννου", "Πληροφορική, Μαθηματικά", "6941234567", "maria@example.com", },
            {"Γιάννης Παπαδόπουλος", "Μηχανολογία, Φυσική", "6971111111", "giannis@example.com", },
            {"Άννα Λεωνίδα", "Ιατρική, Βιολογία", "6987654321", "anna@example.com", }
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        JButton viewDetailsButton = new JButton("Προβολή Λεπτομερειών");
        viewDetailsButton.setEnabled(false); // ενεργοποιείται μόνο όταν επιλεγεί γραμμή

        table.getSelectionModel().addListSelectionListener(e -> {
            viewDetailsButton.setEnabled(table.getSelectedRow() != -1);
        });

        viewDetailsButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String name = model.getValueAt(selectedRow, 0).toString();
                String interests = model.getValueAt(selectedRow, 1).toString();
                
                // τα έξτρα πεδία (μόνο από το original data array)
                String phone = data[selectedRow][2].toString();
                String email = data[selectedRow][3].toString();
                
                detailsPanel.showDetails(name, phone, email, interests);
                cardLayout.show(cardPanel, "appointmentDetails");
            }
        });


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton backToMain = new JButton("Πίσω Στο Αρχικό Μενού");
        JButton backButton = new JButton("Πίσω");
        
        backToMain.setBackground(new Color(224, 224, 224));
        backButton.setBackground(new Color(255, 229, 180));

        backToMain.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));

        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(backToMain);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
