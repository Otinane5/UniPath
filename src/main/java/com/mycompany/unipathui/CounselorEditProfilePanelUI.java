package com.mycompany.unipathui;

import com.mycompany.baseClasses.Counselor;
import javax.swing.*;
import java.awt.*;

public class CounselorEditProfilePanelUI extends JPanel {
    //ATTRIBUTES
    private JTextField nameField, surnameField, emailField, phoneField;
    private JTextArea bioArea;
    //CONSTRUCTOR
    public CounselorEditProfilePanelUI(CardLayout cardLayout, JPanel cardPanel, CounselorProfilePanel profilePanel) {
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Επεξεργασία Προφίλ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        //JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        //formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        
        Counselor counselor = CounselorProfilePanel.CounselorToDisplay;

        //nameField = new JTextField(counselor.name);
        //surnameField = new JTextField(counselor.lastName);
        //emailField = new JTextField(counselor.email);
        //phoneField = new JTextField(counselor.phoneNum);
        
        //formPanel.add(new JLabel("Όνομα:"));
        //formPanel.add(nameField);
        formPanel.add(makeLabeledField("Όνομα:", nameField = new JTextField(counselor.name), labelFont, fieldFont));
        //formPanel.add(new JLabel("Επώνυμο:"));
        //formPanel.add(surnameField);
        formPanel.add(makeLabeledField("Επώνυμο:", surnameField = new JTextField(counselor.lastName), labelFont, fieldFont));
        //formPanel.add(new JLabel("Email:"));
        //formPanel.add(emailField);
        formPanel.add(makeLabeledField("Email:", emailField = new JTextField(counselor.email), labelFont, fieldFont));
        //formPanel.add(new JLabel("Τηλέφωνο:"));
        //formPanel.add(phoneField);
        formPanel.add(makeLabeledField("Τηλέφωνο:", phoneField = new JTextField(counselor.phoneNum), labelFont, fieldFont));
        bioArea = new JTextArea(counselor.bio, 4, 20);
        bioArea.setFont(fieldFont);
        bioArea.setLineWrap(true);
        bioArea.setWrapStyleWord(true);
        JScrollPane bioScrollPane = new JScrollPane(bioArea);
        formPanel.add(makeLabeledField("Περιγραφή (Bio):", bioScrollPane, labelFont, fieldFont));

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton previewButton = new JButton("Προεπισκόπηση");
        JButton cancelButton = new JButton("Ακύρωση");
        JButton backToMain = new JButton("Αρχική Σελίδα");
        backToMain.setBackground(Color.decode("#B3FF66"));
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.decode("#FFCC66"));

        previewButton.setBackground(new Color(100, 149, 237));
        cancelButton.setBackground(new Color(255, 102, 102));
        
        // Προεπισκόπηση
        previewButton.addActionListener(e -> {
            String oldName = counselor.name;
            String oldSurname = counselor.lastName;
            String oldEmail = counselor.email;
            String oldPhone = counselor.phoneNum;
            String oldBio = counselor.bio;

            String newName = nameField.getText();
            String newSurname = surnameField.getText();
            String newEmail = emailField.getText();
            String newPhone = phoneField.getText();
            String newBio = bioArea.getText();

            // Δημιουργία panel για προεπισκόπηση
            JPanel previewPanel = new JPanel();
            previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));
            previewPanel.setPreferredSize(new Dimension(500, 300));
            
            Font previewFont = new Font("Arial", Font.PLAIN, 14);
            String[] fields = { "Όνομα", "Επώνυμο", "Email", "Τηλέφωνο", "Bio" };
            String[] oldValues = { oldName, oldSurname, oldEmail, oldPhone, oldBio };
            String[] newValues = { newName, newSurname, newEmail, newPhone, newBio };
            
            for (int i = 0; i < fields.length; i++) {
                JLabel label = new JLabel("<html><b>" + fields[i] + ":</b><br>" +
                        "<span style='color:gray'>" + oldValues[i] + "</span>  ->  " +
                        "<span style='color:green'>" + newValues[i] + "</span></html>");
                label.setFont(previewFont);
                label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                previewPanel.add(label);
            }

            int result = JOptionPane.showConfirmDialog(this, previewPanel, "Προεπισκόπηση Αλλαγών", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                if (validateFields()) {
                    // Αποθήκευση αλλαγών στον CounselorToDisplay
                    counselor.name = newName;
                    counselor.lastName = newSurname;
                    counselor.email = newEmail;
                    counselor.phoneNum = newPhone;
                    counselor.bio = newBio;
                    
                    profilePanel.refresh(); // Update the panel to show the new info
                    JOptionPane.showMessageDialog(this, "Οι αλλαγές αποθηκεύτηκαν με επιτυχία.");
                    cardLayout.show(cardPanel, "profile");
                    //onSave.run(); // ή αποθήκευση σε μεταβλητές ή ΒΔ
                }                
            }
        });
        
        // Ακύρωση
        cancelButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Οι αλλαγές θα χαθούν. Θέλετε να συνεχίσετε;", "Ακύρωση", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                    cardLayout.show(cardPanel, "profile");
            }
        });
        
        // Αρχικό Μενού
        backToMain.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        
        // Πίσω
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "profile"));
        
        buttonPanel.add(previewButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(backToMain);
        buttonPanel.add(backButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    //When the counselor is updating, they should be writing valid data in the fields
    private boolean validateFields() {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        String phoneRegex = "^\\d{10}$"; // δέχεται μόνο 10ψήφιο αριθμό
        
        if (nameField.getText().trim().isEmpty() ||
            surnameField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty() ||
            phoneField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Συμπληρώστε όλα τα πεδία.");
            return false;
        }
        if (!emailField.getText().matches(emailRegex)) {
            JOptionPane.showMessageDialog(this, "Εισάγετε έγκυρο email.");
            return false;
        }
        if (!phoneField.getText().matches(phoneRegex)) {
            JOptionPane.showMessageDialog(this, "Εισάγετε έγκυρο αριθμό τηλεφώνου (10 ψηφία).");
            return false;
        }
        return true;
    }
    private JPanel makeLabeledField(String labelText, JComponent field, Font labelFont, Font fieldFont) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        field.setFont(fieldFont);
        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        return panel;
    }
}
