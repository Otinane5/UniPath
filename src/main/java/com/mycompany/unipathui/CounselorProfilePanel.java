package com.mycompany.unipathui;

import com.mycompany.baseClasses.Counselor;
import com.mycompany.baseClasses.Unipath;
import javax.swing.*;
import java.awt.*;

public class CounselorProfilePanel extends JPanel {
    public static Counselor CounselorToDisplay = new Counselor(); //πριν χρησιμοποιηθεί αυτή η κλάση ΠΡΕΠΕΙ να έχει φορτωθεί εδώ ο σύμβουλος που θα προβληθεί
    public CounselorProfilePanel(Runnable onBackToMainMenu, Runnable onEditProfile) {
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Προφίλ Συμβούλου", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        formPanel.add(new JLabel("Όνομα:"));
        formPanel.add(new JLabel(CounselorToDisplay.name));
        formPanel.add(new JLabel("Επώνυμο:"));
        formPanel.add(new JLabel(CounselorToDisplay.lastName));
        formPanel.add(new JLabel("Email:"));
        formPanel.add(new JLabel(CounselorToDisplay.email));
        formPanel.add(new JLabel("Τηλέφωνο:"));
        formPanel.add(new JLabel(CounselorToDisplay.phoneNum));
        formPanel.add(new JLabel("Περιγραφή (Bio):"));
        JTextArea bio = new JTextArea(CounselorToDisplay.bio);
        bio.setLineWrap(true);
        bio.setWrapStyleWord(true);
        bio.setEditable(false);
        formPanel.add(new JScrollPane(bio));

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton backToMain = new JButton("Πίσω Στο Αρχικό Μενού");
        backToMain.setBackground(Color.decode("#B3FF66"));
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.decode("#FFCC66"));

        if(Unipath.currentUser.userName.equals(CounselorToDisplay.userName)){
        JButton editButton = new JButton("Επεξεργασία Προφίλ");
        editButton.setBackground(new Color(173, 216, 230));
        editButton.addActionListener(e -> onEditProfile.run());
        buttonPanel.add(editButton);
    }
        
        backToMain.addActionListener(e -> onBackToMainMenu.run());
        backButton.addActionListener(e -> onBackToMainMenu.run()); // εναλλακτικά: επιστροφή σε προηγούμενη οθόνη

        
        buttonPanel.add(backToMain);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
