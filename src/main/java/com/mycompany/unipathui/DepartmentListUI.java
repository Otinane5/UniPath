package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class DepartmentListUI extends JPanel {
    // ATTRIBUTES
    private String selectedDepartment = null; // Αποθήκευση επιλεγμένου τμήματος
    private JButton currentlySelectedButton = null;

    public DepartmentListUI(Runnable onBackToMainMenu, Consumer<String> onShowDepartment, Runnable onApplicationForm) {
        setLayout(new BorderLayout(10, 10));

        // Header label
        JLabel label = new JLabel("Λίστα Τμημάτων", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(label, BorderLayout.NORTH);

        // List of departments
        List<String> departments = List.of(
            "Τμήμα Πληροφορικής",
            "Τμήμα Μηχανικών Υπολογιστών",
            "Τμήμα Νομικής",
            "Τμήμα Ιατρικής",
            "Τμήμα Ψυχολογίας",
            "Τμήμα Καλών Τεχνών",
            "Τμήμα Φιλοσοφίας",
            "Τμήμα Φιλολογίας",
            "Τμήμα Διοίκησης Επιχειρήσεων",
            "Τμήμα Οικονομικών",
            "Τμήμα Φαρμακευτικής",
            "Τμήμα Μαθηματικών",
            "Τμήμα Χημικών Μηχανικών",
            "Τμήμα Λογοθεραπείας"
        );

        // Panel for department buttons
        JPanel departmentPanel = new JPanel();
        departmentPanel.setLayout(new BoxLayout(departmentPanel, BoxLayout.Y_AXIS));

        // Add buttons for each department
        for (String dept : departments) {
            JButton deptButton = new JButton(dept);
            deptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            deptButton.setMaximumSize(new Dimension(400, 40));
            deptButton.addActionListener(e -> {
                selectedDepartment = dept; // Set selected department
                // Highlight selected department visually
                if (currentlySelectedButton != null) {
                    currentlySelectedButton.setBackground(null); // Reset previously selected button
                }
                deptButton.setBackground(Color.LIGHT_GRAY); // Highlight selected button
                currentlySelectedButton = deptButton;
            });

            departmentPanel.add(Box.createVerticalStrut(10));
            departmentPanel.add(deptButton);
        }

        JScrollPane scrollPane = new JScrollPane(departmentPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel with buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Back button
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.YELLOW);
        backButton.addActionListener(e -> {
            onBackToMainMenu.run(); // Call onBackToMainMenu action
        });

        // Show Department button
        JButton showButton = new JButton("Προβολή Τμήματος");
        showButton.setBackground(Color.CYAN);
        showButton.addActionListener(e -> {
            if (selectedDepartment == null) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ επιλέξτε ένα τμήμα πρώτα.");
            } else {
                // Show the selected department details UI
                onShowDepartment.accept(selectedDepartment);
            }
        });

        // Application Form button
        JButton applicationButton = new JButton("Αίτηση Εγγραφής");
        applicationButton.setBackground(Color.GREEN);
        applicationButton.addActionListener(e -> {
            if (selectedDepartment == null) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ επιλέξτε ένα τμήμα πρώτα.");
            } else {
                // Create an instance of Application_FormUI and show it
                Application_FormUI applicationFormUI = new Application_FormUI(selectedDepartment);
                JFrame formFrame = new JFrame("Φόρμα Εγγραφής");
                formFrame.setSize(600, 600);
                formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                formFrame.add(applicationFormUI);
                formFrame.setVisible(true);
            }
        });

        // Add buttons to bottom panel
        bottomPanel.add(backButton);
        bottomPanel.add(showButton);
        bottomPanel.add(applicationButton);

        // Add the bottom panel to the main panel
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
