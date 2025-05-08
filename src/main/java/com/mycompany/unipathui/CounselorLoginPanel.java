package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

//Πάνελ Σύνδεσης Συμβούλου: Εμφανίζει πλαίσια για όνομα χρή-
//στη και κωδικό, καθώς και τα κουμπιά "Σύνδεση" και "Πίσω".

public class CounselorLoginPanel extends JPanel {
    //ATTRIBUTES
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel errorLabel;
    //CONSTRUCTOR
    public CounselorLoginPanel(LoginFrame parentFrame) {
        setLayout(new BorderLayout());

        //Επικεφαλίδα
        JLabel subtitle  = new JLabel("Σύνδεση Συμβούλου", SwingConstants.CENTER);
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
        usernameField.setMaximumSize(new Dimension(200, 30));
        
        // Πεδίο κωδικού
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(200, 30));
        
        //Μήνυμα λάθους
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Κουμπιά
        JButton loginButton = new JButton("Σύνδεση");
        loginButton.setBackground(new Color(144, 238, 144));
        
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(new Color(255, 229, 180));

        Dimension buttonSize = new Dimension(200, 35);
        loginButton.setMaximumSize(buttonSize);
        backButton.setMaximumSize(buttonSize);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Προσθήκη στοιχείων στο loginPanel
        loginPanel.add(new JLabel("Όνομα χρήστη:"));
        loginPanel.add(usernameField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        loginPanel.add(new JLabel("Κωδικός:"));
        loginPanel.add(passwordField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        loginPanel.add(errorLabel);
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

            if (username.equals("admin") && password.equals("1234")) {
                // TODO: Άνοιγμα νέου παραθύρου συμβούλου
                //new CounselorMenuFrame();
                SwingUtilities.getWindowAncestor(this).dispose();
            }
            else {
                errorLabel.setText("Λάθος όνομα χρήστη ή κωδικός!");
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
        errorLabel.setText(" ");
    }
}
