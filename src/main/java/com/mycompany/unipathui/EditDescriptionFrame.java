package com.mycompany.unipathui;
import javax.swing.*;
import java.awt.*;

public class EditDescriptionFrame extends JFrame
{
    public EditDescriptionFrame(String uni_name, String department_name)
    {
        setTitle("Τροποποίηση Προφίλ Τμήματος "+department_name);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel title = new JLabel("UniPath", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(155,20,200,30);
        add(title);

        //θα κάνει ανάκτηση του ονόματος μέσω του login
        JLabel uni_nameLabel=new JLabel(uni_name, SwingConstants.CENTER);
        uni_nameLabel.setFont(new Font("Arial", Font.ITALIC,14));
        uni_nameLabel.setBounds(155,55,200,20);
        add(uni_nameLabel);

        JButton messagesButton = new JButton("Τα μηνύματά μου"); 
        ImageIcon envelopeIcon = new ImageIcon(getClass().getResource("/icons/envelope.png"));
        Image envelopeImage = envelopeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
        messagesButton.setIcon(new ImageIcon(envelopeImage));
        messagesButton.setBounds(330,20,160,30); 
        add(messagesButton); 
       
        JPanel editPanel= new JPanel(null);
        editPanel.setBounds(50,100,400,220);
        editPanel.setBackground(Color.LIGHT_GRAY);
        add(editPanel);
        
        JLabel departmentLabel= new JLabel(department_name, SwingConstants.CENTER);
        departmentLabel.setOpaque(true);
        departmentLabel.setBackground(Color.GREEN);
        departmentLabel.setFont(new Font("Arial", Font.BOLD,16));
        departmentLabel.setBounds(0,0,400,30);
        editPanel.add(departmentLabel);
            
        JLabel descriptionLabel= new JLabel("Περιγραφή Τμήματος:");
        descriptionLabel.setBounds(10,40,200,20);
        editPanel.add(descriptionLabel);
        
        JTextArea descriptionArea= new JTextArea("Περιγραφή...");
        descriptionArea.setBounds(10,65,380,110);
        descriptionArea.setEditable(true);
        editPanel.add(descriptionArea);
        //must connect with database...
        
        JScrollPane scrollPane=new JScrollPane(descriptionArea);
        scrollPane.setBounds(10,60,380,110);
        editPanel.add(scrollPane);
        //need to fix this
        
        JButton cancel=new JButton("Ακύρωση");
        cancel.setBounds(60,180,120,30);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(e->dispose()); //σβηνει το ποπ απ
        editPanel.add(cancel);
        
        JButton accept=new JButton("Αποδοχή Αλλαγών");
        accept.setBounds(200,180,150,30);
        accept.setBackground(Color.GREEN);
        accept.setForeground(Color.WHITE);
        //accept.addActionListener(e->...); will add logic here
        //αποδοχή συνδεση με db 
        editPanel.add(accept);
            
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
    }
}




