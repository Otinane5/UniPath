/**
 *
 * @HelenaSiskou User
 */
package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class StudentApplicationsFrame extends JFrame
{
    public StudentApplicationsFrame()
    {
        setTitle("Αιτήσεις Εγγραφής");
        setSize(500,400);
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
        
        JLabel sectionLabel= new JLabel("Αιτήσεις Εγγραφής");
        sectionLabel.setFont(new Font("Arial",Font.BOLD,16));
        sectionLabel.setBounds(160,90,200,20);
        add(sectionLabel);
        
        JPanel applicationListPanel=new JPanel();
        applicationListPanel.setLayout(new BoxLayout(applicationListPanel, BoxLayout.Y_AXIS));
        //scroll
        
        int application_num=15; //static for now. then we will make it dynamic
        for(int i=1; i<=application_num; i++)
        {
            JLabel applicationLabel=new JLabel("Αίτηση "+i); //kwdikos aithshs?
            //alliws etsi opws einai mporei na erthoun ki alles
            //ara to application_num synexws ayxanetai
            applicationLabel.setFont(new Font("Arial", Font.PLAIN,14));
            applicationLabel.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
            applicationListPanel.add(applicationLabel);
        }
        
        JScrollPane scroll=new JScrollPane(applicationListPanel);
        scroll.setBounds(50,120,390,200);
        add(scroll);
        
    } 
    
    //public static void main(String[] args) {
    //    SwingUtilities.invokeLater(() -> new StudentApplicationsFrame().setVisible(true));
    //}
}
