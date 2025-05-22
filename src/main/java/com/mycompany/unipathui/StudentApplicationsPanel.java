package com.mycompany.unipathui;

import com.mycompany.baseClasses.Application;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentApplicationsPanel extends JPanel {
    
    private final JPanel applicationListPanel = new JPanel();

    public StudentApplicationsPanel(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout(10, 10));
        
        // Top Tools
        JPanel topTools = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topTools.setBackground(Color.WHITE);

        JLabel sectionLabel = new JLabel("Αιτήσεις Εγγραφής");
        sectionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topTools.add(sectionLabel);

        JButton filt = new JButton("Ορισμός Φίλτρων");
        filt.setBackground(new Color(180, 210, 240));
        filt.setFocusPainted(false);
        filt.addActionListener(e -> new ChooseFiltersScreen().setVisible(true));
        topTools.add(filt);

        add(topTools, BorderLayout.BEFORE_FIRST_LINE);

        // Application Panel
        applicationListPanel.setLayout(new BoxLayout(applicationListPanel, BoxLayout.Y_AXIS));
        applicationListPanel.setBackground(Color.WHITE);
        applicationListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        loadApplications(applicationListPanel);

        JScrollPane scrollPane = new JScrollPane(applicationListPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Footer Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(Color.WHITE);

        JButton logout = new JButton("Αποσύνδεση");
        logout.setBackground(Color.decode("#FF6666"));
        bottomPanel.add(logout);

        JButton homeButton = new JButton("Αρχική Σελίδα");
        homeButton.setBackground(Color.decode("#B3FF66"));
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        bottomPanel.add(homeButton);

        JButton back = new JButton("Πίσω");
        back.setBackground(Color.decode("#FFCC66"));
        back.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        bottomPanel.add(back);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void loadApplications(JPanel panel) {
        List<Application> applications = Application.sample;

        if (applications.isEmpty()) {
            JLabel emptyLabel = new JLabel("Δεν υπάρχουν διαθέσιμες αιτήσεις.");
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(emptyLabel);
            return;
        }

        int i = 1;
        for (Application app : applications) {
            JPanel appPanel = new JPanel();
            appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.Y_AXIS));
            appPanel.setPreferredSize(new Dimension(600, 130));
            appPanel.setMaximumSize(new Dimension(600, 130));
            appPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            appPanel.setBackground(Color.WHITE);

            JLabel applicationLabel = new JLabel("Αίτηση " + i++);
            applicationLabel.setFont(new Font("Arial", Font.BOLD, 14));
            appPanel.add(applicationLabel);

            JLabel nameLabel = new JLabel("Ονοματεπώνυμο: " + app.fullName);
            appPanel.add(nameLabel);

            JLabel residenceLabel = new JLabel("Τόπος Διαμονής: " + app.residence);
            appPanel.add(residenceLabel);

            JLabel gradesLabel = new JLabel("Μόρια: " + app.gradePoints);
            appPanel.add(gradesLabel);

            JLabel stateLabel = new JLabel("Κατάσταση: " + translateState(app.state));
            stateLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            appPanel.add(stateLabel);

            // Only show buttons if the state is "sent"
            if ("sent".equals(app.state)) {
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // Layout to make buttons visible

                JButton accept = new JButton("Αποδοχή");
                accept.setBackground(new Color(0, 200, 100));
                accept.addActionListener(e -> {
                    app.state = "approved";
                    refresh();
                });
                buttonPanel.add(accept);

                JButton reject = new JButton("Απόρριψη");
                reject.setBackground(new Color(200, 0, 0));
                reject.setForeground(Color.WHITE);
                reject.addActionListener(e -> {
                    app.state = "rejected";
                    refresh();
                });
                buttonPanel.add(reject);

                appPanel.add(buttonPanel); // Add buttons to the panel
            }

            panel.add(appPanel);
            panel.add(Box.createVerticalStrut(10)); // Space between applications
        }
    }

    private String translateState(String state) {
        return switch (state) {
            case "approved" -> "Εγκεκριμένη";
            case "rejected" -> "Απορριφθείσα";
            default -> "Υποβληθείσα";
        };
    }

    public void refresh() {
        applicationListPanel.removeAll();       // καθάρισε τις προηγούμενες αιτήσεις
        loadApplications(applicationListPanel); // φόρτωσε ξανά από τη λίστα Application.sample
        applicationListPanel.revalidate();      // ενημέρωση layout
        applicationListPanel.repaint();         // redraw UI
    }
    
    public void viewApplications() {
        // For future use
    }

    public void applyFilters() {
        // For future use
    }
}
