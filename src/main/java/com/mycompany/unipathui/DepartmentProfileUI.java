package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DepartmentProfileUI extends JPanel{
    public DepartmentProfileUI(String departmentName, Runnable onGoBack, 
        Runnable onBackToMenu, Runnable onViewAnnouncement){
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel(departmentName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        //Περιεχομενο κλασης
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.YELLOW);
        backButton.addActionListener(e -> onGoBack.run());
        
        JButton menuButton = new JButton("Επιστροφή στο μενού");
        menuButton.setBackground(Color.RED);
        menuButton.addActionListener(e -> onBackToMenu.run());
        
        JButton announcementButton = new JButton("Ανακοινώσεις");
        announcementButton.setBackground(Color.ORANGE);
        announcementButton.addActionListener(e -> onViewAnnouncement.run());
        
        bottomPanel.add(backButton);
        bottomPanel.add(menuButton);
        bottomPanel.add(announcementButton);
        
        add(bottomPanel, BorderLayout.SOUTH);
    }            
}
