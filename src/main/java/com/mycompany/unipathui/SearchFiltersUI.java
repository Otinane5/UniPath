package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class SearchFiltersUI extends JPanel {
    private final JComboBox<String> typeBox;
    private final JTextField minFeeField;
    private final JTextField maxFeeField;
    private final JTextField minPointsField;
    private FilterCriteria lastAppliedFilters;
    
    public SearchFiltersUI(Runnable onGoBack, Consumer<FilterCriteria> onApplyFilters){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBorder(BorderFactory.createTitledBorder("Φίλτρα Αναζήτησης"));
        innerPanel.setMaximumSize(new Dimension(300,9999));
        
        innerPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        JLabel typeLabel = new JLabel("Τύπος Τμήματος:");
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        innerPanel.add(typeLabel);
        typeBox = new JComboBox<>(new String[]{
            "Όλα", "Θετικών Σπουδών", "Ανθρωπιστικών Σπουδών", "Οικονομικών Σπουδών",
            "Πολυτεχνικών Σπουδών", "Σπουδών Υγείας", "Καλών Τεχνών"
        });
        typeBox.setMaximumSize(new Dimension(200, 25));
        typeBox.setPreferredSize(new Dimension(200, 25));
        typeBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        innerPanel.add(typeBox);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        JLabel minFeeLabel = new JLabel("Ελάχιστα Δίδακτρα: ");
        minFeeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        innerPanel.add(minFeeLabel);
        minFeeField = createSizedTextField();
        minFeeField.setAlignmentX(Component.CENTER_ALIGNMENT);
        innerPanel.add(minFeeField);
    
        innerPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        JLabel maxFeeLabel = new JLabel("Μέγιστα Δίδακτρα: ");
        maxFeeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        innerPanel.add(maxFeeLabel);
        maxFeeField = createSizedTextField();
        maxFeeField.setAlignmentX(Component.CENTER_ALIGNMENT);
        innerPanel.add(maxFeeField);
        
        innerPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        JLabel minPointsLabel = new JLabel("Ελάχιστα Μόρια: ");
        minPointsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        innerPanel.add(minPointsLabel);
        minPointsField = createSizedTextField();
        minPointsField.setAlignmentX(Component.CENTER_ALIGNMENT);
        innerPanel.add(minPointsField);
        
        innerPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        JButton applyFiltersButton = new JButton("Εφαρμογή Φίλτρων");
        applyFiltersButton.setBackground(Color.GREEN);
        applyFiltersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        applyFiltersButton.addActionListener(e -> {
            if(!validateInputs()){
                return; // Έλεγχος αν τα inputs είναι έγκυρα
            }
            lastAppliedFilters = getCriteria(); // Αποθήκευση των τρεχόντων φίλτρων
            onApplyFilters.accept(lastAppliedFilters);
        });
        innerPanel.add(applyFiltersButton);
             
        // Εκκαθάριση φίλτρων
        JButton clearFiltersButton = new JButton("Εκκαθάριση Φίλτρων");
        clearFiltersButton.setBackground(Color.YELLOW);
        clearFiltersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearFiltersButton.addActionListener(e -> {
            resetFilters();
            lastAppliedFilters = getCriteria();
            onApplyFilters.accept(lastAppliedFilters);
        });
        innerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerPanel.add(clearFiltersButton);
        
        // Ακύρωση φίλτρων
        JButton cancelFiltersButton = new JButton("Ακύρωση Αλλαγών");
        cancelFiltersButton.setBackground(Color.RED);
        cancelFiltersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelFiltersButton.addActionListener(e -> {
            if(lastAppliedFilters != null){
                setFilters(lastAppliedFilters);
            }
            onGoBack.run();
        });
        innerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        innerPanel.add(cancelFiltersButton);
        
        innerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        add(innerPanel);
    }
    
    public static class FilterCriteria{
        public final String type;
        public final String minFee;
        public final String maxFee;
        public final String minPoints;
        
        public FilterCriteria(String type, String minFee, String maxFee, String minPoints){
            this.type = type;
            this.minFee = minFee;
            this.maxFee = maxFee;
            this.minPoints = minPoints;
        }
    }
    
    public FilterCriteria getCriteria(){
        return new FilterCriteria(
            typeBox.getSelectedItem().toString(),
            minFeeField.getText(),
            maxFeeField.getText(),
            minPointsField.getText()    
        );
    }
      
    private JTextField createSizedTextField(){
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(200, 25));
        field.setPreferredSize(new Dimension(200, 25));
        return field;
    }
    
    public void resetFilters(){
        typeBox.setSelectedIndex(0); //Όλα
        minFeeField.setText("");
        maxFeeField.setText("");
        minPointsField.setText("");
    }
    
    public void setFilters(FilterCriteria filters) {
        if(filters == null){
            resetFilters();
            return;
        }
        typeBox.setSelectedItem(filters.type != null ? filters.type : "Όλα");
        minFeeField.setText(filters.minFee != null ? filters.minFee : "");
        maxFeeField.setText(filters.maxFee != null ? filters.maxFee : "");
        minPointsField.setText(filters.minPoints != null ? filters.minPoints : "");
    }
    
    private boolean validateInputs() {
        try{
            if (!minFeeField.getText().isEmpty()){
                int minFee = Integer.parseInt(minFeeField.getText());
                if(minFee < 0) throw new NumberFormatException();
            }
            if (!maxFeeField.getText().isEmpty()){
                int maxFee = Integer.parseInt(maxFeeField.getText());
                if(maxFee < 0) throw new NumberFormatException();
            }
            if (!minPointsField.getText().isEmpty()){
                int minPoints = Integer.parseInt(minPointsField.getText());
                if(minPoints < 0) throw new NumberFormatException();
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, 
                 "Παρακαλώ, εισάγετε μόνο μη αρνητικούς ακεραίους στα πεδία.",
                 "Μη έγκυρη/ες είσοδος/οι",
                 JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
