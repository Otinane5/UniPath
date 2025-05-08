package com.mycompany.technlog;

import javax.swing.*;
import java.awt.*;

public class CounselorLoginPanel extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel errorLabel;

    public CounselorLoginPanel(LoginFrame parentFrame) {
        setLayout(new BorderLayout());

        // === Τίτλος ===
        JLabel titleLabel = new JLabel("Σύνδεση Συμβούλου", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // === Κεντρικό wrapper με GridBag για κεντράρισμα ===
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        usernameField.setMaximumSize(new Dimension(200, 30));
        passwordField.setMaximumSize(new Dimension(200, 30));

        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLabel.setPreferredSize(new Dimension(250, 20));

        JButton loginButton = new JButton("Σύνδεση");
        JButton backButton = new JButton("Πίσω");

        loginButton.setBackground(new Color(144, 238, 144));
        backButton.setBackground(new Color(255, 229, 180));

        Dimension buttonSize = new Dimension(200, 35);
        loginButton.setMaximumSize(buttonSize);
        backButton.setMaximumSize(buttonSize);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        // Βάζουμε loginPanel μέσα στο wrapper
        centerWrapper.add(loginPanel);
        add(centerWrapper, BorderLayout.CENTER);

        // === Λειτουργικότητα ===
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("1234")) {
                new CounselorMenuFrame();
                SwingUtilities.getWindowAncestor(this).dispose();
            } else {
                errorLabel.setText("Λάθος όνομα χρήστη ή κωδικός!");
            }
        });

        backButton.addActionListener(e -> {
            clearFields();
            parentFrame.showRoleSelectionPanel();
        });
    }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        errorLabel.setText(" ");
    }
}
