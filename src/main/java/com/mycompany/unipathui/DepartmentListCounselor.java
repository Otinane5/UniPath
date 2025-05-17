package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DepartmentListCounselor extends JPanel{
    //ATTRIBUTES
    private String selectedDepartment = null; //Αποθήκευση επιλεγμένου τμήματος
    private JButton currentlySelectedButton = null;
    public DepartmentListCounselor(Runnable onBackToMainMenu, Runnable onShowDepartment, Runnable onApplicationForm){
        setLayout(new BorderLayout(10,10));
        JLabel label = new JLabel("Λίστα Τμημάτων", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD,18));
        add(label,BorderLayout.NORTH);
        
        List<String> departments = List.of(
            "Τμήμα Πληροφορικής",
            "Τμήμα Μηχανικών Υπολογιστών",
            "Τμήμα Νομικής",
            "Τμήμα Ιατρικής",
            "Τμήμα Ψυχολογίας",
            "Τμήμα Καλών Τεχνών",   
            "Τμήμα Φιλοσοφίας",
            "Τμήμα Φιλολογίας", 
            "Τμήμα Διοίκησης Επιχειρήσεων",
            "Τμήμα Οικονομικών",
            "Τμήμα Φαρμακευτικής",
            "Τμήμα Μαθηματικών",
            "Τμήμα Χημικών Μηχανικών",
            "Τμήμα Λογοθεραπείας"
        );
        
        JPanel departmentPanel = new JPanel();
        departmentPanel.setLayout(new BoxLayout(departmentPanel, BoxLayout.Y_AXIS));
        for(String dept: departments){
            JButton deptButton = new JButton(dept);
            deptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            deptButton.setMaximumSize(new Dimension(400,40));
            deptButton.addActionListener(e-> {
                selectedDepartment = dept; //Ανάθεση επιλεγμένου τμήματος
                //Εμφάνιση της επιλογής οπτικά
                if (currentlySelectedButton != null){
                    currentlySelectedButton.setBackground(null); //επαναφορά επιλεγμένου 
                }
                deptButton.setBackground(Color.LIGHT_GRAY); //Ξεχωρισμός του επιλεγμένου
                currentlySelectedButton = deptButton;
            });
            
            departmentPanel.add(Box.createVerticalStrut(10));
            departmentPanel.add(deptButton);
        }

        JScrollPane scrollPane = new JScrollPane(departmentPanel);
        add(scrollPane, BorderLayout.CENTER);
        
        //Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.YELLOW);
        backButton.addActionListener(e->onBackToMainMenu.run());
        
        JButton showButton = new JButton("Προβολή Τμήματος");
        showButton.setBackground(Color.CYAN);
        showButton.addActionListener(e-> {
            if(selectedDepartment == null){
                JOptionPane.showMessageDialog(this, "Παρακαλώ επιλέξτε ένα τμήμα πρώτα.");
            }
        });
               
        bottomPanel.add(backButton);
        bottomPanel.add(showButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
