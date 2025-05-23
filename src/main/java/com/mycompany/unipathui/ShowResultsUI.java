package com.mycompany.unipathui;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ShowResultsUI extends JPanel {
    public ShowResultsUI(Runnable onGoBackToMenu, AnswerLog answerLog){
        setLayout(new BorderLayout(10, 10));
        
        JLabel resultLabel = new JLabel("Τα αποτελέσματά σου εμφανίζονται εδώ:", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(resultLabel, BorderLayout.NORTH);
        
        JTextArea resultArea = new JTextArea("Τα αποτελέσματα δεν υπολογίζονται ακόμα.");
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        
        StringBuilder sb = new StringBuilder();
        Map<AnswerLog.DepartmentType, Integer> percentages = answerLog.getSortedPercentages();
        for(Map.Entry<AnswerLog.DepartmentType, Integer> entry : percentages.entrySet()){
            String label = switch(entry.getKey()){
                case ART -> "Καλών Τεχνών";
                case ECONOMICS -> "Οικονομικών Σπουδών"; 
                case MEDICINE -> "Σπουδών Υγείας";
                case ENGINEERING -> "Πολυτεχνικών Σπουδών";
                case SOCIAL -> "Ανθρωπιστικών Σπουδών";
                case SCIENCE -> "Θετικών Επιστημών";     
            };
            sb.append(label).append(": ").append(entry.getValue()).append("%\n");
        }
        
        resultArea.setText(sb.toString());
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        
        JButton menuButton = new JButton("Επιστροφή στο κεντρικό μενού");
        menuButton.setBackground(Color.YELLOW);
        menuButton.addActionListener(e -> onGoBackToMenu.run());
            
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(menuButton);
        add(bottomPanel, BorderLayout.SOUTH);      
    }
}
