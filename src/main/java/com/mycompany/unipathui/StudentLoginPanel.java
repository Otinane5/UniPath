package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

//Πάνελ Σύνδεσης Μαθητή: Εμφανίζει πλαίσια για όνομα χρή-
//στη και κωδικό, καθώς και τα κουμπιά "Σύνδεση" και "Πίσω".
public class StudentLoginPanel extends JPanel {
    //ATTRIBUTES
    private JTextField usernameField;
    private JPasswordField passwordField;
    //CONSTRUCTOR
    public StudentLoginPanel(LoginFrame parentFrame) {
        setLayout(new BorderLayout());

        //Επικεφαλίδα
        JLabel subtitle  = new JLabel("Σύνδεση Μαθητή", SwingConstants.CENTER);
        subtitle.setFont(new Font("Arial", Font.BOLD, 18));
        subtitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(subtitle, BorderLayout.NORTH);

        //Κέντρο φόρμα login
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        //Πεδίο όνομα χρήστη
        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(200, 35));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Πεδίο κωδικού
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(200, 35));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Κουμπιά
        JButton loginButton = new JButton("Σύνδεση");
        loginButton.setBackground(Color.CYAN);
        
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.decode("#FFCC66"));

        //backButton.setBackground(Color.PINK);
        
        //Μέγεθος και στοίχιση κουμπιών ρόλου
        Dimension buttonSize = new Dimension(200, 35);
        for (JButton btn : new JButton[]{loginButton, backButton}) {
            btn.setMaximumSize(buttonSize);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        
        //Προσθήκη στοιχείων στο loginPanel
        loginPanel.add(new JLabel("Όνομα χρήστη:"));
        loginPanel.add(usernameField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        loginPanel.add(new JLabel("Κωδικός:"));
        loginPanel.add(passwordField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        loginPanel.add(loginButton);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(backButton);

        // Βάλε το loginPanel μέσα στο wrapper και το wrapper στο Center
        centerWrapper.add(loginPanel);
        add(centerWrapper, BorderLayout.CENTER);

        //Λειτουργικότητα κουμπιών
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("1111")) {
                new StudentMenuFrame().setVisible(true);
                SwingUtilities.getWindowAncestor(this).dispose();
            }
            else {
                JOptionPane.showMessageDialog(this,"Έχετε εισάγει λανθασμένα στοιχεία σύνδεσης.\n     Προσπαθήστε ξανά πατώντας το ΟΚ");
            }
        });

        backButton.addActionListener(e -> {
            clearFields();
            parentFrame.showRoleSelectionPanel();
        });
    }
    //METHODS
    //Καθαρίζει τα πεδία και τα μηνύματα λάθους
    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }
}
