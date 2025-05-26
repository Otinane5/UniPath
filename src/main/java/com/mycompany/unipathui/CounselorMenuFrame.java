package com.mycompany.unipathui;

import com.mycompany.baseClasses.Unipath;
import javax.swing.*;
import java.awt.*;

//Frame of Counselor's Menu: shows the menu with the
//choices a counselor can make in our application.

public class CounselorMenuFrame extends JFrame {
    //ATTRIBUTES
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private DepartmentProfileUI depProfilePanel;
    private AnnouncementUI announcementPanel;
    //CONSTRUCTOR
    public CounselorMenuFrame() {
        setTitle("UniPath - Σύμβουλος");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout(10, 10));

        // --- TOP PANEL ---
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //Προαιρετικό padding
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1; // central column
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;

        //TITLES (IN THE CENTER)
        JLabel titleLabel = new JLabel("UniPath");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel counselorNameLabel = new JLabel(Unipath.currentUser.userName);
        counselorNameLabel.setFont(new Font("Arial", Font.ITALIC,14));
        counselorNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        counselorNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //TITLES (IN A COLUMN)
        JPanel titleBox = new JPanel();
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));
        titleBox.setOpaque(false);
        titleBox.add(titleLabel);
        titleBox.add(counselorNameLabel);
        
        //ADD TITLES IN THE CENTER
        topPanel.add(titleBox, gbc);
        
        //MESSAGE BUTTON (RIGHT)
       JButton messagesButton = new JButton("Τα μηνύματά μου");
        messagesButton.setPreferredSize(new Dimension(160, 30)); //specific
        
        ImageIcon envelopeIcon = new ImageIcon(getClass().getResource("/icons/envelope.png"));
        Image envelopeImage = envelopeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        messagesButton.setIcon(new ImageIcon(envelopeImage));
        messagesButton.setBounds(630, 10, 150, 30);
        add(messagesButton);
        messagesButton.addActionListener(e -> new MessageBoxFrame().setVisible(true));
        
        //ADD A DUMMY BUTTON (RIGHT
        //To make the UI "prettier"
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.0;
        topPanel.add(messagesButton, gbc);
        
        //Dummy "invisible" panel left
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.0;
        topPanel.add(Box.createHorizontalStrut(160), gbc); // same with message button
        
        //Add panel in the frame
        add(topPanel, BorderLayout.NORTH);
        
        // --- CENTER PANEL ---
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
        
        //MAKE CHOICE BUTTONS
        JLabel actionTitle = new JLabel("Επιλογή Ενέργειας:");
        actionTitle.setFont(new Font("Arial", Font.BOLD, 18));
        actionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(actionTitle);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Distance from buttons

        Dimension buttonSize = new Dimension(300, 50);

        JButton profileButton = new JButton("Προβολή Προφίλ");
        profileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileButton.setPreferredSize(buttonSize);
        profileButton.setMaximumSize(buttonSize);
        profileButton.setBackground(Color.GREEN);

        JButton appointmentButton = new JButton("Προβολή Αιτήσεων Ραντεβού");
        appointmentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        appointmentButton.setPreferredSize(buttonSize);
        appointmentButton.setMaximumSize(buttonSize);
        appointmentButton.setBackground(Color.CYAN);

        JButton viewdepartmentsButton = new JButton("Προβολή λίστας τμημάτων");
        viewdepartmentsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewdepartmentsButton.setPreferredSize(buttonSize);
        viewdepartmentsButton.setMaximumSize(buttonSize);
        viewdepartmentsButton.setBackground(Color.CYAN);
        
        menuPanel.add(profileButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(appointmentButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        menuPanel.add(viewdepartmentsButton);

        add(cardPanel, BorderLayout.CENTER);

        // --- BOTTOM PANEL ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton logoutButton = new JButton("Αποσύνδεση");
        logoutButton.setBackground(Color.decode("#FF6666"));
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- PANELS --- 
        CounselorProfilePanel profilePanel = new CounselorProfilePanel(
                () -> cardLayout.show(cardPanel, "menu"),
                () -> cardLayout.show(cardPanel, "editProfile")
        );
        
        DepartmentListCounselor deplistPanel = new DepartmentListCounselor(
                () -> cardLayout.show(cardPanel, "menu"),
                (String departmentName) -> showDepartmentProfile(departmentName),
                () -> cardLayout.show(cardPanel, "setFilters")
        ); 
        
        SearchFiltersUI filterPanel = new SearchFiltersUI(
                () -> cardLayout.show(cardPanel, "seeListOfDepartments"),
                criteria -> {
                    deplistPanel.applyFilters(
                    criteria.type, criteria.minFee, criteria.maxFee, criteria.minPoints
                    );
                    cardLayout.show(cardPanel, "seeListOfDepartments");
                }
        );
        
        CounselorAcceptAppointmentPanel acceptPanel = new CounselorAcceptAppointmentPanel(cardLayout, cardPanel);
        CounselorRejectAppointmentPanel rejectPanel = new CounselorRejectAppointmentPanel(cardLayout, cardPanel);
        CounselorAppointmentDetailsPanel detailsPanel = new CounselorAppointmentDetailsPanel(cardLayout, cardPanel, acceptPanel, rejectPanel);
        CounselorAppointmentRequestsPanel appointmentPanel = new CounselorAppointmentRequestsPanel(cardLayout, cardPanel, detailsPanel);
        CounselorEditProfilePanelUI editPanel = new CounselorEditProfilePanelUI(cardLayout, cardPanel, profilePanel);
        
        cardPanel.add(menuPanel, "menu");
        cardPanel.add(appointmentPanel, "appointments");
        cardPanel.add(detailsPanel, "appointmentDetails");
        cardPanel.add(acceptPanel, "acceptAppointment");
        cardPanel.add(rejectPanel, "rejectAppointment");
        cardPanel.add(editPanel, "editProfile");
        cardPanel.add(profilePanel, "profile");
        
        cardPanel.add(deplistPanel, "seeListOfDepartments");
        cardPanel.add(filterPanel, "setFilters");
        
        // ACTION LISTENERS
        appointmentButton.addActionListener(e -> cardLayout.show(cardPanel, "appointments"));
        profileButton.addActionListener(e -> cardLayout.show(cardPanel, "profile"));
        viewdepartmentsButton.addActionListener(e -> cardLayout.show(cardPanel, "seeListOfDepartments"));
        logoutButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Είστε σίγουρος ότι θέλετε να αποσυνδεθείτε;",
                    "Επιβεβαίωση Αποσύνδεσης",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                dispose(); // Close this frame
                new LoginFrame().setVisible(true); // Open the login from the beginning
            }
        });
        setVisible(true);
    }
    
    private void showDepartmentProfile(String departmentName) {
        if(depProfilePanel!=null){
            cardPanel.remove(depProfilePanel); 
        }
        
        depProfilePanel = new DepartmentProfileUI(
        departmentName,
        () -> cardLayout.show(cardPanel, "seeListOfDepartments"),
        () -> cardLayout.show(cardPanel, "menu"),
        (deptName, announcements) -> showDepartmentAnnouncements(deptName, announcements)        
        );
        cardPanel.add(depProfilePanel, "showProfile");
        cardLayout.show(cardPanel, "showProfile");
    }
    
    private void showDepartmentAnnouncements(String departmentName, java.util.List<AnnouncementView> announcements){
        if(announcementPanel!=null){
            cardPanel.remove(announcementPanel);    
        }
        
        announcementPanel = new AnnouncementUI(
            departmentName,
            announcements,
            () -> cardLayout.show(cardPanel, "showProfile"), //πίσω
            () -> cardLayout.show(cardPanel, "menu") //πίσω στο μενού  
        );
        
        cardPanel.add(announcementPanel, "showAnnouncements");
        cardLayout.show(cardPanel, "showAnnouncements");
    }
}
