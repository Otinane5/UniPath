package com.mycompany.unipathui;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import javax.swing.table.TableCellRenderer;


public class ShowResultsUI extends JPanel {
    public ShowResultsUI(Runnable onGoBackToMenu, AnswerLog answerLog){
        setLayout(new BorderLayout(10, 10));
        
        JLabel resultLabel = new JLabel("Αποτελέσματα Quiz:", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(resultLabel, BorderLayout.NORTH);
        
        //Προετοιμασία πίνακα αποτελεσμάτων
        Map<AnswerLog.DepartmentType, Integer> percentages = answerLog.getSortedPercentages();
        String[] columnNames = {"Τύπος Τμημάτων", "Ποσοστό Συμβατότητας"};
        Object[][] data = new Object[percentages.size()][2];
        int row = 0;
        
        for(Map.Entry<AnswerLog.DepartmentType, Integer> entry : percentages.entrySet()){
            String label = switch(entry.getKey()){
                case ART -> "Καλών Τεχνών";
                case ECONOMICS -> "Οικονομικών Σπουδών"; 
                case MEDICINE -> "Σπουδών Υγείας";
                case ENGINEERING -> "Πολυτεχνικών Σπουδών";
                case SOCIAL -> "Ανθρωπιστικών Σπουδών";
                case SCIENCE -> "Θετικών Επιστημών";     
            };
            data[row][0] = label;
            data[row][1] = entry.getValue() + "%";
            row++;
        }
        
        JTable table = new JTable(data, columnNames){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
                Component comp = super.prepareRenderer(renderer, row, column);
                //Ορισμός χρωματισμού των κελιών
                if(column == 1){
                    int percent = Integer.parseInt(getValueAt(row, 1).toString().replace("%", ""));
                    comp.setBackground(getColorByPercentage(percent));
                }
                else{
                    comp.setBackground(Color.WHITE);
                }  
                //Print των labels στο κέντρο των κελιών
                if(comp instanceof JLabel label){
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                }
                
                comp.setForeground(Color.BLACK);
                return comp;
            }
        
            @Override    
            public boolean isCellEditable(int row, int column){
                return false; //Κάνουμε τον πίνακα non editable
            }    
        };        
  
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD,16));
        
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        JButton menuButton = new JButton("Επιστροφή στο κεντρικό μενού");
        menuButton.setBackground(Color.YELLOW);
        menuButton.addActionListener(e -> onGoBackToMenu.run());
            
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(menuButton);
        add(bottomPanel, BorderLayout.SOUTH);      
    }
    
    private Color getColorByPercentage(int percent){
        if(percent >= 81) return new Color(0, 153, 0);     //Πράσινο
        if(percent >= 61) return new Color(102, 204, 0);   //Λαχανί
        if(percent >= 41) return new Color(255, 255, 102); //Κίτρινο
        if(percent >= 21) return new Color(255, 153, 153); //Ανοιχτό κόκκινο
        return new Color(255, 51, 51);                     //Κόκκινο
    }
}
