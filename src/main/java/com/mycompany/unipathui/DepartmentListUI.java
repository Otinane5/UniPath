package com.mycompany.unipathui;

import com.mycompany.baseClasses.Student;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class DepartmentListUI extends JPanel {
    // ATTRIBUTES
    private String selectedDepartment = null;
    private JButton currentlySelectedButton = null;
    private JPanel departmentPanel;
    private boolean quizTaken = false;

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
        new DepartmentInfo("Τμήμα Λογοθεραπείας", "Σπουδών Υγείας", 12000, 14860)
    );
    private List<DepartmentInfo> filteredDepartments = new ArrayList<>(allDepartments);

    public DepartmentListUI(Runnable onBackToMainMenu, Consumer<String> onShowDepartment,
                            Runnable onApplicationForm, Runnable onShowFilters) {
        setLayout(new BorderLayout(10, 10));

        // Τίτλος
        JLabel label = new JLabel("Λίστα Τμημάτων", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.NORTH);

        // Panel για τα κουμπιά της λίστας τμημάτων
        departmentPanel = new JPanel();
        departmentPanel.setLayout(new BoxLayout(departmentPanel, BoxLayout.Y_AXIS));
        departmentPanel.setBackground(Color.WHITE);

        // Αρχική φόρτωση χωρίς ποσοστά/χρώματα
        refreshDepartmentList();

        // Panel για φίλτρα
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton filterButton = new JButton("Φίλτρα");
        filterButton.setBackground(Color.PINK);
        filterButton.setPreferredSize(new Dimension(80, 30));
        filterButton.setMaximumSize(new Dimension(80, 30));
        filterButton.addActionListener(e -> onShowFilters.run());

        topPanel.add(filterButton, BorderLayout.WEST);

        JScrollPane scrollPane = new JScrollPane(departmentPanel);

        JPanel listContainer = new JPanel(new BorderLayout());
        listContainer.add(topPanel, BorderLayout.NORTH);
        listContainer.add(scrollPane, BorderLayout.CENTER);

        add(listContainer, BorderLayout.CENTER);

        // Bottom Panel με κουμπιά ενέργειας
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.YELLOW);
        backButton.addActionListener(e -> onBackToMainMenu.run());

        JButton showButton = new JButton("Προβολή Τμήματος");
        showButton.setBackground(Color.CYAN);
        showButton.addActionListener(e -> {
            if (selectedDepartment == null) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ επιλέξτε ένα τμήμα πρώτα.");
            } else {
                onShowDepartment.accept(selectedDepartment);
            }
        });

        JButton applicationButton = new JButton("Αίτηση Εγγραφής");
        applicationButton.setBackground(Color.GREEN);
        applicationButton.addActionListener(e -> {
            if (selectedDepartment == null) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ επιλέξτε ένα τμήμα πρώτα.");
            } else {
                Application_FormUI.uniName = selectedDepartment;
                onApplicationForm.run();
            }
        });

        JButton suggestionsButton = new JButton("Προτάσεις για Εσένα");
        suggestionsButton.setBackground(Color.ORANGE);
        suggestionsButton.addActionListener(e -> {
            Map<AnswerLog.DepartmentType, Integer> percentages = Student.answerLog.getSortedPercentages();
            if (!Student.hasAnswerLog) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ συμπληρώστε πρώτα το quiz για να δείτε προτάσεις.");
                return;
            }else{
            quizTaken = true;}
            refreshDepartmentList();
        });

        bottomPanel.add(backButton);
        bottomPanel.add(showButton);
        bottomPanel.add(applicationButton);
        bottomPanel.add(suggestionsButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void applyFilters(String type, String minFeeStr, String maxFeeStr, String minPointsStr) {
        int minFee = minFeeStr.isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(minFeeStr);
        int maxFee = maxFeeStr.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(maxFeeStr);
        int minPoints = minPointsStr.isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(minPointsStr);

        filteredDepartments = allDepartments.stream()
            .filter(dept -> ("Όλα".equals(type) || dept.type.equals(type)))
            .filter(dept -> dept.tuitionFee >= minFee && dept.tuitionFee <= maxFee)
            .filter(dept -> dept.academicPoints >= minPoints)
            .toList();

        refreshDepartmentList();
    }

    private void refreshDepartmentList() {
        departmentPanel.removeAll();
        currentlySelectedButton = null;

        Map<AnswerLog.DepartmentType, Integer> percentages = quizTaken ? Student.answerLog.getSortedPercentages() : Map.of();

        if (quizTaken) {
            filteredDepartments.sort(Comparator.comparing(
                (DepartmentInfo dept) -> percentages.getOrDefault(mapType(dept.type), 0)
            ).reversed());
        }

        for (DepartmentInfo dept : filteredDepartments) {
            int pct = percentages.getOrDefault(mapType(dept.type), -1);
            String btnText = quizTaken ? String.format("%s - %d%%", dept.name, pct) : dept.name;
            JButton deptButton = new JButton(btnText);
            deptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            deptButton.setMaximumSize(new Dimension(400, 50));
            deptButton.setPreferredSize(new Dimension(400, 50));
            deptButton.setFont(new Font("Arial", Font.PLAIN, 14));
            deptButton.setBackground(quizTaken ? getColorByPercentage(pct) : Color.WHITE);
            deptButton.setOpaque(true);
            deptButton.addActionListener(e -> {
                selectedDepartment = dept.name;
                if (currentlySelectedButton != null) {
                    if (quizTaken) {
                        String prevText = currentlySelectedButton.getText();
                        int prevPct = Integer.parseInt(prevText.replaceAll(".*-(\\d+)%", "$1"));
                        currentlySelectedButton.setBackground(getColorByPercentage(prevPct));
                    } else {
                        currentlySelectedButton.setBackground(Color.WHITE);
                    }
                }
                deptButton.setBackground(Color.LIGHT_GRAY);
                currentlySelectedButton = deptButton;
            });
            departmentPanel.add(Box.createVerticalStrut(8));
            departmentPanel.add(deptButton);
        }

        departmentPanel.revalidate();
        departmentPanel.repaint();
    }

    private AnswerLog.DepartmentType mapType(String greekType) {
        return switch (greekType) {
            case "Καλών Τεχνών" -> AnswerLog.DepartmentType.ART;
            case "Σπουδών Υγείας" -> AnswerLog.DepartmentType.MEDICINE;
            case "Οικονομικών Σπουδών" -> AnswerLog.DepartmentType.ECONOMICS;
            case "Πολυτεχνικών Σπουδών" -> AnswerLog.DepartmentType.ENGINEERING;
            case "Ανθρωπιστικών Σπουδών" -> AnswerLog.DepartmentType.SOCIAL;
            case "Θετικών Σπουδών" -> AnswerLog.DepartmentType.SCIENCE;
            default -> null;
        };
    }

    private Color getColorByPercentage(int percent) {
        if (percent >= 81) return new Color(0, 153, 0);
        if (percent >= 61) return new Color(102, 204, 0);
        if (percent >= 41) return new Color(255, 255, 102);
        if (percent >= 21) return new Color(255, 153, 153);
        return new Color(255, 51, 51);
    }
}
