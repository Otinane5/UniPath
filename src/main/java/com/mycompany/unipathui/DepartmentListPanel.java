package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class DepartmentListPanel extends JPanel {
    
    private CardLayout cardLayout;
private JPanel cardPanel;


    public DepartmentListPanel(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
    this.cardPanel = cardPanel;
    
    
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel listTitle = new JLabel("Λίστα Τμημάτων Πανεπιστημίου", SwingConstants.CENTER);
        listTitle.setFont(new Font("Arial", Font.BOLD, 20));
        listTitle.setOpaque(true);
        listTitle.setBackground(Color.decode("#66A3FF"));
        listTitle.setBounds(110,75,300,25);
        add(listTitle, BorderLayout.NORTH);

        // Λίστα με τα panels για κάθε τμήμα
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        String[] departments = {
            "Πληροφορικής",
            "Μηχανικών Υπολογιστών",
            "Νομικής",
            "Ιατρικής",
            "Ψυχολογίας",
            "Καλών Τεχνών",
            "Φιλοσοφίας",
            "Φιλολογίας", 
            "Διοίκησης Επιχειρήσεων",
            "Οικονομικών",
            "Φαρμακευτικής",
            "Μαθηματικών",
            "Χημικών Μηχανικών",
            "Λογοθεραπείας",
            "Πολιτικών Επιστημών",
            "Εργοθεραπείας",
            "Ηλεκτρολόγων Μηχανικών",
            };
                
            
        for(String department : departments) {
            JPanel departmentPanel=new JPanel(new BorderLayout());
            departmentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
            departmentPanel.setBackground(Color.WHITE);
            departmentPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            
            JLabel departmentLabel= new JLabel(department, SwingConstants.CENTER);
            //JLabel departmentLabel = new JLabel(department, SwingConstants.LEFT);
            departmentLabel.setFont(new Font("Arial", Font.PLAIN,14));
            departmentLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            departmentPanel.add(departmentLabel,BorderLayout.CENTER);
            
            JButton viewProfile= new JButton("Προβολή Προφίλ Τμήματος");
            viewProfile.setBackground(Color.CYAN);
            viewProfile.addActionListener(e -> cardLayout.show(cardPanel, "seeProfileDetails"));
            departmentPanel.add(viewProfile, BorderLayout.EAST);
            
            listPanel.add(departmentPanel);
            listPanel.add(Box.createVerticalStrut(10)); // Απόσταση μεταξύ τμημάτων
        }

        // Scroll pane για λίστα
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // BOTTOM BUTTONS
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton homeButton = new JButton("Αρχική Σελίδα");
        homeButton.setBackground(Color.decode("#B3FF66"));
        homeButton.setBounds(170,330,150,30);
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        
        JButton back = new JButton("Πίσω");
        back.setBackground(Color.decode("#FFCC66"));
        back.setBounds(330,330,150,30);
        back.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
                
        buttonPanel.add(homeButton);
        buttonPanel.add(back);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    public void getDepartments() {
        // For future use
    }
    public void getDepartmentList() {
        // For future use
    }
    //argument departmentID int
    public void selectDepartment() {
        // For future use
    }
    public void requestDepartmentList() {
        // For future use
    }
}
