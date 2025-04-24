package com.mycompany.unipathui;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @HelenaSiskou User
 */

public class DepartmentListFrame extends JFrame
{
    public DepartmentListFrame(String uni_name)
    {
        setTitle("Λίστα Τμημάτων - "+uni_name);
        setSize(500,400); // screen size 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        messagesButton.setBounds(330,20,160,30); // adjust width for icon
        add(messagesButton); 
       
        JLabel listTitle= new JLabel("Λίστα Τμημάτων Πανεπιστημίου", SwingConstants.CENTER);
        listTitle.setFont(new Font("Arial", Font.BOLD, 16));
        listTitle.setOpaque(true);
        listTitle.setBackground(Color.decode("#66A3FF"));
        listTitle.setBounds(110,75,300,25);
        add(listTitle);
        
        String[] departments={"Πληροφορικής","Μηχανικών Πληροφορικής","Ιατρικής","Οικονομικών","Πολιτικών Επιστημών","Εργοθεραπείας","Ηλεκτρολόγων Μηχανικών","Καλών Τεχνών"};
        //static for now
        //πως θα εισάγει κάθε πανεπιστήμιο τα τμήματά του? Import από αρχείο? στατικά/δυναμικα?
        
        
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(0,1,15,15));
        actionPanel.setBackground(Color.LIGHT_GRAY);        
        actionPanel.setPreferredSize(new Dimension(420, departments.length*60));
        
        for(String department : departments)
        {
            JPanel departmentPanel=new JPanel(new BorderLayout());
            departmentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
            
            JLabel departmentLabel= new JLabel(department, SwingConstants.CENTER);
            departmentLabel.setFont(new Font("Arial", Font.PLAIN,14));
            departmentPanel.add(departmentLabel,BorderLayout.CENTER);
            
            actionPanel.add(departmentPanel);
            
            JButton viewProfile= new JButton("Προβολή Προφίλ Τμήματος");
            viewProfile.setBackground(Color.CYAN);
            viewProfile.addActionListener(e->
            {
                new ProfileFrame(uni_name, department).setVisible(true);
                //JOptionPane.showMessageDialog(this, "Προφίλ τμήματος:"+department);
                // Will add more logic here soon
            });
            
            departmentPanel.add(viewProfile, BorderLayout.EAST);
        }
       
        //JList<String> departmentList= new JList<>(departments);
        JScrollPane scroll=new JScrollPane(actionPanel);
        scroll.setBounds(110,100,300,200);
        add(scroll);
        //actionPanel.add(scroll);
       
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
    }
}
