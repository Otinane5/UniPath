package com.mycompany.unipathui;

import com.mycompany.baseClasses.Application;
import com.mycompany.baseClasses.ApplicationSender;
import com.mycompany.baseClasses.Unipath;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DepartmentApplicationUI extends JPanel {

    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8;
    private JTextField jTextField1, jTextField2, jTextField3, jTextField4, jTextField5;
    private JComboBox<String> jComboBox1;
    private JButton jButton1, jButton2;
    public static String uniName;
    private String department; //unusable
    
    public DepartmentApplicationUI() {
        openDepartmentApplication();
    }

    private void openDepartmentApplication() {
        setBackground(new Color(240, 240, 240));
        setPreferredSize(new Dimension(500, 700));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Labels
        jLabel1 = new JLabel("Φόρμα Εγγραφής:");
        jLabel1.setFont(new Font("SansSerif", Font.BOLD, 18));
        jLabel1.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        add(jLabel1, gbc);

        jLabel2 = new JLabel("*");
        //jLabel2 = new JLabel("Τμήμα: "+uniName);
        jLabel2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jLabel2.setForeground(new Color(51, 51, 51));
        gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        add(jLabel2, gbc);

        jLabel3 = new JLabel("Όνομα/Επώνυμο:");
        jLabel4 = new JLabel("Τόπος Διαμονής:");
        jLabel5 = new JLabel("Ημ. Γέννησης:");
        jLabel6 = new JLabel("Τηλέφωνο:");
        jLabel7 = new JLabel("Email:");
        jLabel8 = new JLabel("Μόρια:");

        // Fields
        jTextField1 = new JTextField(20);
        jTextField2 = new JTextField(20);
        jTextField3 = new JTextField(20);
        jTextField4 = new JTextField(20);
        jTextField5 = new JTextField(20);
        jComboBox1 = new JComboBox<>(new String[]{"Αθήνα", "Θεσσαλονίκη", "Πάτρα", "Ηράκλειο", "Άλλη","Εξωτερικό"});

        //πρέπει να σβήνει ο χρήστης καθε φορά από το πλαίσιο το πληκτρολογήστε... 
        Color placeholderColor = new Color(60, 60, 60);
        jTextField1.setForeground(placeholderColor);
        jTextField1.setText(Unipath.currentUser.userName);

        jTextField2.setForeground(placeholderColor);

        jTextField3.setForeground(placeholderColor);

        jTextField4.setForeground(placeholderColor);

        jTextField5.setForeground(placeholderColor);

        // Buttons
        jButton1 = new JButton("Επαναφορά");
        jButton1.setBackground(new Color(255, 51, 51));
        jButton1.setForeground(Color.WHITE);
        jButton1.setFont(new Font("SansSerif", Font.BOLD, 14));
        jButton1.setPreferredSize(new Dimension(120, 40));
        jButton1.addActionListener(this::resetForm);

        jButton2 = new JButton("Υποβολή");
        jButton2.setBackground(new Color(0, 204, 102));
        jButton2.setForeground(Color.WHITE);
        jButton2.setFont(new Font("SansSerif", Font.BOLD, 14));
        jButton2.setPreferredSize(new Dimension(120, 40));
        jButton2.addActionListener(this::submitForm);

        //+ κουμπί "πίσω" και "Αρχική σελίδα"
        
        // Layout
        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;

        gbc.gridy = 2; gbc.gridx = 0; add(jLabel3, gbc); gbc.gridx = 1; add(jTextField1, gbc);
        gbc.gridy = 3; gbc.gridx = 0; add(jLabel4, gbc); gbc.gridx = 1; add(jComboBox1, gbc);
        gbc.gridy = 4; gbc.gridx = 0; add(jLabel5, gbc); gbc.gridx = 1; add(jTextField2, gbc);
        gbc.gridy = 5; gbc.gridx = 0; add(jLabel6, gbc); gbc.gridx = 1; add(jTextField3, gbc);
        gbc.gridy = 6; gbc.gridx = 0; add(jLabel7, gbc); gbc.gridx = 1; add(jTextField4, gbc);
        gbc.gridy = 7; gbc.gridx = 0; add(jLabel8, gbc); gbc.gridx = 1; add(jTextField5, gbc);

        gbc.gridy = 8; gbc.gridx = 0; add(jButton1, gbc); gbc.gridx = 1; add(jButton2, gbc);
    }

    private void resetForm(ActionEvent evt) {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jComboBox1.setSelectedIndex(0);
    }

    private void submitForm(ActionEvent evt) {
        String fullName = jTextField1.getText().trim();
        String residence = jComboBox1.getSelectedItem().toString();
        String birthDate = jTextField2.getText().trim();
        String phone = jTextField3.getText().trim();
        String email = jTextField4.getText().trim();
        String gradePoints = jTextField5.getText().trim();

        // Check if all fields are filled
        if (fullName.isEmpty() || residence.isEmpty() || birthDate.isEmpty() || phone.isEmpty() || email.isEmpty() || gradePoints.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Παρακαλώ συμπληρώστε όλα τα πεδία.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ask for confirmation before submitting
        int response = JOptionPane.showConfirmDialog(this, "Είστε σίγουροι ότι θέλετε να υποβάλετε την αίτηση;", 
                                                     "Επιβεβαίωση Υποβολής", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            Application newApp = new Application(fullName, residence, birthDate, phone, email, gradePoints,department);
            Application.sample.add(newApp);
            JOptionPane.showMessageDialog(this, "Η αίτηση υποβλήθηκε επιτυχώς!Θα λάβετε επιβεβαιωτικό μήνυμα σύντομα");
            ApplicationSender.send();
            resetForm(null);
        }
    }
}
