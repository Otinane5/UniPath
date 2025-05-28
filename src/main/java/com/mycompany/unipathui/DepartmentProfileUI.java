package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.List;
import com.mycompany.baseClasses.Description; 

public class DepartmentProfileUI extends JPanel
{      
    public DepartmentProfileUI(String departmentName, Runnable onGoBack, 
        Runnable onBackToMenu, BiConsumer<String, List<AnnouncementView>> onViewAnnouncement){
       
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel(departmentName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        //Η περιγραφή του τμήματος και το κουμπί λίστας ανακοινώσεων θα είναι σε κάθετο BoxLayout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        
        String description = Description.getDepartmentDescription(departmentName);

        JTextArea infoArea = new JTextArea(description);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        infoArea.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        
        JButton announcementButton = new JButton("Ανακοινώσεις");
        announcementButton.setBackground(Color.CYAN);
        announcementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        announcementButton.addActionListener(e -> 
        {
            List<AnnouncementView> departmentAnnouncements = AnnouncementRepository.getAnnouncements(departmentName);
            onViewAnnouncement.accept(departmentName, departmentAnnouncements);
        });
        
        centerPanel.add(scrollPane);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(announcementButton);
        
        add(centerPanel, BorderLayout.CENTER);
              
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
