package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;


import java.util.ArrayList;
import java.util.List;


public class ProfilePanel extends JPanel {
    private JPanel announcementPanel;
    private JTextArea descriptionArea;
    
    private JScrollPane scroll;
    public static String currentDescription = "Περιγραφή...";
    
    
    private JPanel contentPanel;
    private JPanel descPanel;
    public static String currentDepartment = "Τμήμα ...";
private JLabel departmentLabel;
    
    public static java.util.List<String[]> announcements = new java.util.ArrayList<>();

    public ProfilePanel(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout(10, 10));
        
        // Title label
        JLabel titleLabel = new JLabel("Προφίλ Τμήματος", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        // Main content panel
        //JPanel 
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // padding
        
        // Department
        //JLabel departmentLabel= new JLabel("Τμήμα ...", SwingConstants.CENTER); //+department name
                departmentLabel = new JLabel(currentDepartment, SwingConstants.CENTER);

        departmentLabel.setOpaque(true);
        departmentLabel.setBackground(Color.GREEN);
        departmentLabel.setFont(new Font("Arial", Font.BOLD,16));
        departmentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        departmentLabel.setMaximumSize(new Dimension(400, 30));
        departmentLabel.setBounds(0,0,400,30);
        contentPanel.add(departmentLabel);
        contentPanel.add(Box.createVerticalStrut(10));
        
        // Description and Edit
        //JPanel 
        descPanel = new JPanel(new BorderLayout(10, 0));
        descPanel.setMaximumSize(new Dimension(400, 70));
        descPanel.add(new JLabel("Περιγραφή Τμήματος"), BorderLayout.WEST);
         editDescription(cardLayout, cardPanel);
        contentPanel.add(descPanel);

         
        /*JButton edit = new JButton("Τροποποίηση");
        edit.addActionListener(e -> cardLayout.show(cardPanel, "editUniDesc"));
        descPanel.add(edit, BorderLayout.EAST);
        contentPanel.add(descPanel);*/
        
        //Department's description
        descriptionArea = new JTextArea(currentDescription);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        descScroll.setPreferredSize(new Dimension(380, 60));
        descScroll.setMaximumSize(new Dimension(400, 60));
        contentPanel.add(descScroll);
        contentPanel.add(Box.createVerticalStrut(10));
        
        // Announcements and Adding Announcements
        JPanel annPanel = new JPanel(new BorderLayout(10, 0));
        annPanel.setMaximumSize(new Dimension(400, 35));
        annPanel.add(new JLabel("Ανακοινώσεις"), BorderLayout.WEST);
        JButton addAnnouncement = new JButton("Προσθήκη");
        addAnnouncement.addActionListener(e -> cardLayout.show(cardPanel, "addAnnouncement"));
        annPanel.add(addAnnouncement, BorderLayout.EAST);
        contentPanel.add(annPanel);
        
        

        //Announcement List
        //JPanel announcementPanel = new JPanel();
        announcementPanel=new JPanel();
        announcementPanel.setLayout(new BoxLayout(announcementPanel, BoxLayout.Y_AXIS));
        announcementPanel.setBackground(Color.WHITE);      
        
        refreshAnnouncements();

        //scrolling enabled
        JScrollPane scroll = new JScrollPane(announcementPanel);
        scroll.setPreferredSize(new Dimension(380, 100));
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(scroll);
        
        add(contentPanel, BorderLayout.CENTER);
        
        // BOTTOM BUTTONS
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton homeButton = new JButton("Αρχική Σελίδα");
        homeButton.setBackground(Color.decode("#B3FF66"));
        homeButton.setBounds(170,330,150,30);
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        
        JButton back= new JButton("Πίσω");
        back.setBackground(Color.decode("#FFCC66"));
        back.setBounds(330,330,150,30);
        back.addActionListener(e -> cardLayout.show(cardPanel, "seeListOfDepartments"));
        
        buttonPanel.add(homeButton);
        buttonPanel.add(back);
        add(buttonPanel, BorderLayout.SOUTH);
        
        //descriptionArea.setText(currentDescription);

    }
    
    //public void openDepartmentProfile() //departmentID:int
    //{} to profil έχει ήδη ανοίξει. ίσως παει σαν μεθοδοσ στο προηγούμενο frame
    
    /* ϊσως να μεταφερθουν στο προηγούμενο panel
    public void getDepartmentProfile() //departmentID:int
    {}
    public void getDescription() //departmentID: int
    {}
    public void getAnnouncements() //departmentID:int
    {}
    public void displayProfile()
    {}
    public void displayFullProfile()
    {}*/
    
    public void editDescription(CardLayout cardLayout, JPanel cardPanel)
    {
        JButton edit = new JButton("Τροποποίηση");
        edit.addActionListener(e -> cardLayout.show(cardPanel, "editUniDesc"));
        descPanel.add(edit, BorderLayout.EAST);
        //contentPanel.add(descPanel);
    }
    public void addAnnouncement()
    {}
    public void publishAnnouncement()
    {}
    //public void pressBack()
    //{}
    
    //new method
    public void updateDescription(String newDesc) 
    {
        currentDescription = newDesc;
        descriptionArea.setText(newDesc);
    }
    
    //new method
    public void refreshAnnouncements() 
    {
        announcementPanel.removeAll();

        for (String[] ann : announcements) 
        {
            JLabel annLabel = new JLabel(" 📢 " + ann[0] + ": " + ann[1]);
            annLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            announcementPanel.add(annLabel);
        }

        announcementPanel.revalidate();
        announcementPanel.repaint();
    }
    

    public void refreshProfile() {
        departmentLabel.setText(currentDepartment);
        descriptionArea.setText(currentDescription);
        refreshAnnouncements();
    }

}
