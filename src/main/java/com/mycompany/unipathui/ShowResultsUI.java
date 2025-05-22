package com.mycompany.unipathui;
import javax.swing.*;
import java.awt.*;

public class ShowResultsUI extends JPanel {
    public ShowResultsUI(Runnable onGoBackToMenu){
        setLayout(new BorderLayout(10, 10));
        
        JLabel resultLabel = new JLabel("Τα αποτελέσματά σου εμφανίζονται εδώ:", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(resultLabel, BorderLayout.NORTH);
        
        JTextArea resultArea = new JTextArea("Τα αποτελέσματα δεν υπολογίζονται ακόμα.");
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        
        JButton menuButton = new JButton("Επιστροφή στο κεντρικό μενού");
        menuButton.setBackground(Color.YELLOW);
        menuButton.addActionListener(e -> onGoBackToMenu.run());
            
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(menuButton);
        add(bottomPanel, BorderLayout.SOUTH);      
    }
}
