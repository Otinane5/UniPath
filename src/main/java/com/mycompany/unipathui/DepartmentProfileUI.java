package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DepartmentProfileUI extends JPanel{
    
    //Στατική ανάθεση περιγραφών τμημάτων
    private static final Map<String, String> departmentDescriptions = new HashMap<>();
    static {
        departmentDescriptions.put("Τμήμα Πληροφορικής", "Περιγραφή τμήματος πληροφορικής.");
        departmentDescriptions.put("Τμήμα Μηχανικών Υπολογιστών", "Περιγραφή τμήματος ceid.");
        departmentDescriptions.put("Τμήμα Νομικής", "Περιγραφή τμήματος νομικής.");
        departmentDescriptions.put("Τμήμα Ιατρικής", "Περιγραφή τμήματος ιατρικής.");
        departmentDescriptions.put("Τμήμα Ψυχολογίας", "Περιγραφή τμήματος ψυχολογίας.");
        departmentDescriptions.put("Τμήμα Καλών Τεχνών", "Περιγραφή τμήματος καλών τεχνών.");
        departmentDescriptions.put("Τμήμα Φιλοσοφίας", "Περιγραφή τμήματος φιλοσοφίας.");
        departmentDescriptions.put("Τμήμα Φιλολογίας", "Περιγραφή τμήματος φιλολογίας.");
        departmentDescriptions.put("Τμήμα Διοίκησης Επιχειρήσεων", "Περιγραφή τμήματος διοίκησης επιχειρήσεων.");
        departmentDescriptions.put("Τμήμα Οικονομικών", "Περιγραφή τμήματος οικονομικών.");
        departmentDescriptions.put("Τμήμα Φαρμακευτικής", "Περιγραφή τμήματος φαρμακευτικής.");
        departmentDescriptions.put("Τμήμα Μαθηματικών", "Περιγραφή τμήματος μαθηματικών.");
        departmentDescriptions.put("Τμήμα Χημικών Μηχανικών", "Περιγραφή τμήματος χημικών μηχανικών.");
        departmentDescriptions.put("Τμήμα Λογοθεραπείας", "Περιγραφή τμήματος λογοθεραπείας.");       
    }
    
    public DepartmentProfileUI(String departmentName, Runnable onGoBack, 
        Runnable onBackToMenu, Runnable onViewAnnouncement){
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel(departmentName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        //Η περιγραφή του τμήματος και το κουμπί λίστας ανακοινώσεων θα είναι σε κάθετο BoxLayout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
 
        String description = departmentDescriptions.getOrDefault(departmentName,
            "Δεν υπάρχουν διαθέσιμες πληροφορίες για αυτό το τμήμα.");
        
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
        announcementButton.addActionListener(e -> onViewAnnouncement.run());
        
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
