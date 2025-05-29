package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class AddAnnouncementPanel extends JPanel 
{
    private String departmentName;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton cancel;
    private JLabel departmentLabel;
    private JTextField titleField;
    private JTextArea bodyArea;
    private JButton publish;
    private ProfilePanel profilePanel; //αναφορά στο Profile Panel για ανανέωση ανακοινώσεων
    
    public AddAnnouncementPanel(CardLayout cardLayout, JPanel cardPanel,ProfilePanel profilePanel) 
    {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.profilePanel = profilePanel;

        setLayout(new BorderLayout(10, 10));
        
        // Title label
        JLabel titleLabel = new JLabel("Προσθήκη Νέας Ανακοίνωσης", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        // Main announcement panel
        JPanel annPanel= new JPanel(new GridBagLayout());
        annPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        // Department
        departmentLabel= new JLabel("Όνομα Τμήματος:"+departmentName, SwingConstants.CENTER); 
        departmentLabel.setFont(new Font("Arial", Font.BOLD,16));
        departmentLabel.setOpaque(true);
        departmentLabel.setBackground(Color.GREEN);
        departmentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        annPanel.add(departmentLabel, gbc);
        
        gbc.gridy++;
        JLabel announcementTitle=new JLabel("Τίτλος Ανακοίνωσης:");
        annPanel.add(announcementTitle, gbc);
                
        gbc.gridx = 1;
        titleField = new JTextField(25);
        annPanel.add(titleField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel body = new JLabel("Σώμα Ανακοίνωσης:");
        annPanel.add(body, gbc);
        
        gbc.gridx = 1;
        bodyArea = new JTextArea(6, 25);
        bodyArea.setLineWrap(true);
        bodyArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(bodyArea);
        annPanel.add(scrollPane, gbc);
        
        // Button row
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
       
        cancel = new JButton("Ακύρωση");
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        pressCancelNewAnnouncement(); 
       
        publish = new JButton("Δημοσίευση");
        publish.setBackground(Color.GREEN);
        
        //κλήση της μεθόδου για Δημοσίευση της ανακοίνωσης
        postAnnouncement();
        
        btnPanel.add(cancel);
        btnPanel.add(publish);
        
        annPanel.add(btnPanel, gbc);
        add(annPanel, BorderLayout.CENTER);
        
        // BOTTOM BUTTONS
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton homeButton = new JButton("Αρχική Σελίδα");
        homeButton.setBackground(Color.decode("#B3FF66"));
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        
        JButton back= new JButton("Πίσω");
        back.setBackground(Color.decode("#FFCC66"));
        back.addActionListener(e-> cardLayout.show(cardPanel, "seeProfileDetails"));
        
        buttonPanel.add(homeButton);
        buttonPanel.add(back);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
        public void postAnnouncement() 
        {
            publish.addActionListener(e -> 
            {
                //ανακτά τον τίτλο/σώμα και αφαιρεί τα όποια κενά
                String title = titleField.getText().trim();
                String bodyText = bodyArea.getText().trim();

                //έλεγχος για κενά πεδία
                if (title.isEmpty() || bodyText.isEmpty()) 
                {
                    JOptionPane.showMessageDialog(this, 
                        "Παρακαλώ συμπληρώστε όλα τα πεδία.", 
                        "Σφάλμα!", 
                        JOptionPane.ERROR_MESSAGE);
                        return;
                }
              
                //προσθέτει την ανακοίνωση στο Announcement repository για το συγκεκριμένο τμήμα
                AnnouncementView newAnnouncement = new AnnouncementView(title, bodyText);
                AnnouncementRepository.addAnnouncement(departmentName, newAnnouncement);
            
                // clear
                titleField.setText("");
                bodyArea.setText("");

                JOptionPane.showMessageDialog(this,
                    "Η ανακοίνωση προστέθηκε με επιτυχία στο "+departmentName+".");
                    if (profilePanel != null) 
                    {
                         profilePanel.refreshAnnouncements();
                    }
                cardLayout.show(cardPanel, "seeProfileDetails"); //επιστροφή στο Προφίλ
            });
        }
     
        //ορίζει την συμπεριφορά του κουμπιού "Ακύρωση"
        public void pressCancelNewAnnouncement()
        {
            cancel.addActionListener(e -> 
            {
                int result = JOptionPane.showConfirmDialog
                (
                    this,
                    "Η ανακοίνωση δεν θα αποθηκευτεί.\n Συμφωνείτε;",
                    "Επιβεβαίωση Ακύρωσης",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if (result == JOptionPane.YES_OPTION) 
                {
                    titleField.setText("");
                    bodyArea.setText("");
                    cardLayout.show(cardPanel, "seeProfileDetails");
                }
            });
        }
    
       public void setDepartmentName(String departmentName) 
        {
            this.departmentName = departmentName;
            departmentLabel.setText(departmentName); //ενημέρωση της ετικέτας με το όνομα Τμήματος
            JPanel contentPanel = (JPanel) getComponent(1);
            Component[] components = contentPanel.getComponents();
                for (Component comp : components) 
                {
                    if (comp instanceof JLabel && ((JLabel) comp).getText().startsWith("Τμήμα:")) 
                    {
                        ((JLabel) comp).setText("Τμήμα: " + departmentName);
                        break;
                    }
                }
        }
}
