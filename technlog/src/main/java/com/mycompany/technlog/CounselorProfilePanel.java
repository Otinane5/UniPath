package com.mycompany.technlog;

import javax.swing.*;
import java.awt.*;

public class CounselorProfilePanel extends JPanel {
    public CounselorProfilePanel(Runnable onBackToMainMenu, Runnable onEditProfile) {
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Προφίλ Συμβούλου", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        formPanel.add(new JLabel("Όνομα:"));
        formPanel.add(new JLabel("Κώστας"));
        formPanel.add(new JLabel("Επώνυμο:"));
        formPanel.add(new JLabel("Παπαδόπουλος"));
        formPanel.add(new JLabel("Email:"));
        formPanel.add(new JLabel("kostas@email.com"));
        formPanel.add(new JLabel("Τηλέφωνο:"));
        formPanel.add(new JLabel("6900000000"));
        formPanel.add(new JLabel("Περιγραφή (Bio):"));
        JTextArea bio = new JTextArea("Εδώ είναι η περιγραφή του συμβούλου.");
        bio.setLineWrap(true);
        bio.setWrapStyleWord(true);
        bio.setEditable(false);
        formPanel.add(new JScrollPane(bio));

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton editButton = new JButton("Επεξεργασία Προφίλ");
        JButton backToMain = new JButton("Πίσω Στο Αρχικό Μενού");
        JButton backButton = new JButton("Πίσω");

        editButton.setBackground(new Color(173, 216, 230));
        backToMain.setBackground(new Color(224, 224, 224));
        backButton.setBackground(new Color(255, 229, 180));

        editButton.addActionListener(e -> onEditProfile.run());
        backToMain.addActionListener(e -> onBackToMainMenu.run());
        backButton.addActionListener(e -> onBackToMainMenu.run()); // εναλλακτικά: επιστροφή σε προηγούμενη οθόνη

        buttonPanel.add(editButton);
        buttonPanel.add(backToMain);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
