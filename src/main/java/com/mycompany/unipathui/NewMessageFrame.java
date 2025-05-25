package com.mycompany.unipathui;

import com.mycompany.baseClasses.MessageService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewMessageFrame extends JFrame {
    private MessageService messageService;
    private String currentUserId;
    private String currentUserName;
    private String currentUserType;
    private MessageBoxFrame parentFrame;

    public NewMessageFrame(MessageBoxFrame parent, String currentUserId, String currentUserName, String currentUserType) {
        this.parentFrame = parent;
        this.currentUserId = currentUserId;
        this.currentUserName = currentUserName;
        this.currentUserType = currentUserType;
        this.messageService = MessageService.getInstance();
        
        setTitle("Νέο Μήνυμα - " + currentUserName);
        setSize(500, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Παραλήπτης
        JLabel toLabel = new JLabel("Παραλήπτης:");
        toLabel.setBounds(30, 30, 100, 25);
        add(toLabel);

        JTextField toField = new JTextField();
        toField.setBounds(130, 30, 200, 25);
        add(toField);
        
        // Κουμπί αναζήτησης παραλήπτη
        JButton searchButton = new JButton("Αναζήτηση");
        searchButton.setBounds(340, 30, 90, 25);
        searchButton.setBackground(Color.decode("#66B3FF"));
        add(searchButton);

        // Λίστα με προτεινόμενους παραλήπτες (αρχικά κρυφή)
        DefaultListModel<String> recipientListModel = new DefaultListModel<>();
        JList<String> recipientList = new JList<>(recipientListModel);
        JScrollPane recipientScrollPane = new JScrollPane(recipientList);
        recipientScrollPane.setBounds(130, 60, 300, 80);
        recipientScrollPane.setVisible(false);
        add(recipientScrollPane);

        // Θέμα
        JLabel subjectLabel = new JLabel("Θέμα:");
        subjectLabel.setBounds(30, 150, 100, 25);
        add(subjectLabel);

        JTextField subjectField = new JTextField();
        subjectField.setBounds(130, 150, 300, 25);
        add(subjectField);

        // Περιεχόμενο
        JLabel contentLabel = new JLabel("Περιεχόμενο:");
        contentLabel.setBounds(30, 190, 100, 25);
        add(contentLabel);

        JTextArea contentArea = new JTextArea();
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        contentScrollPane.setBounds(130, 190, 300, 150);
        add(contentScrollPane);

        // Ημερομηνία
        JLabel dateLabel = new JLabel("Ημερομηνία:");
        dateLabel.setBounds(30, 360, 100, 25);
        add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setBounds(130, 360, 150, 25);
        // Set current date as default
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateField.setText(LocalDateTime.now().format(dateFormatter));
        add(dateField);

        // Ώρα
        JLabel timeLabel = new JLabel("Ώρα:");
        timeLabel.setBounds(290, 360, 50, 25);
        add(timeLabel);

        JTextField timeField = new JTextField();
        timeField.setBounds(340, 360, 90, 25);
        // Set current time as default
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        timeField.setText(LocalDateTime.now().format(timeFormatter));
        add(timeField);

        // Κουμπί "Τώρα" για γρήγορη εισαγωγή τρέχουσας ημερομηνίας/ώρας
        JButton nowButton = new JButton("Τώρα");
        nowButton.setBounds(30, 400, 80, 25);
        nowButton.setBackground(Color.decode("#FFCC66"));
        add(nowButton);

        // Αποστολή
        JButton sendButton = new JButton("Αποστολή");
        sendButton.setBackground(Color.decode("#B3FF66"));
        sendButton.setBounds(130, 450, 100, 30);
        add(sendButton);

        // Ακύρωση
        JButton cancelButton = new JButton("Ακύρωση");
        cancelButton.setBackground(Color.decode("#FF6666"));
        cancelButton.setBounds(240, 450, 100, 30);
        add(cancelButton);

        // Προεπισκόπηση
        JButton previewButton = new JButton("Προεπισκόπηση");
        previewButton.setBackground(Color.decode("#E6B3FF"));
        previewButton.setBounds(350, 450, 120, 30);
        add(previewButton);

        // Αναζήτηση παραλήπτη
        searchButton.addActionListener(e -> {
            String searchTerm = toField.getText().trim();
            if (!searchTerm.isEmpty()) {
                recipientListModel.clear();
                
                // Add some sample recipients based on user type
                if (currentUserType.equals("STUDENT")) {
                    recipientListModel.addElement("counselor1 - Σύμβουλος Παπαδόπουλος (counselor)");
                    recipientListModel.addElement("university1 - Πανεπιστήμιο Αθηνών (university)");
                    recipientListModel.addElement("university2 - ΤΕΙ Θεσσαλονίκης (university)");
                } else if (currentUserType.equals("COUNSELOR")) {
                    recipientListModel.addElement("student1 - Μαρία Ιωάννου (student)");
                    recipientListModel.addElement("student2 - Γιάννης Παπαδόπουλος (student)");
                    recipientListModel.addElement("university1 - Πανεπιστήμιο Αθηνών (university)");
                } else if (currentUserType.equals("UNIVERSITY")) {
                    recipientListModel.addElement("student1 - Μαρία Ιωάννου (student)");
                    recipientListModel.addElement("student2 - Γιάννης Παπαδόπουλος (student)");
                    recipientListModel.addElement("counselor1 - Σύμβουλος Παπαδόπουλος (counselor)");
                }
                
                recipientScrollPane.setVisible(true);
                repaint();
            }
        });

        // Επιλογή από λίστα παραλήπτων
        recipientList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selected = recipientList.getSelectedValue();
                if (selected != null) {
                    toField.setText(selected);
                    recipientScrollPane.setVisible(false);
                    repaint();
                }
            }
        });

        // Κουμπί "Τώρα"
        nowButton.addActionListener(e -> {
            LocalDateTime now = LocalDateTime.now();
            dateField.setText(now.format(dateFormatter));
            timeField.setText(now.format(timeFormatter));
        });

        // Προεπισκόπηση
        previewButton.addActionListener(e -> {
            String recipient = toField.getText().trim();
            String subject = subjectField.getText().trim();
            String content = contentArea.getText().trim();
            String date = dateField.getText().trim();
            String time = timeField.getText().trim();

            if (recipient.isEmpty() || subject.isEmpty() || content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ συμπλήρωσε τα βασικά πεδία για προεπισκόπηση.", 
                                            "Ελλιπή Στοιχεία", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String previewContent = "ΠΡΟΕΠΙΣΚΟΠΗΣΗ ΜΗΝΥΜΑΤΟΣ\n" +
                                  "========================\n" +
                                  "Από: " + currentUserName + " (" + currentUserType.toLowerCase() + ")\n" +
                                  "Προς: " + recipient + "\n" +
                                  "Θέμα: " + subject + "\n" +
                                  "Ημερομηνία/Ώρα: " + date + " " + time + "\n\n" +
                                  "Περιεχόμενο:\n" + content;

            new MessageViewFrame("Προεπισκόπηση - " + subject, previewContent).setVisible(true);
        });

        // Λειτουργία αποστολής
        sendButton.addActionListener(e -> {
            String recipient = toField.getText().trim();
            String subject = subjectField.getText().trim();
            String content = contentArea.getText().trim();
            String date = dateField.getText().trim();
            String time = timeField.getText().trim();

            // Validation
            if (recipient.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ επίλεξε παραλήπτη.", 
                                            "Κενό Πεδίο", JOptionPane.WARNING_MESSAGE);
                toField.requestFocus();
                return;
            }
            
            if (subject.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ γράψε θέμα.", 
                                            "Κενό Πεδίο", JOptionPane.WARNING_MESSAGE);
                subjectField.requestFocus();
                return;
            }
            
            if (content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ γράψε περιεχόμενο.", 
                                            "Κενό Πεδίο", JOptionPane.WARNING_MESSAGE);
                contentArea.requestFocus();
                return;
            }

            if (date.isEmpty() || time.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ συμπλήρωσε ημερομηνία και ώρα.", 
                                            "Κενό Πεδίο", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Parse recipient info
            String recipientId = parseRecipientId(recipient);
            String recipientName = parseRecipientName(recipient);
            String recipientType = parseRecipientType(recipient);

            if (recipientId == null) {
                JOptionPane.showMessageDialog(this, "Μη έγκυρος παραλήπτης. Παρακαλώ χρησιμοποίησε την αναζήτηση.", 
                                            "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate date/time format
            String dateTime = date + " " + time;
            if (!isValidDateTime(dateTime)) {
                JOptionPane.showMessageDialog(this, "Μη έγκυρη μορφή ημερομηνίας/ώρας. Χρησιμοποίησε: dd/mm/yyyy hh:mm", 
                                            "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Send message
            boolean success = messageService.sendMessage(
                currentUserId, currentUserName, currentUserType,
                recipientId, recipientName, recipientType,
                subject, content, dateTime
            );

            if (success) {
                JOptionPane.showMessageDialog(this, "Το μήνυμα στάλθηκε επιτυχώς!", 
                                            "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
                
                // Refresh parent frame
                if (parentFrame != null) {
                    parentFrame.refreshMessages();
                }
                
                // Clear form or close
                int choice = JOptionPane.showConfirmDialog(this, 
                    "Θέλεις να στείλεις άλλο μήνυμα;", 
                    "Επιτυχία", 
                    JOptionPane.YES_NO_OPTION);
                
                if (choice == JOptionPane.YES_OPTION) {
                    // Clear form for new message
                    toField.setText("");
                    subjectField.setText("");
                    contentArea.setText("");
                    dateField.setText(LocalDateTime.now().format(dateFormatter));
                    timeField.setText(LocalDateTime.now().format(timeFormatter));
                    toField.requestFocus();
                } else {
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Σφάλμα κατά την αποστολή του μηνύματος. Προσπάθησε ξανά.", 
                                            "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Λειτουργία ακύρωσης
        cancelButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,
                "Είσαι σίγουρος ότι θέλεις να ακυρώσεις; Θα χαθούν όλα τα δεδομένα.",
                "Επιβεβαίωση Ακύρωσης",
                JOptionPane.YES_NO_OPTION);
            
            if (choice == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
    }

    // Helper methods for parsing recipient information
    private String parseRecipientId(String recipient) {
        if (recipient.contains(" - ")) {
            return recipient.split(" - ")[0];
        }
        return null;
    }

    private String parseRecipientName(String recipient) {
        if (recipient.contains(" - ") && recipient.contains(" (")) {
            String part = recipient.split(" - ")[1];
            return part.substring(0, part.lastIndexOf(" ("));
        }
        return recipient; // fallback
    }

    private String parseRecipientType(String recipient) {
        if (recipient.contains("(") && recipient.contains(")")) {
            String type = recipient.substring(recipient.lastIndexOf("(") + 1, recipient.lastIndexOf(")"));
            return type.toUpperCase();
        }
        return "STUDENT"; // default
    }

    // Validate date/time format
    private boolean isValidDateTime(String dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }}