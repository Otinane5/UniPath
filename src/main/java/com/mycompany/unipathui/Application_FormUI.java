package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Application_FormUI extends javax.swing.JPanel {

    public Application_FormUI() {
        initComponents();
    }

 
    private void initComponents() {

        // Initialize components
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();

        // Styling
        setBackground(new Color(240, 240, 240));  // Background color of the form
        setPreferredSize(new Dimension(500, 700));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margin between components

        // Header Label
        jLabel1.setText("Φόρμα Εγγραφής - <Όνομα Πανεπιστημίου>");
        jLabel1.setFont(new Font("SansSerif", Font.BOLD, 18));
        jLabel1.setForeground(new Color(0, 102, 204));  // Blue color for header
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(jLabel1, gbc);

        // Department Label
        jLabel2.setText("Τμήμα: <Όνομα Τμήματος>");
        jLabel2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jLabel2.setForeground(new Color(51, 51, 51));  // Dark gray color
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(jLabel2, gbc);

        // Name and Surname
        jLabel3.setText("Όνομα/Επώνυμο:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(jLabel3, gbc);

        jTextField1.setText("Πληκτρολογήστε το όνομα και το επώνυμο");
        jTextField1.setForeground(new Color(153, 153, 153)); // Placeholder text color
        jTextField1.setColumns(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(jTextField1, gbc);

        // Residence
        jLabel4.setText("Τόπος Διαμονής:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(jLabel4, gbc);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Αθήνα", "Θεσσαλονίκη", "Πάτρα", "Ηράκλειο" }));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(jComboBox1, gbc);

        // Birth Date
        jLabel5.setText("Ημ. Γέννησης:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(jLabel5, gbc);

        jTextField2.setText("Πληκτρολογήστε ημερομηνία γέννησης");
        jTextField2.setForeground(new Color(153, 153, 153)); // Placeholder text color
        jTextField2.setColumns(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(jTextField2, gbc);

        // Phone Number
        jLabel6.setText("Τηλέφωνο:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(jLabel6, gbc);

        jTextField3.setText("Πληκτρολογήστε τον αριθμό τηλεφώνου");
        jTextField3.setForeground(new Color(153, 153, 153)); // Placeholder text color
        jTextField3.setColumns(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(jTextField3, gbc);

        // Email
        jLabel7.setText("Email:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        add(jLabel7, gbc);

        jTextField4.setText("Πληκτρολογήστε το email σας");
        jTextField4.setForeground(new Color(153, 153, 153)); // Placeholder text color
        jTextField4.setColumns(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        add(jTextField4, gbc);

        // Grade Points (Μόρια)
        jLabel8.setText("Μόρια:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        add(jLabel8, gbc);

        jTextField5.setText("Πληκτρολογήστε τα μόρια");
        jTextField5.setForeground(new Color(153, 153, 153)); // Placeholder text color
        jTextField5.setColumns(20);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        add(jTextField5, gbc);

        // Submit Button
        jButton2.setBackground(new Color(0, 204, 102)); // Green color
        jButton2.setText("Υποβολή");
        jButton2.setFont(new Font("SansSerif", Font.BOLD, 14));
        jButton2.setForeground(Color.WHITE);
        jButton2.setPreferredSize(new Dimension(120, 40));
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(jButton2, gbc);

        // Reset Button
        jButton1.setBackground(new Color(255, 51, 51)); // Red color
        jButton1.setText("Επαναφορά");
        jButton1.setFont(new Font("SansSerif", Font.BOLD, 14));
        jButton1.setForeground(Color.WHITE);
        jButton1.setPreferredSize(new Dimension(120, 40));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(jButton1, gbc);

    }// </editor-fold>//GEN-END:initComponents

    // Action methods for buttons (You can implement your actions here)
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // Handle combo box changes if needed
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Reset the form fields if needed
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jComboBox1.setSelectedIndex(0); // Reset combo box to first item
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
