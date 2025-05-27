package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.List;
import com.mycompany.baseClasses.Description; 

public class DepartmentProfileUI extends JPanel
{   
    //private static final Map<String, String> departmentDescriptions = new HashMap<>();
        
    //Στατική ανάθεση περιγραφών τμημάτων
    /*static {
        //ΟΛΑ ΑΥΤΑ ΕΜΦΑΝΙΖΟΝΤΑΙ ΣΤΟ ΠΕΡΙΒΑΛΛΟΝ ΤΟΥ ΜΑΘΗΤΗ
        departmentDescriptions.put("Τμήμα Πληροφορικής", "Το τμήμα πληροφορικής ιδρύθηκε το...");
        departmentDescriptions.put("Τμήμα Μηχανικών Υπολογιστών", "Το τμήμα Μηχανικών Υπολογιστών ιδρύθηκε το...");
        departmentDescriptions.put("Τμήμα Νομικής", "Η Νομική ιδρύθηκε...");
        departmentDescriptions.put("Τμήμα Ιατρικής", "Η Ιατρική ιδρύθηκε..");
        departmentDescriptions.put("Τμήμα Ψυχολογίας", "Η Ψυχολογία είναι ένα νέο τμήμα...");
        departmentDescriptions.put("Τμήμα Καλών Τεχνών", "Οι Καλές Τέχνες...");
        departmentDescriptions.put("Τμήμα Φιλοσοφίας", "Το τμήμα Φιλοσοφίας προσφέρει στους αποφοίτους του...");
        departmentDescriptions.put("Τμήμα Φιλολογίας", "Το τμήμα Φιλολογίας ιδρύθηκε..");
        departmentDescriptions.put("Τμήμα Διοίκησης Επιχειρήσεων", "Το τμήμα Διοίκησης επιχειρήσεων προσφέρει στους αποφοίτους του....");
        departmentDescriptions.put("Τμήμα Οικονομικών", "Το τμήμα Οικονομικών προσφέρει στους αποφοίτους του...");
        departmentDescriptions.put("Τμήμα Φαρμακευτικής", "Η Φαρμακευτική προσφέρει στους αποφοίτους του...");
        departmentDescriptions.put("Τμήμα Μαθηματικών", "Το τμήμα Μαθηματικών προσφέρει στους αποφοίτους του...");
        departmentDescriptions.put("Τμήμα Χημικών Μηχανικών", "Το τμήμα Χημικών Μηχανικών προσφέρει στους αποφοίτους του...");
        departmentDescriptions.put("Τμήμα Λογοθεραπείας", "Το νεοσύστατο τμήμα Λογοθεραπείας..");       
    }*/
    
    public DepartmentProfileUI(String departmentName, Runnable onGoBack, 
        Runnable onBackToMenu, BiConsumer<String, List<AnnouncementView>> onViewAnnouncement){
       
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel(departmentName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        //Η περιγραφή του τμήματος και το κουμπί λίστας ανακοινώσεων θα είναι σε κάθετο BoxLayout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
 
        //String description = departmentDescriptions.getOrDefault(departmentName,
          //  "Δεν υπάρχουν διαθέσιμες πληροφορίες για αυτό το τμήμα.");
        
        String description = Description.getDepartmentDescription(departmentName);

        JTextArea infoArea = new JTextArea(description);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        infoArea.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        
        JButton announcementButton = new JButton("Ανακοινώσεις");
        announcementButton.setBackground(Color.CYAN);
        announcementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        announcementButton.addActionListener(e -> {
            List<AnnouncementView> departmentAnnouncements = AnnouncementRepository.getAnnouncements(departmentName);
            onViewAnnouncement.accept(departmentName, departmentAnnouncements);
        });
        
        centerPanel.add(scrollPane);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(announcementButton);
        
        add(centerPanel, BorderLayout.CENTER);
              
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.YELLOW);
        backButton.addActionListener(e -> onGoBack.run());
        
        JButton menuButton = new JButton("Επιστροφή στο μενού");
        menuButton.setBackground(Color.ORANGE);
        menuButton.addActionListener(e -> onBackToMenu.run());
           
        bottomPanel.add(backButton);
        bottomPanel.add(menuButton);
        
        add(bottomPanel, BorderLayout.SOUTH);
    }            
}
