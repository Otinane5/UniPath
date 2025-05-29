package com.mycompany.unipathui;

import com.mycompany.baseClasses.Description;

import javax.swing.*;
import java.awt.*;

public class EditDescriptionPanel extends JPanel {

    private JTextArea descriptionArea;
    private String originalDescription;
    private JLabel seeProfileLabel;
    private String departmentName;
    private JLabel departmentLabel;

    public EditDescriptionPanel(CardLayout cardLayout, JPanel cardPanel,String departmentName) 
    {
        setLayout(new BorderLayout(10, 10));
        this.departmentName = departmentName;

        // Title label
        JLabel titleLabel = new JLabel("Τροποποίηση Προφίλ Τμήματος", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        // Main edit panel
        JPanel editPanel= new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        editPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // padding
        
        departmentLabel = new JLabel(departmentName != null ? departmentName : "Τμήμα...", SwingConstants.CENTER);
        departmentLabel.setOpaque(true);
        departmentLabel.setBackground(Color.GREEN);
        departmentLabel.setFont(new Font("Arial", Font.BOLD,16));
        departmentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        editPanel.add(departmentLabel);
        editPanel.add(Box.createVerticalStrut(10));
        
        JLabel descriptionLabel= new JLabel("Περιγραφή Τμήματος:", SwingConstants.CENTER);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        editPanel.add(descriptionLabel);
        
        if (departmentName != null) 
        {
            originalDescription = Description.getDepartmentDescription(departmentName);
        } 
        else 
        {
            originalDescription = "Περιγραφή...";
        }

        descriptionArea = new JTextArea(originalDescription);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(true);

        JScrollPane scrollPane=new JScrollPane(descriptionArea);
        scrollPane.setPreferredSize(new Dimension(380, 100));
        editPanel.add(scrollPane);
        editPanel.add(Box.createVerticalStrut(10));
        
        JButton cancel = pressCancelEdit(cardLayout, cardPanel);       
        JButton accept = acceptChanges(cardLayout, cardPanel);
        
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
    public JButton acceptChanges(CardLayout cardLayout, JPanel cardPanel){
        
        JButton accept=new JButton("Αποδοχή Αλλαγών");
        accept.setBackground(Color.GREEN);
        accept.setForeground(Color.WHITE);
        
        accept.addActionListener(e -> 
        {
            String newDescription=descriptionArea.getText().trim();
            
            JTextArea preview = new JTextArea("Από:\n" + originalDescription + "\n\n➔ Σε:\n" + newDescription);
            preview.setEditable(false);
            preview.setLineWrap(true);
            preview.setWrapStyleWord(true);

            int result = JOptionPane.showConfirmDialog(this, new JScrollPane(preview), "Προεπισκόπηση Αλλαγών", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) 
            {
                originalDescription = newDescription;
                Description.setDepartmentDescription(departmentName, newDescription);
                
                Component profileComp = null;
                for (Component comp : cardPanel.getComponents()) 
                {
                    if (comp instanceof ProfilePanel) 
                    {
                        //profileComp = comp;
                        ((ProfilePanel) comp).updateDescription(newDescription);

                        break;
                    }
                }

                JOptionPane.showMessageDialog(this, "Οι αλλαγές αποθηκεύτηκαν με επιτυχία.");
                cardLayout.show(cardPanel, "seeProfileDetails");    
            } 
        });
        return accept;
    }
    
    public void setDepartmentName(String departmentName) 
    {
        this.departmentName = departmentName;

        // ενημερώνεται το Label Με το νεο όνομα τμηματος
        if (departmentLabel != null) 
        {
            departmentLabel.setText(departmentName);
            departmentLabel.repaint(); 
        }

        // Ενημέρωση της περιγραφής από το Description repository (Description.java)
        originalDescription = Description.getDepartmentDescription(departmentName);
        if (descriptionArea != null) 
        {
            descriptionArea.setText(originalDescription);
        }
    }
    
    public JButton pressCancelEdit(CardLayout cardLayout, JPanel cardPanel)
    {
        
        JButton cancel=new JButton("Ακύρωση");
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
                
        cancel.addActionListener(e -> 
        {
            int confirm =JOptionPane.showConfirmDialog(this, "Με αυτή την επιλογή οι αλλαγές θα χαθούν!", "Ακύρωση", JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_OPTION)
            {
                cardLayout.show(cardPanel, "seeProfileDetails"); 
            }
        });
        return cancel;
    }
}
