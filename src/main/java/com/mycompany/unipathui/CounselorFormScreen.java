package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class CounselorFormScreen extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private JTextField emailField;
    private ButtonGroup interestGroup;
    private String studentName;
    private String counselorName;
    
    public CounselorFormScreen(String studentName, String counselorName) {
        this.studentName = studentName;
        this.counselorName = counselorName;
        
        setTitle("Φόρμα Ραντεβού");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("UniPath", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(250, 10, 200, 30);
        add(title);

        JLabel nameLabel = new JLabel(studentName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        nameLabel.setBounds(250, 40, 200, 20);
        add(nameLabel);

        

        JLabel formTitle = new JLabel("Φόρμα Ραντεβού", SwingConstants.CENTER);
        formTitle.setFont(new Font("Arial", Font.BOLD, 16));
        formTitle.setBounds(200, 80, 300, 25);
        add(formTitle);

        JLabel counselorLabel = new JLabel("Σύμβουλος: " + counselorName);
        counselorLabel.setBounds(200, 110, 300, 25);
        add(counselorLabel);

        JLabel firstNameLabel = new JLabel("Όνομα:");
        firstNameLabel.setBounds(150, 150, 100, 25);
        add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(250, 150, 300, 25);
        add(firstNameField);

        JLabel lastNameLabel = new JLabel("Επίθετο:");
        lastNameLabel.setBounds(150, 190, 100, 25);
        add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(250, 190, 300, 25);
        add(lastNameField);

        JLabel phoneLabel = new JLabel("Τηλέφωνο:");
        phoneLabel.setBounds(150, 230, 100, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(250, 230, 300, 25);
        add(phoneField);

        JLabel emailLabel = new JLabel("e-mail:");
        emailLabel.setBounds(150, 270, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(250, 270, 300, 25);
        add(emailField);

      JLabel interestLabel = new JLabel("Πεδίο ενδιαφέροντος:");
interestLabel.setBounds(120, 295, 200, 25); // moved up by 15px and widened
add(interestLabel);

        JPanel radioPanel = new JPanel();
radioPanel.setLayout(new GridLayout(2, 3, 5, 5)); 
 
radioPanel.setBounds(200, 310, 360, 60); 

interestGroup = new ButtonGroup();
String[] interestLabels = {
    "Πληροφορική", 
    "Μαθηματικά", 
    "Μηχανολογία", 
    "Φυσική", 
    "Ιατρική", 
    "Βιολογία"
};

for (String label : interestLabels) {
    JRadioButton btn = new JRadioButton(label);
    interestGroup.add(btn);
    radioPanel.add(btn);
}

add(radioPanel);


        JButton cancelBtn = new JButton("Ακύρωση");
        cancelBtn.setBackground(Color.RED);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setBounds(250, 370, 120, 30);
        add(cancelBtn);

        JButton submitBtn = new JButton("Υποβολή");
        submitBtn.setBackground(Color.GREEN);
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setBounds(390, 370, 120, 30);
        add(submitBtn);

        JButton logoutBtn = new JButton("Αποσύνδεση");
        logoutBtn.setBounds(30, 420, 150, 30);
        logoutBtn.setBackground(Color.decode("#FF6666"));
        logoutBtn.setForeground(Color.WHITE);
        add(logoutBtn);

        JButton homeBtn = new JButton("Αρχική Σελίδα");
        homeBtn.setBounds(270, 420, 150, 30);
        homeBtn.setBackground(Color.decode("#B3FF66"));
        add(homeBtn);

        JButton backBtn = new JButton("Πίσω");
        backBtn.setBounds(510, 420, 150, 30);
        backBtn.setBackground(Color.decode("#FFCC66"));
        add(backBtn);

        // Enhanced Cancel button functionality
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCancel();
            }
        });
        
        // Enhanced Submit button functionality
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        backBtn.addActionListener(e -> dispose());
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        homeBtn.addActionListener(e -> {
            dispose();
            new StudentMenuFrame().setVisible(true);
        });
    }
    
    private void handleCancel() {
        // Show confirmation dialog
        int option = JOptionPane.showConfirmDialog(
            this,
            "Είστε σίγουροι ότι θέλετε να ακυρώσετε την αίτηση;\nΌλα τα στοιχεία θα χαθούν.",
            "Ακύρωση Φόρμας",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (option == JOptionPane.YES_OPTION) {
            // Clear all fields
            clearForm();
            // Close current window and return to counselor list
            dispose();
            // You might want to open the counselor list window here
            // new CounselorListFrame().setVisible(true);
            JOptionPane.showMessageDialog(
                null,
                "Η φόρμα ακυρώθηκε. Επιστροφή στη λίστα συμβούλων.",
                "Ακύρωση",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    private void handleSubmit() {
        // Step 7: Validate form data
        if (validateForm()) {
            // Step 8: Create booking
            boolean bookingCreated = createBooking();
            
            if (bookingCreated) {
                // Step 9: Send confirmation message to student
                sendConfirmationMessage();
                
                // Step 10: Return to counselor list window
                JOptionPane.showMessageDialog(
                    this,
                    "Η φόρμα υποβλήθηκε επιτυχώς!\nΘα λάβετε το επιβεβαιωτικό μήνυμα σύντομα.",
                    "Επιτυχής Υποβολή",
                    JOptionPane.INFORMATION_MESSAGE
                );
                
                dispose();
                // You might want to open the counselor list window here
                // new CounselorListFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Υπήρξε σφάλμα κατά τη δημιουργία της κράτησης.\nΠαρακαλώ δοκιμάστε ξανά.",
                    "Σφάλμα Υποβολής",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    private boolean validateForm() {
        StringBuilder errors = new StringBuilder();
        
        // Validate first name
        if (firstNameField.getText().trim().isEmpty()) {
            errors.append("• Το όνομα είναι υποχρεωτικό\n");
        }
        
        // Validate last name
        if (lastNameField.getText().trim().isEmpty()) {
            errors.append("• Το επίθετο είναι υποχρεωτικό\n");
        }
        
        // Validate phone
        String phone = phoneField.getText().trim();
        if (phone.isEmpty()) {
            errors.append("• Το τηλέφωνο είναι υποχρεωτικό\n");
        } else if (!isValidPhone(phone)) {
            errors.append("• Το τηλέφωνο δεν είναι έγκυρο\n");
        }
        
        // Validate email
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            errors.append("• Το e-mail είναι υποχρεωτικό\n");
        } else if (!isValidEmail(email)) {
            errors.append("• Το e-mail δεν είναι έγκυρο\n");
        }
        
        // Validate interest field selection
        if (interestGroup.getSelection() == null) {
            errors.append("• Παρακαλώ επιλέξτε ένα πεδίο ενδιαφέροντος\n");
        }
        
        if (errors.length() > 0) {
            JOptionPane.showMessageDialog(
                this,
                "Παρακαλώ διορθώστε τα παρακάτω σφάλματα:\n\n" + errors.toString(),
                "Σφάλματα Επικύρωσης",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        
        return true;
    }
    
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    
    private boolean isValidPhone(String phone) {
        // Greek phone number validation (basic)
        String phoneRegex = "^(\\+30|0030|30)?[26789]\\d{9}$|^[26789]\\d{9}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phone.replaceAll("\\s|-", "")).matches();
    }
    
    private boolean createBooking() {
        try {
            // Simulate booking creation process
            // In a real application, this would interact with a database or service
            
            String selectedInterest = getSelectedInterest();
            
            // Create booking object or call service
            BookingData booking = new BookingData(
                firstNameField.getText().trim(),
                lastNameField.getText().trim(),
                phoneField.getText().trim(),
                emailField.getText().trim(),
                selectedInterest,
                counselorName,
                studentName
            );
            
            // Simulate processing time
            Thread.sleep(500);
            
            // In real implementation, save to database and return success/failure
            System.out.println("Booking created: " + booking.toString());
            
            return true; // Simulate successful booking creation
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private String getSelectedInterest() {
        for (AbstractButton button : java.util.Collections.list(interestGroup.getElements())) {
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return "";
    }
    
    private void sendConfirmationMessage() {
               System.out.println("Confirmation message sent to counselor " );
        
        
    }
    
    private void clearForm() {
        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        interestGroup.clearSelection();
    }
    
    // Inner class to represent booking data
    private static class BookingData {
        private String firstName;
        private String lastName;
        private String phone;
        private String email;
        private String interest;
        private String counselorName;
        private String studentName;
        
        public BookingData(String firstName, String lastName, String phone, 
                          String email, String interest, String counselorName, String studentName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
            this.interest = interest;
            this.counselorName = counselorName;
            this.studentName = studentName;
        }
        
        @Override
        public String toString() {
            return String.format("Booking{firstName='%s', lastName='%s', phone='%s', " +
                               "email='%s', interest='%s', counselor='%s', student='%s'}", 
                               firstName, lastName, phone, email, interest, counselorName, studentName);
        }
    }
}