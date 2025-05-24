package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class SearchFiltersUI extends JPanel {
    private final JComboBox<String> typeBox;
    private final JTextField minFeeField;
    private final JTextField maxFeeField;
    private final JTextField minPointsField;
    
    public SearchFiltersUI(Runnable onGoBack, Consumer<FilterCriteria> onApplyFilters){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBorder(BorderFactory.createTitledBorder("Φίλτρα Αναζήτησης"));
        innerPanel.setMaximumSize(new Dimension(300,9999));
        
        innerPanel.add(new JLabel("Τύπος Τμήματος:"));
        typeBox = new JComboBox<>(new String[]{
            "Όλα", "Θετικών Σπουδών", "Ανθρωπιστικών Σπουδών", "Οικονομικών Σπουδών",
            "Πολυτεχνικών Σπουδών", "Σπουδών Υγείας", "Καλών Τεχνών"
        });
        innerPanel.add(typeBox);
        
        innerPanel.add(new JLabel("Ελάχιστα Δίδακτρα: "));
        minFeeField = createSizedTextField();
        innerPanel.add(minFeeField);
        
        innerPanel.add(new JLabel("Μέγιστα Δίδακτρα: "));
        maxFeeField = createSizedTextField();
        innerPanel.add(maxFeeField);
        
        innerPanel.add(new JLabel("Ελάχιστα Μόρια: "));
        minPointsField = createSizedTextField();
        innerPanel.add(minPointsField);
        
        innerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton applyFiltersButton = new JButton("Εφαρμογή Φίλτρων");
        applyFiltersButton.setBackground(Color.GREEN);
        applyFiltersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        applyFiltersButton.addActionListener(e -> onApplyFilters.accept(getCriteria()));
        innerPanel.add(applyFiltersButton);
        
        innerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.YELLOW);
        backButton.addActionListener(e -> onGoBack.run());
        innerPanel.add(backButton);
        
        add(innerPanel);
    }
    
    public FilterCriteria getCriteria(){
        return new FilterCriteria(
            typeBox.getSelectedItem().toString(),
            minFeeField.getText(),
            maxFeeField.getText(),
            minPointsField.getText()    
        );
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
    
    private JTextField createSizedTextField(){
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(200,25));
        field.setPreferredSize(new Dimension(200,25));
        return field;
    }
}
