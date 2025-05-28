package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DepartmentListCounselor extends JPanel {
    // ATTRIBUTES
    private String selectedDepartment = null; // Αποθήκευση επιλεγμένου τμήματος
    private JButton currentlySelectedButton = null;
    private JPanel departmentPanel;
    
    // Λίστα τμημάτων
    private List<DepartmentInfo> allDepartments = List.of(
        new DepartmentInfo("Τμήμα Πληροφορικής", "Θετικών Σπουδών", 5000, 12250),
        new DepartmentInfo("Τμήμα Μηχανικών Υπολογιστών", "Πολυτεχνικών Σπουδών", 10000, 16561),
        new DepartmentInfo("Τμήμα Νομικής", "Ανθρωπιστικών Σπουδών", 12000, 18921),
        new DepartmentInfo("Τμήμα Ιατρικής", "Σπουδών Υγείας", 16000, 19231),
        new DepartmentInfo("Τμήμα Ψυχολογίας", "Ανθρωπιστικών Σπουδών", 8000, 13211),
        new DepartmentInfo("Τμήμα Καλών Τεχνών", "Καλών Τεχνών", 14500, 8023),
        new DepartmentInfo("Τμήμα Φιλοσοφίας", "Ανθρωπιστικών Σπουδών", 6000, 10189),
        new DepartmentInfo("Τμήμα Φιλολογίας", "Ανθρωπιστικών Σπουδών", 5500, 16328),
        new DepartmentInfo("Τμήμα Διοίκησης Επιχειρήσεων", "Οικονομικών Σπουδών", 5000, 9823),
        new DepartmentInfo("Τμήμα Οικονομικών", "Οικονομικών Σπουδών", 6500, 11345),
        new DepartmentInfo("Τμήμα Φαρμακευτικής", "Σπουδών Υγείας", 12500, 18357),
        new DepartmentInfo("Τμήμα Μαθηματικών", "Θετικών Σπουδών", 8000, 13456),
        new DepartmentInfo("Τμήμα Χημικών Μηχανικών", "Πολυτεχνικών Σπουδών", 10500, 15672),
        new DepartmentInfo("Τμήμα Λογοθεραπείας", "Σπουδών Υγείας", 12000, 14860),
        new DepartmentInfo("Τμήμα Πολιτικών Επιστημών", "Ανθρωπιστικών Σπουδών", 13000, 12453),
        new DepartmentInfo("Τμήμα Εργοθεραπείας", "Σπουδών Υγείας", 8600, 8727),
        new DepartmentInfo("Τμήμα Ηλεκτρολόγων Μηχανικών", "Πολυτεχνικών Σπουδών", 15000, 18769)
    );
    
    private List<DepartmentInfo> filteredDepartments = new ArrayList<>(allDepartments);
        
    public DepartmentListCounselor(Runnable onBackToMainMenu, Consumer<String> onShowDepartment, 
            Runnable onShowFilters) 
    {
        setLayout(new BorderLayout(10, 10));

        // Τίτλος
        JLabel label = new JLabel("Λίστα Τμημάτων", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.NORTH);

        // Panel για τα κουμπιά της λίστας τμημάτων
        departmentPanel = new JPanel();
        departmentPanel.setLayout(new BoxLayout(departmentPanel, BoxLayout.Y_AXIS));
        departmentPanel.setBackground(Color.WHITE);
        refreshDepartmentList();
        
        // Panel για θέση του κουμπιού φίλτρων (πάνω αριστερά)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Κουμπί φίλτρων
        JButton filterButton = new JButton("Φίλτρα");
        filterButton.setBackground(Color.PINK);
        filterButton.setPreferredSize(new Dimension(80, 30));
        filterButton.setMaximumSize(new Dimension(80, 30));
        filterButton.addActionListener(e -> onShowFilters.run());
        
        topPanel.add(filterButton, BorderLayout.WEST);

        JScrollPane scrollPane = new JScrollPane(departmentPanel);
        
        JPanel listContainer = new JPanel();
        listContainer.setLayout( new BorderLayout());
        listContainer.add(topPanel, BorderLayout.NORTH);
        listContainer.add(scrollPane, BorderLayout.CENTER);
        
        add(listContainer, BorderLayout.CENTER);
             
        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Πίσω στο μενού κουμπί
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.YELLOW);
        backButton.addActionListener(e -> 
        { onBackToMainMenu.run(); });

        // Κουμπί προβολής τμήματος
        JButton showButton = new JButton("Προβολή Τμήματος");
        showButton.setBackground(Color.CYAN);
        showButton.addActionListener(e -> {
            if (selectedDepartment == null) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ επιλέξτε ένα τμήμα πρώτα.");
            } else {
                // Εμφάνιση του επιλεγμένου τμήματος
                onShowDepartment.accept(selectedDepartment);
            }
        });         
        
        // Προσθήκη Κουμπιών στο bottom panel
        bottomPanel.add(backButton);
        bottomPanel.add(showButton);

        // Τοποθέτηση του bottom panel στο κεντρικό
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void applyFilters(String type, String minFeeStr, String maxFeeStr, String minPointsStr){
        int minFee = minFeeStr.isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(minFeeStr);
        int maxFee = maxFeeStr.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(maxFeeStr);
        int minPoints = minPointsStr.isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(minPointsStr);

        filteredDepartments = allDepartments.stream()
            .filter(dept -> (type.equals("Όλα")|| dept.type.equals(type)))
            .filter(dept -> dept.tuitionFee >= minFee && dept.tuitionFee <= maxFee)
            .filter(dept -> dept.academicPoints >= minPoints)
            .toList();
        refreshDepartmentList();
    }
    
    //Προσθήκη κουμπιών κάθε τμήματος και ανάλογα με τα φίλτρα
    private void refreshDepartmentList()
    {
         departmentPanel.removeAll();
         currentlySelectedButton = null;
         for(DepartmentInfo dept : filteredDepartments)
         {
            JButton deptButton = new JButton(dept.name);
            deptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            deptButton.setMaximumSize(new Dimension(400, 40));
            deptButton.addActionListener(e -> 
            {
                selectedDepartment = dept.name;
                if(currentlySelectedButton != null)
                {
                    currentlySelectedButton.setBackground(UIManager.getColor("Button.background"));
                }
                deptButton.setBackground(Color.LIGHT_GRAY);
                currentlySelectedButton = deptButton;
            });
            departmentPanel.add(Box.createVerticalStrut(10));
            departmentPanel.add(deptButton);
         }
         departmentPanel.revalidate();
         departmentPanel.repaint();
    }
}
