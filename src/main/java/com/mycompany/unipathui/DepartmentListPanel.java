package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import com.mycompany.baseClasses.Description;

import static java.util.Map.entry;

public class DepartmentListPanel extends JPanel 
{
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private String selectedDepartment = null;
    private JButton viewProfileButton;
    private ProfilePanel profilePanel;
    
    public static final Map<Integer, String> departments = Map.ofEntries(
    entry(1, "Τμήμα Πληροφορικής"),
    entry(2, "Τμήμα Μηχανικών Υπολογιστών"),
    entry(3, "Τμήμα Νομικής"),
    entry(4, "Τμήμα Ιατρικής"),
    entry(5, "Τμήμα Ψυχολογίας"),
    entry(6, "Τμήμα Καλών Τεχνών"),
    entry(7, "Τμήμα Φιλοσοφίας"),
    entry(8, "Τμήμα Φιλολογίας"),
    entry(9, "Τμήμα Διοίκησης Επιχειρήσεων"),
    entry(10, "Τμήμα Οικονομικών"),
    entry(11, "Τμήμα Φαρμακευτικής"),
    entry(12, "Τμήμα Μαθηματικών"),
    entry(13, "Τμήμα Χημικών Μηχανικών"),
    entry(14, "Τμήμα Λογοθεραπείας"),
    entry(15, "Τμήμα Πολιτικών Επιστημών"),
    entry(16, "Τμήμα Εργοθεραπείας"),
    entry(17, "Τμήμα Ηλεκτρολόγων Μηχανικών")
    );
    
    public DepartmentListPanel(CardLayout cardLayout, JPanel cardPanel,ProfilePanel profilePanel) 
    {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.profilePanel = profilePanel; 
    
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel listTitle = new JLabel("Λίστα Τμημάτων Πανεπιστημίου", SwingConstants.CENTER);
        listTitle.setFont(new Font("Arial", Font.BOLD, 20));
        listTitle.setOpaque(true);
        listTitle.setBackground(Color.decode("#66A3FF"));
        add(listTitle, BorderLayout.NORTH);

        // Λίστα με τα panels για κάθε τμήμα
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
       getDepartmentList(listPanel);

        // Scroll pane για λίστα
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // BOTTOM BUTTONS
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton homeButton = new JButton("Αρχική Σελίδα");
        homeButton.setBackground(Color.decode("#B3FF66"));
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        
        JButton back = new JButton("Πίσω");
        back.setBackground(Color.decode("#FFCC66"));
        back.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
                
        viewProfileButton = new JButton("Προβολή Προφίλ Τμήματος");
        viewProfileButton.setBackground(Color.CYAN);
        viewProfileButton.setEnabled(false); // Αρχικά απενεργοποιημένο
        viewProfileButton.addActionListener(e -> 
        {
            if (selectedDepartment != null) 
            {
                String description = Description.getDepartmentDescription(selectedDepartment);
                profilePanel.setProfileData(selectedDepartment, description);
                cardLayout.show(cardPanel, "seeProfileDetails");
            }
        });
        
        buttonPanel.add(homeButton);
        buttonPanel.add(back);
        buttonPanel.add(viewProfileButton);
                
        add(buttonPanel, BorderLayout.SOUTH);
    }
    public void getDepartmentList(JPanel listPanel )
    {
        
     for (Map.Entry<Integer, String> entry : departments.entrySet())
        {
            String departmentName = entry.getValue();

            JPanel departmentPanel = new JPanel(new BorderLayout());
            departmentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            departmentPanel.setBackground(Color.WHITE);
            departmentPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            JLabel departmentLabel = new JLabel(departmentName, SwingConstants.CENTER);
            departmentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            departmentLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            departmentPanel.add(departmentLabel, BorderLayout.CENTER);

            selectDepartment(departmentPanel, departmentName, listPanel);

            listPanel.add(departmentPanel);
            listPanel.add(Box.createVerticalStrut(10));
        }
     
    }

    public void selectDepartment(JPanel departmentPanel, String department, JPanel listPanel) 
    {
        departmentPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        departmentPanel.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) 
            {
                selectedDepartment = department;
                viewProfileButton.setEnabled(true);
                highlightSelectedPanel(listPanel, departmentPanel);
            }
        });
    }
    private void highlightSelectedPanel(JPanel listPanel, JPanel selected) 
    {
        for (Component comp : listPanel.getComponents()) 
        {
            if (comp instanceof JPanel) 
            {
                comp.setBackground(Color.WHITE);
            }
        }
        selected.setBackground(Color.LIGHT_GRAY);
    }
}
