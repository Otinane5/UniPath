package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AnnouncementUI extends JPanel{
    public AnnouncementUI(String departmentName, List<AnnouncementView> announcements, 
        Runnable onGoBack, Runnable onBackToMenu){
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Ανακοινώσεις - " + departmentName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        
        //Panel λίστας ανακοινώσεων
        DefaultListModel<AnnouncementView> listModel = new DefaultListModel<>();
        for (AnnouncementView announcement : announcements){
            listModel.addElement(announcement);
        }    
        JList<AnnouncementView> announcementList = new JList<>(listModel);
        announcementList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(announcementList);
        
        //Περιοχή σώματος επιλεγμένης ανακοίνωσης
        JTextArea bodyArea = new JTextArea();
        bodyArea.setLineWrap(true);
        bodyArea.setWrapStyleWord(true);
        bodyArea.setEditable(false);
        bodyArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane bodyScrollPane = new JScrollPane(bodyArea);
        bodyScrollPane.setPreferredSize(new Dimension(300, 200));
             
        //Διαχωρισμός των Panels
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, bodyScrollPane);
        splitPane.setResizeWeight(0.8); //60% της οθόνης λίστα και 40% σώμα 
        add(splitPane, BorderLayout.CENTER);
        
        JButton viewButton = new JButton("Προβολή Ανακοίνωσης");
        viewButton.setBackground(Color.CYAN);
        viewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
             
        viewButton.addActionListener(e ->{
            AnnouncementView selected = announcementList.getSelectedValue();
            if(selected != null){
                bodyArea.setText(selected.body);
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
        
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.YELLOW);
        backButton.addActionListener(e -> onGoBack.run());
        
        JButton menuButton = new JButton("Επιστροφή στο μενού");
        menuButton.setBackground(Color.ORANGE);
        menuButton.addActionListener(e -> onBackToMenu.run());
           
        navPanel.add(backButton);
        navPanel.add(menuButton);
        
        JPanel bottomBox = new JPanel();
        bottomBox.setLayout(new BoxLayout(bottomBox, BoxLayout.Y_AXIS));
        bottomBox.add(viewButton);
        bottomBox.add(Box.createVerticalStrut(10));
        bottomBox.add(navPanel);
        add(bottomBox, BorderLayout.SOUTH);
    }
}
