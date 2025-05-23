package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class ChooseFiltersScreen extends JPanel {
    
    private JTextField dept,city,min,max;
    
    public ChooseFiltersScreen(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout(10, 10));
        
        // Title label
        JLabel titleLabel = new JLabel("Ορισμός Φίλτρων", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);
        
        // Central Panel with Fields
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Ετικέτες και πεδία
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(new JLabel("Τμήμα:"), gbc);
        gbc.gridx = 1;
        dept = new JTextField(20);
        centerPanel.add(dept, gbc);
        
        gbc.gridx = 0; gbc.gridy++;
        centerPanel.add(new JLabel("Πόλη:"), gbc);
        gbc.gridx = 1;
        city = new JTextField(20);
        centerPanel.add(city, gbc);
        
        gbc.gridx = 0; gbc.gridy++;
        centerPanel.add(new JLabel("Ελάχιστα μόρια:"), gbc);
        gbc.gridx = 1;
        min = new JTextField(20);
        centerPanel.add(min, gbc);
        
        gbc.gridx = 0; gbc.gridy++;
        centerPanel.add(new JLabel("Μέγιστα μόρια:"), gbc);
        gbc.gridx = 1;
        max = new JTextField(20);
        centerPanel.add(max, gbc);
        
        //+περιορισμός για τα ακέραια min+max (Να μην μπορει σε εκείνα τα πεδία να γράψει αλφαριθμητικό)
        
        // Save Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton saveButton=new JButton("Αποθήκευση Φίλτρων");
        saveButton.setBackground(Color.decode("#66FF66"));
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.addActionListener(e->saveFilters());
        centerPanel.add(saveButton, gbc);
        
        add(centerPanel, BorderLayout.CENTER);
        
        // BOTTOM BUTTONS
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton homeButton = new JButton("Αρχική Σελίδα");
        homeButton.setBackground(Color.decode("#B3FF66"));
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        
        JButton back= new JButton("Πίσω");
        back.setBackground(Color.decode("#FFCC66"));
        back.addActionListener(e -> cardLayout.show(cardPanel, "applications"));
        
        buttonPanel.add(homeButton);
        buttonPanel.add(back);
        add(buttonPanel, BorderLayout.SOUTH);
}
    public void saveFilters(){
        // Προαιρετικά: έλεγχος για αριθμούς
        try {
            if (!min.getText().isEmpty()) Integer.parseInt(min.getText());
            if (!max.getText().isEmpty()) Integer.parseInt(max.getText());
            JOptionPane.showMessageDialog(this, "Τα φίλτρα αποθηκεύτηκαν!");
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Παρακαλώ εισάγετε έγκυρα αριθμητικά όρια.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void returnFilteredApplicationList(){
    }
}



