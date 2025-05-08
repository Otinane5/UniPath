package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Main Menu (Πανεπιστήμιο)");
        setSize(500,400); // screen size 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel title = new JLabel("UniPath", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(155,20,200,30);
        add(title);
        
        //θα κάνει ανάκτηση του ονόματος μέσω του login
        JLabel uni_name=new JLabel("<Όνομα πανεπιστημίου>", SwingConstants.CENTER);
        uni_name.setFont(new Font("Arial", Font.ITALIC,14));
        uni_name.setBounds(155,55,200,20);
        add(uni_name);
        
        JButton messagesButton = new JButton("Τα μηνύματά μου"); 
        ImageIcon envelopeIcon = new ImageIcon(getClass().getResource("/icons/envelope.png"));
        
        Image envelopeImage = envelopeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
        messagesButton.setIcon(new ImageIcon(envelopeImage));
        messagesButton.setBounds(330,20,160,30); // adjust width for icon
        add(messagesButton); 
       
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(null);
        actionPanel.setBackground(Color.LIGHT_GRAY);
        actionPanel.setBounds(110,100,300,150);
        add(actionPanel);
        
        JLabel actionLabel= new JLabel("Επιλογή Ενέργειας:");
        actionLabel.setFont(new Font("Arial", Font.BOLD,14));
        actionLabel.setBounds(80,10,200,20);
        actionPanel.add(actionLabel);
        
        JButton viewApplicationsButton= new JButton("Προβολή Αιτήσεων Μαθητών");
        viewApplicationsButton.setBounds(50,40,200,30);
        viewApplicationsButton.setBackground(Color.decode("#B3FF66"));
        actionPanel.add(viewApplicationsButton);
        
        viewApplicationsButton.addActionListener(e-> new StudentApplicationsFrame().setVisible(true));
        //dispose
        
        JButton viewDepartmentsButton= new JButton("Προβολή Λίστας Τμημάτων Πανεπιστημίου");
        viewDepartmentsButton.setBounds(10,80,280,30);
        viewDepartmentsButton.setBackground(Color.decode("#99EBFF"));
        actionPanel.add(viewDepartmentsButton);
        
        //String uni_name="Upatras"; //static
        //viewDepartmentsButton.addActionListener(e-> new DepartmentListFrame().setVisible(true));
        
        viewDepartmentsButton.addActionListener(e -> 
        {
        String universityName = uni_name.getText(); 
        new DepartmentListFrame(universityName).setVisible(true);
        });

       
        JButton logoutButton = new JButton("Αποσύνδεση");
        logoutButton.setBounds(185,280,150,30);
        logoutButton.setBackground(Color.decode("#FF6666"));
        add(logoutButton);
        //fix it
        
        //logoutButton.addActionListener(e -> System.exit(0));
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater( ()->new MainMenu().setVisible(true));
//    }
}

//CHANGE VAR NAMES!!
//fullscreen
// Να κλείνει η παλιά οθόνη
