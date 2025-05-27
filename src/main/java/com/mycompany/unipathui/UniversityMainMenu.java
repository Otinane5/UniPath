package com.mycompany.unipathui;

import com.mycompany.baseClasses.Unipath;

import javax.swing.*;
import java.awt.*;

public class UniversityMainMenu extends JFrame {
    
    private JPanel cardPanel;
    private CardLayout cardLayout;
    
    public UniversityMainMenu() { 
        setTitle("UniPath - Πανεπιστήμιο");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout(10, 10));

        // --- TOP PANEL ---
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //Προαιρετικό padding
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1; // κεντρική στήλη
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;

        //Τίτλοι (στο κέντρο)
        JLabel titleLabel = new JLabel("UniPath");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel uni_name = new JLabel(Unipath.currentUser.userName);
        uni_name.setFont(new Font("Arial", Font.ITALIC,14));
        uni_name.setHorizontalAlignment(SwingConstants.CENTER);
        uni_name.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Τίτλοι σε κάθετη στοίχιση
        JPanel titleBox = new JPanel();
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));
        titleBox.setOpaque(false);
        titleBox.add(titleLabel);
        titleBox.add(uni_name);
        
        //Προσθήκη τίτλων στο κέντρο
        topPanel.add(titleBox, gbc);
        
        //Κουμπί Μηνυμάτων (δεξιά)
        JButton messagesButton = new JButton("Τα μηνύματά μου");
        messagesButton.setPreferredSize(new Dimension(160, 30)); //Σταθερό μέγεθος
        
        ImageIcon envelopeIcon = new ImageIcon(getClass().getResource("/icons/envelope.png"));
        Image envelopeImage = envelopeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        messagesButton.setIcon(new ImageIcon(envelopeImage));
        messagesButton.setBounds(630, 10, 150, 30);
        add(messagesButton);
        messagesButton.addActionListener(e -> new MessageBoxFrame().setVisible(true));
        
        //Τοποθέτηση κουμπιού δεξιά
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        topPanel.add(messagesButton, gbc);
        
        //Dummy "αόρατο" panel αριστερά για να εξισορροπήσει το βάρος
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.0;
        topPanel.add(Box.createHorizontalStrut(160), gbc); // ίδιο πλάτος με το κουμπί
        
        //Προσθήκη panel στο frame
        add(topPanel, BorderLayout.NORTH);
        
        // --- CENTER ---
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        // Προσθήκη τίτλου
        JLabel actionTitle = new JLabel("Επιλογή Ενέργειας:");
        actionTitle.setFont(new Font("Arial", Font.BOLD, 18));
        actionTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Κέντρο
        menuPanel.add(actionTitle);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Απόσταση από κουμπιά

        JButton viewApplicationsButton = new JButton("Προβολή Αιτήσεων Μαθητών");
        viewApplicationsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewApplicationsButton.setBounds(50,40,200,30);
        viewApplicationsButton.setBackground(Color.GREEN);

        JButton viewDepartmentsButton= new JButton("Προβολή Λίστας Τμημάτων Πανεπιστημίου");
        viewDepartmentsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewDepartmentsButton.setBounds(10,80,280,30);
        viewDepartmentsButton.setBackground(Color.CYAN);
        
        menuPanel.add(viewApplicationsButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(viewDepartmentsButton);

        add(cardPanel, BorderLayout.CENTER);

        // --- BOTTOM ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton logoutButton = new JButton("Αποσύνδεση");
        logoutButton.setBounds(185,280,150,30);
        logoutButton.setBackground(Color.decode("#FF6666"));
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- Panels --- 
        StudentApplicationsPanel applicationsPanel = new StudentApplicationsPanel(cardLayout, cardPanel);
        ProfilePanel profilePanel = new ProfilePanel(cardLayout, cardPanel);
                DepartmentListPanel deplistPanel = new DepartmentListPanel(cardLayout, cardPanel,profilePanel);

        EditDescriptionPanel editDesc = new EditDescriptionPanel(cardLayout, cardPanel,ProfilePanel.currentDepartment);
        AddAnnouncementPanel addAnnoun = new AddAnnouncementPanel(cardLayout, cardPanel); //,departmentName
        FilteredListScreen filteredListScreen = new FilteredListScreen(cardLayout, cardPanel);
        ChooseFiltersScreen chooseFilt = new ChooseFiltersScreen(cardLayout, cardPanel,filteredListScreen);
        
        cardPanel.add(menuPanel, "menu");
        cardPanel.add(applicationsPanel, "applications");
        cardPanel.add(deplistPanel, "seeListOfDepartments");
        cardPanel.add(profilePanel, "seeProfileDetails");
        cardPanel.add(editDesc, "editUniDesc");
        cardPanel.add(addAnnoun, "addAnnouncement");
        cardPanel.add(chooseFilt, "chooseFilters");
        cardPanel.add(filteredListScreen, "filteredList");

        // Action Listeners
        viewApplicationsButton.addActionListener(e -> cardLayout.show(cardPanel, "applications"));
        viewDepartmentsButton.addActionListener(e -> cardLayout.show(cardPanel, "seeListOfDepartments"));
        logoutButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Είστε σίγουρος ότι θέλετε να αποσυνδεθείτε;",
                    "Επιβεβαίωση Αποσύνδεσης",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                dispose(); // Κλείσιμο αυτού του frame
                new LoginFrame().setVisible(true); // Άνοιγμα login από την αρχή
            }
        });
        setVisible(true);
    }
}

