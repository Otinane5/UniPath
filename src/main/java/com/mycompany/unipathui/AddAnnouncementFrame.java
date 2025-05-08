package com.mycompany.unipathui;
import javax.swing.*;
import java.awt.*;

public class AddAnnouncementFrame extends JFrame {
    public AddAnnouncementFrame(String uni_name, String department_name) {
        setTitle("Προσθήκη Νέας Ανακοίνωσης Τμήματος "+department_name);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel title = new JLabel("UniPath", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(155,20,200,30);
        add(title);

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
        
        JPanel annPanel= new JPanel(null);
        annPanel.setBounds(50,100,400,220);
        annPanel.setBackground(Color.LIGHT_GRAY);
        add(annPanel);
        
        JLabel departmentLabel= new JLabel(department_name, SwingConstants.CENTER);
        departmentLabel.setOpaque(true);
        departmentLabel.setBackground(Color.GREEN);
        departmentLabel.setFont(new Font("Arial", Font.BOLD,16));
        departmentLabel.setBounds(0,0,400,30);
        annPanel.add(departmentLabel);
            
        JLabel new_announcement= new JLabel("Νέα Ανακοίνωση", SwingConstants.CENTER);
        new_announcement.setBounds(0,40,400,20);
        annPanel.add(new_announcement);

        JLabel announcement_title=new JLabel("Τίτλος Νέας Ανακοίνωσης:");
        announcement_title.setBounds(10,65,200,20);
        annPanel.add(announcement_title);
        
        JTextField titleField=new JTextField();
        titleField.setBounds(10,85,380,25);
        annPanel.add(titleField);
        //connect to DB
        
        JLabel body=new JLabel("Σώμα Νέας Ανακοίνωσης");
        body.setBounds(10,115,200,20);
        annPanel.add(body);
        
        JTextArea bodyArea= new JTextArea();
        bodyArea.setLineWrap(true);
        bodyArea.setWrapStyleWord(true);
        //connect with DB
        
        JScrollPane scroll=new JScrollPane(bodyArea);
        scroll.setBounds(10,140,380,70);
        annPanel.add(scroll);
        //ορατό όταν το σώμα της ανακοίνωσης υπερβαινει τα όρια του text area
       
        JButton cancel=new JButton("Ακύρωση");
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(60,180,120,30);
        annPanel.add(cancel);
        
        
        JButton publish=new JButton("Δημοσίευση");
        publish.setBackground(Color.GREEN);
        publish.setBounds(50,300,120,30);
        add(publish);
        
        //annPanel.add(publish);
        // add logic for saving the new announcement to the DB
        
        
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
        homeButton.addActionListener(e -> {
            new MainMenu().setVisible(true); 
            dispose(); 
        });
        logout.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Είστε σίγουρος ότι θέλετε να αποσυνδεθείτε;",
                    "Επιβεβαίωση Αποσύνδεσης",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                dispose(); // Κλείσιμο αυτού του frame
                new LoginFrame().setVisible(true); // Άνοιγμα login από την αρχή
            }
        });
        JButton back= new JButton("Πίσω");
        back.setBackground(Color.decode("#FFCC66"));
        back.setBounds(330,330,150,30);
        back.addActionListener(e-> dispose());
        add(back);
       
    } 
}
