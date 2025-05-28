package com.mycompany.unipathui;
import com.mycompany.baseClasses.Application;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentApplicationsPanel extends JPanel 
{
    private final JPanel applicationListPanel = new JPanel();  
    
    public StudentApplicationsPanel(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        
        // Top Tools
        JPanel topTools = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topTools.setBackground(Color.WHITE);

        JLabel sectionLabel = new JLabel("Αιτήσεις Εγγραφής");
        sectionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topTools.add(sectionLabel);

        JButton filt= applyFilters(cardLayout,cardPanel);
        topTools.add(filt);

        add(topTools, BorderLayout.BEFORE_FIRST_LINE);

        // Application Panel
        applicationListPanel.setLayout(new BoxLayout(applicationListPanel, BoxLayout.Y_AXIS));
        applicationListPanel.setBackground(Color.WHITE);
        applicationListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        loadApplications(applicationListPanel);

        JScrollPane scrollPane = new JScrollPane(applicationListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // Footer Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(Color.WHITE);

        JButton homeButton = new JButton("Αρχική Σελίδα");
        homeButton.setBackground(Color.decode("#B3FF66"));
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        bottomPanel.add(homeButton);

        JButton back = new JButton("Πίσω");
        back.setBackground(Color.decode("#FFCC66"));
        back.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        bottomPanel.add(back);

        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private void loadApplications(JPanel panel) 
    {
        List<Application> applications = Application.sample;

        if (applications.isEmpty()) 
        {
            JLabel emptyLabel = new JLabel("Δεν υπάρχουν διαθέσιμες αιτήσεις.");
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(emptyLabel);
            return;
        }

        int i = 1;
        for (Application app : applications) {
            JPanel appPanel = new JPanel();
            appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.Y_AXIS));
            appPanel.setPreferredSize(new Dimension(600, 130));
            appPanel.setMaximumSize(new Dimension(600, 130));
            appPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            appPanel.setBackground(Color.WHITE);

            JLabel applicationLabel = new JLabel("Αίτηση " + i++);
            applicationLabel.setFont(new Font("Arial", Font.BOLD, 14));
            appPanel.add(applicationLabel);

            JLabel nameLabel = new JLabel("Ονοματεπώνυμο: " + app.fullName);
            appPanel.add(nameLabel);

            JLabel residenceLabel = new JLabel("Τόπος Διαμονής: " + app.residence);
            appPanel.add(residenceLabel);

            JLabel gradesLabel = new JLabel("Μόρια: " + app.gradePoints);
            appPanel.add(gradesLabel);

            JLabel stateLabel = new JLabel("Κατάσταση: " + translateState(app.state));
            stateLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            appPanel.add(stateLabel);
            
            JLabel departmentLabel = new JLabel("Τμήμα Ενδιαφέροντος: " + app.department);
            departmentLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            appPanel.add(departmentLabel);
            
            if ("sent".equals(app.state)) 
            {
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0)); 

                JButton accept = new JButton("Αποδοχή");
                accept.setBackground(new Color(0, 200, 100));
                accept.setForeground(Color.WHITE); 
                accept.addActionListener(e -> 
                    {
                        int result = JOptionPane.showConfirmDialog(this,
                            "Είστε σίγουροι ότι θέλετε να εγκρίνετε την αίτηση του/της " + app.fullName + ";",
                            "Επιβεβαίωση", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            app.state = "approved";
                            refresh();
                            JOptionPane.showMessageDialog(this, "Η αίτηση εγκρίθηκε επιτυχώς!");
                        }
                    });
                buttonPanel.add(accept);

                JButton reject = new JButton("Απόρριψη");
                reject.setBackground(new Color(200, 0, 0));
                reject.setForeground(Color.WHITE);
                reject.addActionListener(e -> 
                    {
                        int result = JOptionPane.showConfirmDialog(this,
                            "Είστε σίγουροι ότι θέλετε να απορρίψετε την αίτηση του/της " + app.fullName + ";",
                            "Επιβεβαίωση", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            app.state = "rejected";
                            refresh();
                            JOptionPane.showMessageDialog(this, "Η αίτηση απορρίφθηκε επιτυχώς!");
                        }
                    });
                buttonPanel.add(reject);

                appPanel.add(buttonPanel); // προσθήκη κουμπιών στο Panel
            }

            panel.add(appPanel);
            panel.add(Box.createVerticalStrut(10)); // κενό μεταξύ των αιτήσεων
        }
    }
    
    public JButton applyFilters(CardLayout cardLayout,JPanel cardPanel) 
    {
        JButton filt = new JButton("Ορισμός Φίλτρων");
        filt.setBackground(new Color(180, 210, 240));
        filt.setFocusPainted(false);
        filt.addActionListener(e -> cardLayout.show(cardPanel, "chooseFilters"));
        return filt;
    }
    
    private String translateState(String state) 
    {
        return switch (state) {
            case "approved" -> "Εγκεκριμένη";
            case "rejected" -> "Απορριφθείσα";
            default -> "Υποβληθείσα";
        };
    }
    
    public void refresh() {
        applicationListPanel.removeAll();       // καθάρισε τις προηγούμενες αιτήσεις
        loadApplications(applicationListPanel); // φόρτωσε ξανά από τη λίστα Application.sample
        applicationListPanel.revalidate();      // ενημέρωση layout
        applicationListPanel.repaint();         // redraw UI
    }
    
    //δεν εχει νόημα εδώ
    //public void viewApplications() {
        // For future use
    //}
}
