package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class AddAnnouncementPanel extends JPanel {
    private String departmentName;
    
    public AddAnnouncementPanel(CardLayout cardLayout, JPanel cardPanel, String departmentName) {
        this.departmentName=departmentName;
        setLayout(new BorderLayout(10, 10));
        
        // Title label
        JLabel titleLabel = new JLabel("Προσθήκη Νέας Ανακοίνωσης Τμήματος", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        // Main announcement panel
        JPanel annPanel= new JPanel(new GridBagLayout());
        annPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        // Department
        JLabel departmentLabel= new JLabel("Όνομα Τμήματος", SwingConstants.CENTER);
        departmentLabel.setFont(new Font("Arial", Font.BOLD,16));
        departmentLabel.setOpaque(true);
        departmentLabel.setBackground(Color.GREEN);
        departmentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        annPanel.add(departmentLabel, gbc);
        
        gbc.gridy++;
        JLabel announcementTitle=new JLabel("Τίτλος Ανακοίνωσης:");
        annPanel.add(announcementTitle, gbc);
                
        gbc.gridx = 1;
        JTextField titleField = new JTextField(25);
        annPanel.add(titleField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel body = new JLabel("Σώμα Ανακοίνωσης:");
        annPanel.add(body, gbc);
        
        gbc.gridx = 1;
        JTextArea bodyArea = new JTextArea(6, 25);
        bodyArea.setLineWrap(true);
        bodyArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(bodyArea);
        annPanel.add(scrollPane, gbc);
        
        // Button row
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
       
        JButton cancel = new JButton("Ακύρωση");
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(e -> cardLayout.show(cardPanel, "seeProfileDetails"));
        
        /* cancel.addActionListener(e -> {
            // Clear fields when canceling
            titleField.setText("");
            bodyArea.setText("");
            cardLayout.show(cardPanel, "seeProfileDetails");
        });
        maybe
        */
        
        JButton publish = new JButton("Δημοσίευση");
        publish.setBackground(Color.GREEN);
        publish.addActionListener(e -> {
            String title = titleField.getText().trim();
            String bodyText = bodyArea.getText().trim();
            
            // Validate input
            if (title.isEmpty() || bodyText.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Παρακαλώ συμπληρώστε όλα τα πεδία.", 
                    "Σφάλμα", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Create and save the announcement for this department
            AnnouncementView newAnnouncement = new AnnouncementView(title, bodyText);
            AnnouncementRepository.addAnnouncement(departmentName, newAnnouncement);
            
            // Clear fields after successful save
            titleField.setText("");
            bodyArea.setText("");
            
            JOptionPane.showMessageDialog(this, 
                "Η ανακοίνωση προστέθηκε με επιτυχία στο " + departmentName + ".");
            cardLayout.show(cardPanel, "seeProfileDetails");
        });
        
        /*publish.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Η ανακοίνωση προστέθηκε με επιτυχία.");
            cardLayout.show(cardPanel, "seeProfileDetails");
        });*/
        
        btnPanel.add(cancel);
        btnPanel.add(publish);
        
        annPanel.add(btnPanel, gbc);
        
        add(annPanel, BorderLayout.CENTER);
        
        // BOTTOM BUTTONS
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton homeButton = new JButton("Αρχική Σελίδα");
        homeButton.setBackground(Color.decode("#B3FF66"));
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        
        JButton back= new JButton("Πίσω");
        back.setBackground(Color.decode("#FFCC66"));
        back.addActionListener(e-> cardLayout.show(cardPanel, "seeProfileDetails"));
        
        buttonPanel.add(homeButton);
        buttonPanel.add(back);
        
        add(buttonPanel, BorderLayout.SOUTH);
}
    public void writeAnnouncement(){
    }
    public void postAnnouncement() //arg departmentID : int
    {}
    public void pressCancelNewAnnouncement(){
    }
}
