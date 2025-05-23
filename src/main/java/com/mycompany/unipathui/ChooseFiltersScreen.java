package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class ChooseFiltersScreen extends JPanel {
    
    private JTextField name,dept,city,min,max;
    
    public ChooseFiltersScreen(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout(10, 10));
        
        // Title label
        JLabel titleLabel = new JLabel("Ορισμός Φίλτρων", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        // Main Filter Screen
        JLabel departmentLabel= new JLabel("Τμήμα:");
        departmentLabel.setBounds(50,125,120,25);
        add(departmentLabel);
        dept=new JTextField();
        dept.setBounds(180,125,230,25);
        add(dept);
        
        JLabel cityLabel= new JLabel("Πόλη:");
        cityLabel.setBounds(50,160,120,25);
        add(cityLabel);
        city=new JTextField();
        city.setBounds(180,160,230,25);
        add(city);
        
        JLabel minLabel= new JLabel("Ελάχιστα μόρια:");
        minLabel.setBounds(50,195,120,25);
        add(minLabel);
        min=new JTextField();
        min.setBounds(180,195,230,25);
        add(min);
        
        JLabel maxLabel= new JLabel("Μέγιστα μόρια:");
        maxLabel.setBounds(50,230,120,25);
        add(maxLabel);
        max=new JTextField();
        max.setBounds(180,230,230,25);
        add(max);
        
        //+περιορισμός για τα ακέραια min+max (Να μην μπορει σε εκείνα τα πεδία να γράψει αλφαριθμητικό)

        JButton saveButton=new JButton("Αποθήκευση Φίλτρων");
        saveButton.setBounds(50,270,170,30);
        saveButton.setBackground(Color.decode("#66FF66"));
        add(saveButton);
        saveButton.addActionListener(e->saveFilters());
        
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
        
        //add(editPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
/*

        
*/
   }   
    public void saveFilters(){
    }
    public void returnFilteredApplicationList(){
    }
}



