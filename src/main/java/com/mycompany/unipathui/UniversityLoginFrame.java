package com.mycompany.unipathui;
import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;

public class UniversityLoginFrame extends JFrame {
    public UniversityLoginFrame() {
        setTitle("University Login Menu");
        setSize(500,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel title = new JLabel("UniPath", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(155,20,200,30);
        add(title);
        
        JLabel welcome=new JLabel("Καλώς Ορίσατε!", SwingConstants.CENTER);
        welcome.setFont(new Font("Arial", Font.PLAIN,18));
        welcome.setBounds(150,60,200,25);
        add(welcome);
        
        JLabel login_as = new JLabel("Σύνδεση ως Πανεπιστήμιο", SwingConstants.CENTER);
        login_as.setFont(new Font("Arial", Font.ITALIC,14));
        login_as.setBounds(150,80,200,20);
        add(login_as);
        
        JPanel loginPanel=new JPanel(null);
        loginPanel.setBounds(100,100,300,150);
        loginPanel.setBackground(Color.LIGHT_GRAY);
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        add(loginPanel);
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20,20,80,25);
        loginPanel.add(usernameLabel);
        
        JTextField usernameField= new JTextField();
        usernameField.setBounds(100,20,160,25);
        loginPanel.add(usernameField);
        
        JLabel passwordLabel= new JLabel("Password:");
        passwordLabel.setBounds(20,60,80,25);
        loginPanel.add(passwordLabel);
        
        JPasswordField passwordField= new JPasswordField();
        passwordField.setBounds(100,60,160,25);
        loginPanel.add(passwordField);
       
        JButton login= new JButton("Σύνδεση");
        login.setBounds(160,100,100,25);
        login.setBackground(Color.CYAN);
        loginPanel.add(login);
        
        JButton cancel=new JButton("Ακύρωση");
        cancel.setBounds(30,100,100,25);
        cancel.setBackground(Color.PINK);
        loginPanel.add(cancel);
        
        JButton exit=new JButton("Έξοδος από το σύστημα");
        exit.setBounds(150,290,200,30);
        exit.setBackground(Color.RED);
        add(exit);
        
        login.addActionListener(e-> {
            String username = usernameField.getText();
            String password= new String(passwordField.getPassword());
            
            // static password=1111
            // στην πορεία θα γινει ΔΥΝΑΜΙΚΟ
            if(username.equals("admin") && password.equals("1111")) {
                new MainMenu().setVisible(true);
                dispose(); //kleinei to login parathiro
            }
            else {
                JOptionPane.showMessageDialog(this,"Έχετε εισάγει λανθασμένα στοιχεία σύνδεσης.\n     Προσπαθήστε ξανά πατώντας το ΟΚ");
            }
        });
        exit.addActionListener(e -> System.exit(0));
}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UniversityLoginFrame().setVisible(true));
    }
}


//syndesh me Database-εξακρίβωση στοιχείων
//αν δεν υπάρχει ο χρήστης, δημιουργειται, και την επόμενη φορά ελέγχεται αν: 
//1)υπάρχει αυτός ο χρήστης και 2) ελέγχει αν κανουν Match το username με τον κωδικό