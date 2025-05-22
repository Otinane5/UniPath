package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class DepartmentListPanel extends JPanel {
    
    public DepartmentListPanel(CardLayout cardLayout, JPanel cardPanel, ProfilePanel detailsProfilePanel) {
        setLayout(new BorderLayout(10, 10));

        JLabel listTitle = new JLabel("Λίστα Τμημάτων Πανεπιστημίου", SwingConstants.CENTER);
        listTitle.setFont(new Font("Arial", Font.BOLD, 20));
        listTitle.setOpaque(true);
        listTitle.setBackground(Color.decode("#66A3FF"));
        listTitle.setBounds(110,75,300,25);
        add(listTitle, BorderLayout.NORTH);

        String[] departments={"Πληροφορικής","Μηχανικών Πληροφορικής","Ιατρικής","Οικονομικών","Πολιτικών Επιστημών","Εργοθεραπείας","Ηλεκτρολόγων Μηχανικών","Καλών Τεχνών"};
        
        for(String department : departments) {
            JPanel departmentPanel=new JPanel(new BorderLayout());
            departmentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
            
            JLabel departmentLabel= new JLabel(department, SwingConstants.CENTER);
            departmentLabel.setFont(new Font("Arial", Font.PLAIN,14));
            departmentPanel.add(departmentLabel,BorderLayout.CENTER);
            
            JButton viewProfile= new JButton("Προβολή Προφίλ Τμήματος");
            viewProfile.setBackground(Color.CYAN);
            viewProfile.addActionListener(e-> cardLayout.show(cardPanel, "profileDetails"));
                //new ProfileFrame(parentFrame,uni_name, department).setVisible(true);
                //JOptionPane.showMessageDialog(this, "Προφίλ τμήματος:"+department);
                // Will add more logic here soon
                //});
            departmentPanel.add(viewProfile, BorderLayout.EAST);
        }
        
        // BOTTOM OF THE PANEL
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton homeButton = new JButton("Αρχική Σελίδα");
        homeButton.setBackground(Color.decode("#B3FF66"));
        homeButton.setBounds(170,330,150,30);
        
        JButton back = new JButton("Πίσω");
        back.setBackground(Color.decode("#FFCC66"));
        back.setBounds(330,330,150,30);
        
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        back.addActionListener(e -> cardLayout.show(cardPanel, "menu"));

        buttonPanel.add(homeButton);
        buttonPanel.add(back);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    public void getDepartments() {
    }
    public void getDepartmentList() {
    }
    //argument departmentID int
    public void selectDepartment() {
    }
    public void requestDepartmentList() {
    }
}
//μεγαλύτερο box λίστας