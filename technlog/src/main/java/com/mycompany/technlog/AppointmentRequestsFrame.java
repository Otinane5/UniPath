package com.mycompany.technlog;

import javax.swing.*;
import java.awt.*;

// Κλάση για το παράθυρο εμφάνισης των αιτημάτων ραντεβού προς τον σύμβουλο.
public class AppointmentRequestsFrame extends JFrame {
    
    // Constructor - Δημιουργεί και διαμορφώνει το παράθυρο
    public AppointmentRequestsFrame() {
        setTitle("UniPath - Αιτήματα Ραντεβού");        // Θέτουμε τον τίτλο του παραθύρου
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ορίζουμε να κλείνει η εφαρμογή όταν κλείνει το παράθυρο
        setSize(600, 600);                              // Ορίζουμε το μέγεθος του παραθύρου
        setLayout(new BorderLayout(10, 10));            // Χρησιμοποιούμε BorderLayout για την τοποθέτηση των στοιχείων
        
        // ΠΑΝΩ ΠΕΡΙΟΧΗ (NORTH)
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("UniPath", SwingConstants.LEFT);                     // Δημιουργία τίτλου UniPath
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));                           // Μεγαλύτερη και έντονη γραμματοσειρά
        JLabel counselorNameLabel = new JLabel("Κώστας Παπαδόπουλος", SwingConstants.LEFT); // Όνομα συμβούλου κάτω από τον τίτλο
        topPanel.add(titleLabel, BorderLayout.NORTH);                                       // Προσθήκη των στοιχείων στον πάνω πίνακα
        topPanel.add(counselorNameLabel, BorderLayout.CENTER);

        // Button "Τα μηνύματά μου" δεξιά
        JButton messagesButton = new JButton("Τα μηνύματά μου"); // Κουμπί "Τα μηνύματά μου" πάνω δεξιά
        topPanel.add(messagesButton, BorderLayout.EAST);         

        add(topPanel, BorderLayout.NORTH); // Προσθήκη του πάνω πίνακα στο πάνω μέρος του παραθύρου

        // ΚΕΝΤΡΙΚΟ ΠΕΡΙΕΧΟΜΕΝΟ (CENTER)
        JPanel centerPanel = new JPanel();                                   // Δημιουργία panel που θα περιέχει τις "κάρτες" αιτημάτων
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); //Κάθετη διάταξη

        // Προσθήκη αιτημάτων μαθητών (παραδείγματα, γιατί κανονικά θα πηγάζουν από τη βάση δεδομένων)
        centerPanel.add(createStudentRequestPanel("Μαρία Παπαδοπούλου", "6999999999", "maria@email.com", "Ψυχολογία"));
        centerPanel.add(createStudentRequestPanel("Μαρία Νικολάου", "6977777777", "maria@mail.com", "Ιατρική"));
        centerPanel.add(createStudentRequestPanel("Πέτρος Δημητρίου", "6988888888", "petros@mail.com", "Πληροφορική"));
        centerPanel.add(createStudentRequestPanel("Μαρία Παπαδοπούλου", "6999999999", "maria@email.com", "Ψυχολογία"));
        centerPanel.add(createStudentRequestPanel("Μαρία Νικολάου", "6977777777", "maria@mail.com", "Ιατρική"));
        centerPanel.add(createStudentRequestPanel("Πέτρος Δημητρίου", "6988888888", "petros@mail.com", "Πληροφορική"));
        centerPanel.add(createStudentRequestPanel("Μαρία Παπαδοπούλου", "6999999999", "maria@email.com", "Ψυχολογία"));
        centerPanel.add(createStudentRequestPanel("Μαρία Νικολάου", "6977777777", "maria@mail.com", "Ιατρική"));
        centerPanel.add(createStudentRequestPanel("Πέτρος Δημητρίου", "6988888888", "petros@mail.com", "Πληροφορική"));
        
        JScrollPane scrollPane = new JScrollPane(centerPanel); // Τοποθέτηση της λίστας σε ScrollPane για δυνατότητα κύλισης
        JPanel middlePanel = new JPanel();                     // Δημιουργία ενδιάμεσου panel που περιέχει τίτλο και scroll
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

        JLabel requestsTitle = new JLabel("Αιτήματα για ραντεβού");               // Τίτλος "Αιτήματα για ραντεβού"
        requestsTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        requestsTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Κενό γύρω από τον τίτλο
        
        middlePanel.add(requestsTitle); // Προσθήκη τίτλου και scroll στο ενδιάμεσο panel
        middlePanel.add(scrollPane);

        add(middlePanel, BorderLayout.CENTER); // Προσθήκη του ενδιάμεσου panel στο κέντρο του παραθύρου

        // ΚΑΤΩ ΠΕΡΙΟΧΗ (SOUTH)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel για τα κουμπιά πλοήγησης
        JButton logoutButton = new JButton("Αποσύνδεση");                   // Κουμπί "Αποσύνδεση"
        logoutButton.setBackground(new Color(255, 204, 204));               // Απαλό κόκκινο
        logoutButton.setForeground(Color.BLACK);
        JButton homeButton = new JButton("Αρχική σελίδα");                  // Κουμπί "Αρχική σελίδα"
        homeButton.setBackground(new Color(204, 255, 204));                 // Απαλό πράσινο
        homeButton.setForeground(Color.BLACK);
        JButton backButton = new JButton("Πίσω");                           // Κουμπί "Πίσω"
        backButton.setBackground(new Color(255, 229, 180));                 // Απαλό πορτοκαλί
        backButton.setForeground(Color.BLACK);

        bottomPanel.add(logoutButton); // Προσθήκη των κουμπιών στο κάτω panel
        bottomPanel.add(homeButton);
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH); // Προσθήκη κάτω panel στο κάτω μέρος του παραθύρου

        setVisible(true); // Κάνουμε το παράθυρο ορατό
    }
    
    // Δημιουργεί ένα panel που περιέχει τις πληροφορίες ενός αιτήματος από μαθητή.
    private JPanel createStudentRequestPanel(String name, String phone, String email, String interest) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));           // Βασικό panel για το αίτημα
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Πλαίσιο γύρω γύρω
        panel.setBackground(Color.WHITE);                            // Λευκό φόντο

        // Πληροφορίες μαθητή αριστερά
        JPanel infoPanel = new JPanel(new GridLayout(4, 1)); // Κάθετη διάταξη
        infoPanel.add(new JLabel("Όνομα: " + name));
        infoPanel.add(new JLabel("Τηλέφωνο: " + phone));
        infoPanel.add(new JLabel("Email: " + email));
        infoPanel.add(new JLabel("Πεδίο Ενδιαφέροντος: " + interest));

        // Κουμπιά αποδοχής / απόρριψης (δεξιά)
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5)); // Δύο σειρές με απόσταση
        JButton acceptButton = new JButton("Αποδοχή");
        acceptButton.addActionListener(e -> new AcceptAppointmentFrame("Κώστας Παπαδόπουλος"));
        acceptButton.setBackground(new Color(102, 255, 102)); // Πράσινο
        acceptButton.setForeground(Color.BLACK); // Μαύρο κείμενο
        
        JButton rejectButton = new JButton("Απόρριψη");
        rejectButton.setBackground(new Color(255, 102, 102)); // Κόκκινο
        rejectButton.setForeground(Color.BLACK); // Μαύρο κείμενο
        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);

        // Τοποθέτηση των panels στο βασικό panel
        panel.add(infoPanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.EAST);

        return panel;
    }
}