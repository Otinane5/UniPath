package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class QuizUI extends JPanel {
    
    //ATTRIBUTES
    private final JLabel questionLabel;
    private final JRadioButton[] optionButtons;
    private final ButtonGroup optionGroup;
    
    public QuizUI(Runnable onGoBack, Runnable onNextQuestion, Runnable onPreviousQuestion){
        setLayout(new BorderLayout(10, 10));
        
        //Τίτλος
        JLabel titleLabel = new JLabel("Quiz Επαγγελματικού Προσανατολισμού", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        
        //Κεντρικό Panel ερωτήσεων και Επιλογών
        JPanel centerPanel = new JPanel(new BorderLayout(10,10));
        questionLabel = new JLabel("<html>Ερώτηση 1: Καλείσαι να διαχειριστείς το budget μιας επιχείρησης "
        + "και να υπολογίσεις τα ετήσια έσοδα και έξοδά της.<br>Δέχεσαι την θέση;</html>");
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 18));      
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(questionLabel, BorderLayout.NORTH);
        
        //Επιλογές
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionButtons = new JRadioButton[5];
        optionGroup = new ButtonGroup();
        
        String[] options = {
            "Συμφωνώ απόλυτα",
            "Συμφωνώ",
            "Είμαι ουδέτερος/η",
            "Διαφωνώ",
            "Διαφωνώ απόλυτα"
        };

        for(int i=0; i<options.length; i++){
            optionButtons[i] = new JRadioButton(options[i]);
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN,14));
            optionGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        
        centerPanel.add(optionsPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
        
        //Panel Υπόλοιπων Κουμπιών
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton previousButton = new JButton("Προηγούμενη ερώτηση");
        previousButton.setBackground(Color.RED);
        previousButton.addActionListener(e -> onPreviousQuestion.run());
        
        JButton nextButton = new JButton("Επόμενη ερώτηση");
        nextButton.setBackground(Color.GREEN);
        nextButton.addActionListener(e -> onNextQuestion.run());
        
        JButton backButton = new JButton("Επιστροφή στο μενού");
        backButton.setBackground(Color.YELLOW);
        backButton.addActionListener(e -> onGoBack.run());
        
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(backButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void setQuestion(String questionText) {
        questionLabel.setText(questionText);
        optionGroup.clearSelection();
    }
    
    public int getSelectedOptionText() {
        for(int i=0; i<optionButtons.length; i++) {
            if(optionButtons[i].isSelected()){
                return i;
            }
        }
        return -1; //Καμία επιλογή
    }
}
