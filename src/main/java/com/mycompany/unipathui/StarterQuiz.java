package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class StarterQuiz extends JPanel {
    public StarterQuiz(Runnable onBackToMainMenu, Runnable onStartQuiz){
        setLayout(new BorderLayout(10,10));
        
        //Τίτλος
        JLabel titleLabel = new JLabel("Κανόνες Quiz Επαγγελματικού Προσανατολισμού", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        
        //Οδηγίες
        JTextArea instructionsArea = new JTextArea(
            "Πριν ξεκινήσεις το Quiz επαγγελματικού προσανατολισμού: \n\n" + 
            "• To quiz αποτελείται από 18 ερωτήσεις. \n" +
            "• Σε κάθε ερώτηση θα υπάρχουν οι εξής επιλογές: \n" + 
            "  - Συμφωνώ απόλυτα\n" +
            "  - Συμφωνώ\n" +
            "  - Είμαι Ουδέτερος/η\n" +
            "  - Διαφωνώ\n" +
            "  - Διαφωνώ Απόλυτα\n" +
            "• Θα κληθείς να επιλέξεις την απάντησει που σε αντιπροσωπεύει περισσότερο. \n" +  
            "• Αφού επιλέξεις την απάντησή σου, θα πατήσεις το κουμπί 'Επόμενη Ερώτηση'. \n" +
            "• Αν επιθυμείς να αλλάξεις την απάντησή σου στην προηγούμενη ερώτηση, πατάς 'Προηγούμενη Ερώτηση'. \n" +  
            "• Στην τελευταία ερώτηση, πατάς την επιλογή 'Δες τα αποτελέσματα' για να ολοκληρώσεις το Quiz. \n" +
            "• Tα αποτελέσματά του Quiz σου θα εμφανιστούν στην οθόνη σου!.\n"                 
        );
        instructionsArea.setFont(new Font("SansSerif",Font.PLAIN,14));
        instructionsArea.setLineWrap(true);
        instructionsArea.setWrapStyleWord(true);
        instructionsArea.setEditable(false);
        instructionsArea.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        add(instructionsArea, BorderLayout.CENTER);
    
    //Κουμπιά
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
    JButton startButton = new JButton("Έναρξη Quiz");
    startButton.setBackground(Color.GREEN);
    startButton.addActionListener(e -> onStartQuiz.run());
    
    JButton backButton = new JButton("Πίσω");
    backButton.setBackground(Color.YELLOW);
    backButton.addActionListener(e -> onBackToMainMenu.run());
    
    buttonPanel.add(startButton);
    buttonPanel.add(backButton);
    
    add(buttonPanel, BorderLayout.SOUTH);
    }
}
