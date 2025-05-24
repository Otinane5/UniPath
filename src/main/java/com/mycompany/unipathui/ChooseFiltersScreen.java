package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class ChooseFiltersScreen extends JPanel {
    
    private JTextField dept,city,min,max,status;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final FilteredListScreen filteredListScreen;
    
    public ChooseFiltersScreen(CardLayout cardLayout, JPanel cardPanel, FilteredListScreen filteredListScreen) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.filteredListScreen = filteredListScreen;
        
        setLayout(new BorderLayout(10, 10));
        
        // Title label
        JLabel titleLabel = new JLabel("Ορισμός Φίλτρων", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);
        
        // Central Panel with Fields
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        centerPanel.setBackground(new Color(245,245,245));
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
        centerPanel.add(new JLabel("Τόπος Διαμονής:"), gbc);
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
        
        gbc.gridx = 0; gbc.gridy++;
        centerPanel.add(new JLabel("Κατάσταση:"), gbc);
        gbc.gridx = 1;
        status=new JTextField(20);
        centerPanel.add(status,gbc);
        
        //+πχ εγκριθείσα=ΕΓΚΡΙΘΕΙΣΑ...
        //+περιορισμός για τα ακέραια min+max (Να μην μπορει σε εκείνα τα πεδία να γράψει αλφαριθμητικό)
        
        // Save Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JButton saveButton=new JButton("Αποθήκευση Φίλτρων");
        saveButton.setBackground(Color.decode("#66FF66"));
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        //αποθήκευση και με enter
        saveButton.addActionListener(e->saveFilters());
        dept.addActionListener(e -> saveFilters());
        city.addActionListener(e -> saveFilters());
        min.addActionListener(e -> saveFilters());
        max.addActionListener(e -> saveFilters());
        status.addActionListener(e -> saveFilters());

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
        
        buttonPanel.setBackground(new Color(245,245,245)); 
        //todo: γκρι και εκει που ειναι τα κουμπιά πισω αποθηκευση
        
        buttonPanel.add(homeButton);
        buttonPanel.add(back);
        add(buttonPanel, BorderLayout.SOUTH);
}
    
    public void saveFilters(){
        // έλεγχος για τα μόρια
        try {
            Integer minValue = min.getText().isEmpty() ? null : Integer.parseInt(min.getText());
            Integer maxValue = max.getText().isEmpty() ? null : Integer.parseInt(max.getText());
            String statusFilter = status.getText().trim();

            JOptionPane.showMessageDialog(this, "Τα φίλτρα αποθηκεύτηκαν με επιτυχία!");
            
            if(filteredListScreen != null)
            {
                filteredListScreen.setFilters(dept.getText(), city.getText(), minValue, maxValue,statusFilter);
                cardLayout.show(cardPanel, "filteredList");
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Σφάλμα!\n Η προβολή φιλτραρισμένων αιτήσεων δεν βρέθηκε.");
            }
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Παρακαλώ εισάγετε έγκυρα αριθμητικά όρια.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void returnFilteredApplicationList()
    {}
}
