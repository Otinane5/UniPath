package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class EditDescriptionPanel extends JPanel {

    private JTextArea descriptionArea;
    private String originalDescription= "Περιγραφή...";
private JLabel seeProfileLabel;

    public EditDescriptionPanel(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout(10, 10));
        
        // Title label
        JLabel titleLabel = new JLabel("Τροποποίηση Προφίλ Τμήματος", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        // Main edit panel
        JPanel editPanel= new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        editPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // padding
        
        // Department
        JLabel departmentLabel= new JLabel("Όνομα Τμήματος", SwingConstants.CENTER);
        departmentLabel.setOpaque(true);
        departmentLabel.setBackground(Color.GREEN);
        departmentLabel.setFont(new Font("Arial", Font.BOLD,16));
        departmentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        editPanel.add(departmentLabel);
        editPanel.add(Box.createVerticalStrut(10));
        
        JLabel descriptionLabel= new JLabel("Περιγραφή Τμήματος:", SwingConstants.CENTER);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        editPanel.add(descriptionLabel);
        
        //JTextArea descriptionArea= new JTextArea("Περιγραφή...");
        descriptionArea = new JTextArea(originalDescription);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(true);

        JScrollPane scrollPane=new JScrollPane(descriptionArea);
        scrollPane.setPreferredSize(new Dimension(380, 100));
        editPanel.add(scrollPane);
        editPanel.add(Box.createVerticalStrut(10));
        
        JButton cancel=new JButton("Ακύρωση");
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        
        
        
        cancel.addActionListener(e -> {
            int confirm =JOptionPane.showConfirmDialog(this, "Με αυτή την επιλογή οι αλλαγές θα χαθούν!", "Ακύρωση", JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_OPTION){cardLayout.show(cardPanel, "seeProfileDetails"); }});
        
        JButton accept=new JButton("Αποδοχή Αλλαγών");
        accept.setBackground(Color.GREEN);
        accept.setForeground(Color.WHITE);
        
        
        accept.addActionListener(e -> {
            String newDescription=descriptionArea.getText().trim();
            
            JTextArea preview = new JTextArea("Από:\n" + originalDescription + "\n\n➔ Σε:\n" + newDescription);
            preview.setEditable(false);
            preview.setLineWrap(true);
            preview.setWrapStyleWord(true);

            int result = JOptionPane.showConfirmDialog(this, new JScrollPane(preview), "Προεπισκόπηση Αλλαγών", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                originalDescription = newDescription;
                updatedDescription(newDescription);

                JOptionPane.showMessageDialog(this, "Οι αλλαγές αποθηκεύτηκαν με επιτυχία.");
                cardLayout.show(cardPanel, "seeProfileDetails");
                
            }
           
        });
        
        
        /*accept.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Οι αλλαγές αποθηκεύτηκαν με επιτυχία.");
            cardLayout.show(cardPanel, "seeProfileDetails");
        });*/
        
        JPanel confirmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        confirmPanel.add(cancel);
        confirmPanel.add(accept);
        editPanel.add(confirmPanel);

        // BOTTOM BUTTONS
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton homeButton = new JButton("Αρχική Σελίδα");
        homeButton.setBackground(Color.decode("#B3FF66"));
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        
        JButton back= new JButton("Πίσω");
        back.setBackground(Color.decode("#FFCC66"));
        back.addActionListener(e -> cardLayout.show(cardPanel, "seeProfileDetails"));
        
        buttonPanel.add(homeButton);
        buttonPanel.add(back);
        
        add(editPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    
    public void openEditDescriptionWindow(){
    }
    public void acceptChanges(){
    }
    public void pressCancelEdit(){
    }
    
    
    public void setSeeProfileDescriptionComponent(JLabel label) {
        this.seeProfileLabel = label;
    }

    public void updatedDescription(String newDescription) {
        if (seeProfileLabel != null) {
            seeProfileLabel.setText("<html><body style='width: 300px'>" + newDescription + "</body></html>");
        }
    }
}
