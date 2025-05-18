package com.mycompany.unipathui;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author HelenaSiskou
 */
public class ChooseFiltersScreen extends JFrame {
     private JTextField name,dept,city,min,max;
   public ChooseFiltersScreen()
   {
        setTitle("Ορισμός Φίλτρων");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("UniPath", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(155,20,200,30);
        add(title);
        
        //ανάκτηση του ονόματος μέσω του login
        JLabel uni_name=new JLabel("<Όνομα πανεπιστημίου>", SwingConstants.CENTER);
        uni_name.setFont(new Font("Arial", Font.ITALIC,14));
        uni_name.setBounds(155,55,200,20);
        add(uni_name);
        
        JButton messagesButton = new JButton("Τα μηνύματά μου"); 
        ImageIcon envelopeIcon = new ImageIcon(getClass().getResource("/icons/envelope.png"));
        Image envelopeImage = envelopeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
        messagesButton.setIcon(new ImageIcon(envelopeImage));
        messagesButton.setBounds(330,20,160,30); // adjust width for icon
        add(messagesButton); 

        JLabel departmentLabel= new JLabel("Τμήμα:");
        departmentLabel.setBounds(50,125,120,25);
        add(departmentLabel);
        dept=new JTextField();
        dept.setBounds(180,125,230,25);
        add(dept);
        
        JLabel cityLabel= new JLabel("Πόλη:");
        cityLabel.setBounds(50,160,120,25);
        add(cityLabel);
        city=new JTextField();
        city.setBounds(180,160,230,25);
        add(city);
        
        JLabel minLabel= new JLabel("Ελάχιστα μόρια:");
        minLabel.setBounds(50,195,120,25);
        add(minLabel);
        min=new JTextField();
        min.setBounds(180,195,230,25);
        add(min);
        
        JLabel maxLabel= new JLabel("Μέγιστα μόρια:");
        maxLabel.setBounds(50,230,120,25);
        add(maxLabel);
        max=new JTextField();
        max.setBounds(180,230,230,25);
        add(max);
        
        //+περιορισμός για τα ακέραια min+max (Να μην μπορει σε εκείνα τα πεδία να γράψει αλφαριθμητικό)

        JButton saveButton=new JButton("Αποθήκευση Φίλτρων");
        saveButton.setBounds(50,270,170,30);
        saveButton.setBackground(Color.decode("#66FF66"));
        add(saveButton);
        saveButton.addActionListener(e->saveFilters());
        
        JButton logout=new JButton("Αποσύνδεση");
        logout.setBounds(10,330,150,30);
        logout.setBackground(Color.decode("#FF6666"));
        logout.setForeground(Color.BLACK);
        add(logout);
        //fix
        
        JButton homeButton = new JButton("Αρχική Σελίδα");
        homeButton.setBackground(Color.decode("#B3FF66"));
        homeButton.setBounds(170,330,150,30);
        add(homeButton);
        homeButton.addActionListener(e -> {
            new MainMenu().setVisible(true); 
            dispose(); 
        });

        JButton back= new JButton("Πίσω");
        back.setBackground(Color.decode("#FFCC66"));
        back.setBounds(330,330,150,30);
        back.addActionListener(e-> dispose());
        add(back);

   }
   
    public void saveFilters()
    {}
    
    public void returnFilteredApplicationList()
    {}
}



