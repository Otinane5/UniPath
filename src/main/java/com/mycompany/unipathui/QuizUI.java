package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class QuizUI extends JPanel {
    
    public QuizUI(Runnable onBackToMainMenu, Runnable onNextQuestion, Runnable onPreviousQuestion){
        //Τίτλος
        JLabel titleLabel = new JLabel("Quiz Επαγγελματικού Προσανατολισμού", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
    }
}
