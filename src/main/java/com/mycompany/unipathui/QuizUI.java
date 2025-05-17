package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QuizUI extends JPanel {
    
    //ATTRIBUTES
    private final JLabel questionLabel;
    private final JRadioButton[] optionButtons;
    private final ButtonGroup optionGroup;
    private final List<String> questions;
    private int currentQuestionIndex=0;
    private final JButton nextButton;
    
    public QuizUI(Runnable onGoBack, Runnable onNextQuestion, Runnable onPreviousQuestion, Runnable onClearQuiz){
        setLayout(new BorderLayout(10, 10));
        
        //Τίτλος
        JLabel titleLabel = new JLabel("Quiz Επαγγελματικού Προσανατολισμού", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
              
        //Στατικές ερωτήσεις, file στο μέλλον;
        questions = List.of(
            "<html>Ερώτηση 1:<br>Καλείσαι να διαχειριστείς το budget μιας επιχείρησης "
            + "και να υπολογίσεις τα ετήσια έσοδα και έξοδά της.<br>Δέχεσαι την θέση;</html>",
                
            "<html>Ερώτηση 2:<br>Στις σχολικές παραστάσεις, "
            + "πάντα ήθελες να έχεις πρωταγωνιστικό ρόλο;</html>",
             
            "<html>Ερώτηση 3:<br>Τι θα έλεγες να ήσουν υπεύθυνος για την σχεδίαση "
            + "και κατασκευή ενός μεγάλου έργου ή μιας αστικής υποδομής; </html>",
            
            "<html>Ερώτηση 4:<br>Θα ήθελες να συμβάλλεις στην ανακάλυψη νέων φαρμάκων "
            + "ή ιατρικών θεραπειών;</html>",
            
            "<html>Ερώτηση 5:<br>Είναι συναρπαστικό να μαθαίνεις για τα φυσικά φαινόμενα "
            + "και να κατανοείς το μαθηματικό τους υπόβαθρο, εκτελώντας παράλληλα πειράματα. ;</html>",
            
            "<html>Ερώτηση 6:<br>Θα σου άρεσε βάσει των νόμων να αντιπαραθέτεις "
            + "τα επιχειρήματά σου (πχ. σε ένα debate, σε ένα δικαστήριο);</html>",
            
            "<html>Ερώτηση 7:<br>Θα σε ενδιέφερε να χτίσεις την δικιά σου επιχείρηση;</html>",
            
            "<html>Ερώτηση 8:<br>Σου αρέσει να αφηγείσαι ιστορίες και να εκφράζεις τα "
            + "συναισθήματά σου μέσω της τέχνης, ακόμα και μπροστά σε μεγάλο κοινό;</html>",
            
            "<html>Ερώτηση 9:<br>Σου αρέσει να βρίσκεις λύση σε περίπλοκα προβλήματα;</html>",
            
            "<html>Ερώτηση 10:<br>Θα ήθελες να εργαστείς σε ένα νοσοκομείο "
            + "ή ερευνητικό ιατρικό κέντρο;</html>",
            
            "<html>Ερώτηση 11:<br>Πάντα απολάμβανες να λύνεις μαθηματικά προβλήματα "
            + "και να συμμετέχεις σε διαγωνισμούς (πχ. ρομποτικής,μαθηματικών);</html>",
            
            "<html>Ερώτηση 12:<br>Η λογοτεχνία και τα φιλοσοφικά κείμενα σε συναρπάζουν;</html>",
            
            "<html>Ερώτηση 13:<br>Σε ενδιαφέρει η ανάπτυξη στρατηγικών "
            + "για την αύξηση της κερδοφορίας ενός οργανισμού;</html>",
            
            "<html>Ερώτηση 14:<br>Θα ήθελες να δουλεύεις σε περιβάλλον με μηχανήματα, "
            + "υπολογιστές ή τεχνολογικό εξοπλισμό;</html>",
            
            "<html>Ερώτηση 15:<br>Ασχολήθηκες ποτέ με την ζωγραφική ή μουσική;</html>",
            
            "<html>Ερώτηση 16:<br>Η μελέτη του ανθρώπινου σώματος είναι πολύ ενδιαφέρουσα.</html>",
            
            "<html>Ερώτηση 17:<br>Οι εξελίξεις στην Τεχνητή Νοημοσύνη απαιτούν ανάλυση "
            + "σύνθετων δεδομένων μέσω αλγορίθμων. Θα σε ενδιέφερε να ασχοληθείς με κάτι τέτοιο;</html>",
            
            "<html>Ερώτηση 18:<br>Σε ενδιαφέρει η μελέτη της ανθρώπινης ψυχοσύνθεσης;</html>"       
        );
     
        //Κεντρικό Panel ερωτήσεων και επιλογών
        JPanel centerPanel = new JPanel(new BorderLayout(10,10));
        questionLabel = new JLabel(questions.get(currentQuestionIndex), SwingConstants.CENTER);
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
        
        //Panel Υπόλοιπων Κουμπιών (κάθετα)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        //Panel Επόμενης και Προηγούμενης απάντησης (οριζόντια)
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton previousButton = new JButton("Προηγούμενη ερώτηση");
        previousButton.setBackground(Color.RED);
        previousButton.addActionListener(e -> {
            if(currentQuestionIndex>0){
                currentQuestionIndex--;
                updateQuestion();
            }
            else{
                JOptionPane.showMessageDialog(this, "Δεν μπορείς να πας πίσω, είσαι στην πρώτη ερώτηση!");
            }
        });
        
        nextButton = new JButton("Επόμενη ερώτηση");
        nextButton.setBackground(Color.GREEN);
        nextButton.addActionListener(e -> {
            if(getSelectedOptionText()==-1){
                JOptionPane.showMessageDialog(this, "Παρακαλώ, επέλεξε μια απάντηση.");  
                return;
            } 
                    
            if(currentQuestionIndex<questions.size()-1){
                currentQuestionIndex++;
                updateQuestion();
            }
            /*Αλλαγή του κουμπιού "Επόμενη ερώτηση" σε 
            "Δες τα αποτελέσματα" στην τελευταία ερώτηση*/
            if(currentQuestionIndex==questions.size()-1){
                nextButton.setText("Δες τα αποτελέσματά σου!");
                nextButton.setBackground(Color.CYAN);
            }
            //ΣΥΝΕΧΕΙΑ ΣΤΑ ΑΠΟΤΕΛΕΣΜΑΤΑ
        });
     
        JButton backButton = new JButton("Επιστροφή στο μενού");
        backButton.setBackground(Color.YELLOW);
        backButton.addActionListener(e -> onGoBack.run());
        
        JButton clearButton = new JButton("Εκκαθάριση Quiz");
        clearButton.setBackground(Color.ORANGE);
        clearButton.addActionListener(e -> onClearQuiz.run());
        
        navigationPanel.add(previousButton);
        navigationPanel.add(nextButton);
        navigationPanel.add(clearButton);

        buttonPanel.add(navigationPanel);
        buttonPanel.add(Box.createVerticalStrut(10));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(backButton);
       
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void updateQuestion() {
        questionLabel.setText(questions.get(currentQuestionIndex));
        optionGroup.clearSelection(); //Καθαρισμός προηγούμενης επιλογής
        
        if(currentQuestionIndex<questions.size()-1){
            nextButton.setText("Επόμενη ερώτηση");
            nextButton.setBackground(Color.GREEN);
        }
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
