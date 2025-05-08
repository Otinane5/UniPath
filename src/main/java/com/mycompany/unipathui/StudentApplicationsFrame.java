//package com.mycompany.testing;

/**
 *
 * @HelenaSiskou User
 */
package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class StudentApplicationsFrame extends JFrame
{
    public StudentApplicationsFrame()
    {
        setTitle("Αιτήσεις Εγγραφής");
        setSize(500,400);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel title = new JLabel("UniPath", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(155,20,200,30);
        add(title);
        
        //ανάκτηση του ονόματος μέσω του login
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
        
        JLabel sectionLabel= new JLabel("Αιτήσεις Εγγραφής"); //ή Αποδοχή Απόρριψη Αιτήσεων
        sectionLabel.setFont(new Font("Arial",Font.BOLD,16));
        sectionLabel.setBounds(190,90,200,20);
        add(sectionLabel);
        
        JPanel applicationListPanel=new JPanel();
        applicationListPanel.setLayout(new BoxLayout(applicationListPanel, BoxLayout.Y_AXIS));
        applicationListPanel.setBackground(Color.WHITE);
        //scroll
        
        int application_num=10; //static for now. then we will make it dynamic
        for(int i=1; i<=application_num; i++)
        {
            JPanel singlePanel= new JPanel(null);
            singlePanel.setPreferredSize(new Dimension(700,110));
            singlePanel.setMaximumSize(new Dimension(700,110));
            singlePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            singlePanel.setBackground(Color.WHITE);
            
            JLabel applicationLabel=new JLabel("Αίτηση "+i); //kwdikos aithshs?
            //alliws etsi opws einai mporei na erthoun ki alles
            //ara to application_num synexws ayxanetai
            //++ σύνολο υπολειπόμενων αιτήσεων
            applicationLabel.setFont(new Font("Arial", Font.BOLD,14));
            applicationLabel.setBounds(10,5,300,20);
            singlePanel.add(applicationLabel);
            
            //static (for now!)
            String name="Ονοματεπώνυμο_"+i;
            String department="Τμήμα_"+i;
            int grades= 15000+3*i; //den tha ypologizetai profanws alla tha eisagetai apo ton foithth
            
            //might change the variable names to fit with the rest of the use cases-code
            JLabel nameLabel=new JLabel("Ονοματεπώνυμο: "+name);
            nameLabel.setFont(new Font("Arial", Font.PLAIN,13));
            nameLabel.setBounds(10,30,300,20);
            singlePanel.add(nameLabel);
            
            JLabel departmentLabel=new JLabel("Τμήμα: "+department);
            departmentLabel.setFont(new Font("Arial", Font.PLAIN,13));
            departmentLabel.setBounds(10,45,300,20);
            singlePanel.add(departmentLabel);
            
            JLabel gradesLabel=new JLabel("Μόρια: "+grades);
            gradesLabel.setFont(new Font("Arial", Font.PLAIN,13));
            gradesLabel.setBounds(10,60,300,20);
            singlePanel.add(gradesLabel);
            
            JButton accept= new JButton("Αποδοχή");
            accept.setBackground(Color.decode("#00FF00"));
            accept.setForeground(Color.BLACK);
            accept.setFocusPainted(false);
            accept.setBounds(230,30,100,25);
            singlePanel.add(accept);
            //add(accept);
            //όταν υλοποιηθει το message box: connect για να στελνει μηνυμα αποδοχης
            
            JButton reject= new JButton("Απόρριψη");
            reject.setBackground(Color.decode("#FF0000"));
            reject.setForeground(Color.WHITE);
            reject.setFocusPainted(false);
            reject.setBounds(230,65,100,25);
            singlePanel.add(reject);
            //add(reject);
            //όταν υλοποιηθει το message box: connect για να στελνει μηνυμα απόρριψης

            
            applicationListPanel.add(singlePanel);
            applicationListPanel.add(Box.createVerticalStrut(10));
            
        }
        
        JScrollPane scroll=new JScrollPane(applicationListPanel);
        scroll.setBounds(50,120,390,200);
        add(scroll);
        
        JButton logout=new JButton("Αποσύνδεση");
        logout.setBounds(10,330,150,30);
        logout.setBackground(Color.decode("#FF6666"));
        logout.setForeground(Color.BLACK);
        add(logout);
        //fix
        
        JButton homeButton = new JButton("Αρχική Σελίδα");
        homeButton.setBackground(Color.decode("#B3FF66"));
        homeButton.setBounds(170,330,150,30);
        add(homeButton);
        homeButton.addActionListener(e -> 
            {
                new MainMenu().setVisible(true); 
                dispose(); 
            });

        JButton back= new JButton("Πίσω");
        back.setBackground(Color.decode("#FFCC66"));
        back.setBounds(330,330,150,30);
        back.addActionListener(e-> dispose());
        add(back);
        
        //logout.addActionListener(e -> System.exit(0));
        //back.addActionListener(e -> dispose());
    } 
    
   
    //public static void main(String[] args) {
    //    SwingUtilities.invokeLater(() -> new StudentApplicationsFrame().setVisible(true));
    //}
}

       