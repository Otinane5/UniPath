package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AnnouncementUI extends JPanel{
    public AnnouncementUI(String departmentName, List<String> announcements, 
        Runnable onGoBack, Runnable onBackToMenu){
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Ανακοινώσεις - " + departmentName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String announcement : announcements){
            listModel.addElement(announcement);
            
        JList<String> announcementList = new JList<>(listModel);
        announcementList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(announcementList);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel centerButtons = new JPanel();
        centerButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton viewButton = new JButton("Προβολή Ανακοίνωσης");
        viewButton.setBackground(Color.LIGHT_GRAY);
        centerButtons.add(viewButton);
        
        add(centerButtons, BorderLayout.EAST);
        
        viewButton.addActionListener(e ->{
            String selected = announcementList.getSelectedValue();
            if(selected != null){
                JOptionPane.showMessageDialog(
                   this,
                   selected,
                   "Ανακοίνωση",
                   JOptionPane.INFORMATION_MESSAGE
                 );
            }
            else{
                JOptionPane.showMessageDialog(
                    this,
                    "Παρακαλώ επιλέξτε μία ανακοίνωση",
                    "Καμία επιλογή",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        });     
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.YELLOW);
        backButton.addActionListener(e -> onGoBack.run());
        
        JButton menuButton = new JButton("Επιστροφή στο μενού");
        menuButton.setBackground(Color.ORANGE);
        menuButton.addActionListener(e -> onBackToMenu.run());
           
        bottomPanel.add(backButton);
        bottomPanel.add(menuButton);
        
        add(bottomPanel, BorderLayout.SOUTH);
        }
    }
}
