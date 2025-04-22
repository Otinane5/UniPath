package com.mycompany.unipathui;
import javax.swing.*;
import java.awt.*;


public class ProfileFrame extends JFrame
{
    public ProfileFrame(String uni_name, String department_name)
    {
            setTitle("Προφίλ Τμήματος " + department_name);
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
       
            JPanel profilePanel= new JPanel(null);
            profilePanel.setBounds(50,100,400,220);
            profilePanel.setBackground(Color.LIGHT_GRAY);
            add(profilePanel);
            
            JLabel departmentLabel= new JLabel(department_name, SwingConstants.CENTER);
            departmentLabel.setOpaque(true);
            departmentLabel.setBackground(Color.GREEN);
            departmentLabel.setFont(new Font("Arial", Font.BOLD,16));
            departmentLabel.setBounds(0,0,400,30);
            profilePanel.add(departmentLabel);
         
            JLabel descriptionLabel= new JLabel("Περιγραφή Τμήματος");
            descriptionLabel.setBounds(10,40,200,20);
            profilePanel.add(descriptionLabel);
            
            JButton edit= new JButton("Τροποποίηση");
            edit.setBounds(150,30,120,35);
            profilePanel.add(edit);
            
            JTextArea descriptionArea= new JTextArea("Περιγραφή...");
            //μπορεί να είναι αρχικά περιγραφή... και αφού προστεθεί 
            //η νέα περιγραφή απο το πανεπιστήμιο, αποθηκεύεται και πλέον υπάρχει η νέα περιγραφή
            //+ μπορεί και εδώ να χρειαστεί νέο scroll bar (μπορει η περιγραφή να ειναι μεγάλη
            descriptionArea.setBounds(10,65,380,50);
            descriptionArea.setEditable(true);
            profilePanel.add(descriptionArea);
            
            JLabel announcements= new JLabel("Ανακοινώσεις");
            announcements.setBounds(10,125,200,20);
            profilePanel.add(announcements);
            
            JButton add_announcement = new JButton("Προσθήκη");
            add_announcement.setBounds(150,115,120,35);
            profilePanel.add(add_announcement);
            
            JPanel announcementPanel=new JPanel();
            announcementPanel.setLayout(new BoxLayout(announcementPanel, BoxLayout.Y_AXIS));
            announcementPanel.setBackground(Color.WHITE);
            
            for(int i=1; i<=8; i++) //static for now
            {
                JLabel announcementLabel= new JLabel("~ Ανακοίνωση_"+i);
                announcementLabel.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
                announcementPanel.add(announcementLabel);
            }
            
            JScrollPane scroll=new JScrollPane(announcementPanel);
            scroll.setBounds(10,155,380,50);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            profilePanel.add(scroll);
            
            JButton logout=new JButton("Αποσύνδεση");
            logout.setBounds(10,330,150,30);
            logout.setBackground(Color.decode("#FF6666"));
            logout.setForeground(Color.BLACK);
            add(logout);

            JButton homeButton = new JButton("Αρχική Σελίδα");
            homeButton.setBackground(Color.decode("#B3FF66"));
            homeButton.setBounds(170,330,150,30);
            add(homeButton);

            JButton back= new JButton("Πίσω");
            back.setBackground(Color.decode("#FFCC66"));
            back.setBounds(330,330,150,30);
            back.addActionListener(e-> dispose());
            add(back);
            
            edit.addActionListener(e->
            {
               new EditDescriptionFrame(uni_name,department_name).setVisible(true);
  
            });
            profilePanel.add(edit);
            
            add_announcement.addActionListener(e->
            {
               new AddAnnouncementFrame(uni_name,department_name).setVisible(true);
  
            });
            profilePanel.add(add_announcement);
            
            
    }
}

        