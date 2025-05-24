package com.mycompany.unipathui;
import com.mycompany.baseClasses.Application;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
 
public class FilteredListScreen extends JPanel
{
 
    private final JPanel resultsPanel=new JPanel();
    private String departmentFilter="";
    private String cityFilter = "";
    private Integer minGrade = null;
    private Integer maxGrade = null;
    private String statusFilter="";

    public FilteredListScreen(CardLayout cardLayout, JPanel cardPanel)
    {
        setLayout(new BorderLayout(10,10));
        setBackground(Color.WHITE);

        JLabel titleLabel= new JLabel("Φιλτραρισμένη Λίστα Αιτήσεων", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD,20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15,0,15,0));
        add(titleLabel, BorderLayout.NORTH);

        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(Color.WHITE);
        resultsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane= new JScrollPane(resultsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel= new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
        bottomPanel.setBackground(Color.WHITE);

        JButton back = new JButton("Πίσω");
        back.setFont(new Font("Arial", Font.BOLD,14));
        back.setBackground(Color.decode("#FFCC66"));
        //back.setFocusPainted(false);
        back.setBorder(BorderFactory.createLineBorder(Color.decode("#CCA644")));
        back.setPreferredSize(new Dimension(120, 35));
        back.addActionListener(e -> cardLayout.show(cardPanel, "chooseFilters"));
        bottomPanel.add(back);

        JButton home = new JButton("Αρχική Σελίδα");
        home.setFont(new Font("Arial", Font.BOLD, 14));
        home.setBackground(Color.decode("#B3FF66"));
        //home.setFocusPainted(false);
        home.setBorder(BorderFactory.createLineBorder(Color.decode("#90CC44")));
        home.setPreferredSize(new Dimension(140, 35));
        home.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        bottomPanel.add(home);

        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    //public void selectApplication()
    //{} Δεν θα χρειαστεί αφου η αίτηση δεν "ανοίγει". 
    //όπως ειναι το Ui απλά παταει αποδοχή/απορριψη και φαίνονται επι τοπου ολα τα στοιχεια  

    //new
    public void setFilters(String department, String city, Integer min, Integer max, String status)
    {
        if(department==null)
            {this.departmentFilter="";}
        else
            {this.departmentFilter=department.trim().toLowerCase();}
        this.cityFilter = city == null ? "" : city.trim().toLowerCase(); //if else
        this.minGrade = min;
        this.maxGrade = max;
        this.statusFilter=status.trim();
        // this.statusFilter = (status == null) ? "" : status.trim().toLowerCase();

        returnResults(); 
        //filterApplications();
    }
    
    
    public void requestFullList()
    {returnResults();}
    
    public void returnResults()
    {
        List<Application> filtered = Application.sample.stream()
        .filter(app -> cityFilter.isEmpty() || app.residence.toLowerCase().contains(cityFilter))
        .filter(app -> departmentFilter.isEmpty() || app.department.toLowerCase().contains(departmentFilter))
        .filter(app -> statusFilter.isEmpty() || app.state.equalsIgnoreCase(statusFilter))

        //To do: department
        .filter(app -> 
        {
            try 
            {
                int points = Integer.parseInt(app.gradePoints);
                return (minGrade == null || points >= minGrade) && (maxGrade == null || points <= maxGrade);
            } 
            catch (NumberFormatException e) 
            {
                return false; // Exclude entries with invalid gradePoints
                //message
            }
        })
        .collect(Collectors.toList());

        displayFullList(filtered);
    }
    
    public void displayFullList(List<Application> list)
    {
        resultsPanel.removeAll();
        if(list.isEmpty())
        {
            JLabel noResults=new JLabel("Δεν υπάρχουν αποτελέσματα που να πληρούν τα φίλτρα που διαλέξατε.\n Προσπαθήστε ξανά");
            noResults.setFont(new Font("Arial", Font.ITALIC, 14));
            noResults.setForeground(Color.DARK_GRAY);
            noResults.setAlignmentX(Component.CENTER_ALIGNMENT);
            resultsPanel.add(Box.createVerticalGlue());
            resultsPanel.add(noResults);
            resultsPanel.add(Box.createVerticalGlue());        }
        else
        {
            JLabel countLabel = new JLabel("Βρέθηκαν " + list.size() + " αιτήσεις!");
            countLabel.setFont(new Font("Arial", Font.BOLD, 12));
            countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            countLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            resultsPanel.add(countLabel);
            
            int i = 1;
            for (Application app : list) 
            {
                JPanel appPanel = new JPanel();
                appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.Y_AXIS));
                appPanel.setPreferredSize(new Dimension(600, 130));
                appPanel.setMaximumSize(new Dimension(600, 130));
                appPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                appPanel.setBackground(Color.WHITE);

                JLabel applicationLabel = new JLabel("Αίτηση " + i++);
                applicationLabel.setFont(new Font("Arial", Font.BOLD, 14));
                appPanel.add(applicationLabel);

                appPanel.add(new JLabel("Ονοματεπώνυμο: " + app.fullName));
                appPanel.add(new JLabel("Τμήμα: " + app.department));
                appPanel.add(new JLabel("Τόπος Διαμονής: " + app.residence));
                appPanel.add(new JLabel("Μόρια: " + app.gradePoints));
                appPanel.add(new JLabel("Κατάσταση: " + translateState(app.state)));

                resultsPanel.add(appPanel);
                resultsPanel.add(Box.createVerticalStrut(10));
            }
        }
        resultsPanel.revalidate();
        resultsPanel.repaint();
    
    }
    
    private String translateState(String state)
    {
        return switch (state)
        {
            case "approved"-> "Εγκεκριμένη";
            case "rejected"-> "Απορριφθείσα";
            default-> "Υποβληθείσα";
                    
        };
    }
    
}
