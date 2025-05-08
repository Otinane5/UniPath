package com.mycompany.technlog;

import javax.swing.*;
import java.awt.*;

public class EditProfilePanelUI extends JPanel {
    private JTextField nameField, surnameField, emailField, phoneField;
    private JTextArea bioArea;

    public EditProfilePanelUI(Runnable onBackToMainMenu, Runnable onBack) {
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Επεξεργασία Προφίλ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        nameField = new JTextField("Κώστας");
        surnameField = new JTextField("Παπαδόπουλος");
        emailField = new JTextField("kostas@email.com");
        phoneField = new JTextField("6900000000");
        bioArea = new JTextArea("Εδώ είναι η περιγραφή του συμβούλου.", 3, 20);
        bioArea.setLineWrap(true);

        formPanel.add(new JLabel("Όνομα:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Επώνυμο:"));
        formPanel.add(surnameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Τηλέφωνο:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Περιγραφή (Bio):"));
        formPanel.add(new JScrollPane(bioArea));

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton previewButton = new JButton("Προεπισκόπηση");
        JButton cancelButton = new JButton("Ακύρωση");
        JButton backToMain = new JButton("Πίσω Στο Αρχικό Μενού");
        JButton backButton = new JButton("Πίσω");

        previewButton.setBackground(new Color(100, 149, 237));
        cancelButton.setBackground(new Color(255, 102, 102));
        backToMain.setBackground(new Color(224, 224, 224));
        backButton.setBackground(new Color(255, 229, 180));
        
        // Προεπισκόπηση
        previewButton.addActionListener(e -> {
            String oldName = "Κώστας";
            String oldSurname = "Παπαδόπουλος";
            String oldEmail = "kostas@email.com";
            String oldPhone = "6900000000";
            String oldBio = "Εδώ είναι η περιγραφή του συμβούλου.";

            String newName = nameField.getText();
            String newSurname = surnameField.getText();
            String newEmail = emailField.getText();
            String newPhone = phoneField.getText();
            String newBio = bioArea.getText();

            // Δημιουργία panel για προεπισκόπηση
            JPanel previewPanel = new JPanel(new GridLayout(6, 2, 10, 10));
            previewPanel.setPreferredSize(new Dimension(500, 300));

            previewPanel.add(new JLabel("Όνομα:"));
            previewPanel.add(new JLabel(oldName + "  ➔  " + newName));
            previewPanel.add(new JLabel("Επώνυμο:"));
            previewPanel.add(new JLabel(oldSurname + "  ➔  " + newSurname));
            previewPanel.add(new JLabel("Email:"));
            previewPanel.add(new JLabel(oldEmail + "  ➔  " + newEmail));
            previewPanel.add(new JLabel("Τηλέφωνο:"));
            previewPanel.add(new JLabel(oldPhone + "  ➔  " + newPhone));
            previewPanel.add(new JLabel("Bio:"));
            previewPanel.add(new JLabel("<html>" + oldBio + "<br><b>➔</b><br>" + newBio + "</html>"));

            int result = JOptionPane.showConfirmDialog(this, previewPanel, "Προεπισκόπηση Αλλαγών", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                if (validateFields()) {
                    JOptionPane.showMessageDialog(this, "Οι αλλαγές αποθηκεύτηκαν με επιτυχία.");
                    onBack.run();
                    //onSave.run(); // ή αποθήκευση σε μεταβλητές ή ΒΔ
                }
                else {
                    JOptionPane.showMessageDialog(this, "Συμπληρώστε όλα τα πεδία σωστά.");
                }
            }
        });
        
        // Ακύρωση
        cancelButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Οι αλλαγές θα χαθούν. Θέλετε να συνεχίσετε;", "Ακύρωση", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                    onBack.run();
            }
        });
        
        // Αρχικό Μενού
        backToMain.addActionListener(e -> onBackToMainMenu.run());
        
        // Πίσω
        backButton.addActionListener(e -> onBack.run());

        buttonPanel.add(previewButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(backToMain);
        buttonPanel.add(backButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private boolean validateFields() {
        return !nameField.getText().trim().isEmpty()
            && !surnameField.getText().trim().isEmpty()
            && !emailField.getText().trim().isEmpty()
            && !phoneField.getText().trim().isEmpty();
    }
}
