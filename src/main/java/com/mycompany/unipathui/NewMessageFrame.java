package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
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
        setSize(500, 540);
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
        
        JButton searchButton = new JButton("Αναζήτηση");
        searchButton.setBounds(340, 30, 90, 25);
        searchButton.setBackground(Color.decode("#66B3FF"));
        add(searchButton);

        DefaultListModel<String> recipientListModel = new DefaultListModel<>();
        JList<String> recipientList = new JList<>(recipientListModel);
        JScrollPane recipientScrollPane = new JScrollPane(recipientList);
        recipientScrollPane.setBounds(130, 60, 300, 80);
        recipientScrollPane.setVisible(false);
        add(recipientScrollPane);

        JLabel subjectLabel = new JLabel("Θέμα:");
        subjectLabel.setBounds(30, 150, 100, 25);
        add(subjectLabel);

        JTextField subjectField = new JTextField();
        subjectField.setBounds(130, 150, 300, 25);
        add(subjectField);

        JLabel contentLabel = new JLabel("Περιεχόμενο:");
        contentLabel.setBounds(30, 190, 100, 25);
        add(contentLabel);

        JTextArea contentArea = new JTextArea();
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        contentScrollPane.setBounds(130, 190, 300, 150);
        add(contentScrollPane);

        JLabel dateLabel = new JLabel("Ημερομηνία:");
        dateLabel.setBounds(30, 360, 100, 25);
        add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setBounds(130, 360, 150, 25);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateField.setText(LocalDateTime.now().format(dateFormatter));
        add(dateField);

        JLabel timeLabel = new JLabel("Ώρα:");
        timeLabel.setBounds(290, 360, 50, 25);
        add(timeLabel);

        JTextField timeField = new JTextField();
        timeField.setBounds(340, 360, 90, 25);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        timeField.setText(LocalDateTime.now().format(timeFormatter));
        add(timeField);

        JButton sendButton = new JButton("Αποστολή");
        sendButton.setBackground(Color.decode("#B3FF66"));
        sendButton.setBounds(130, 420, 100, 30);
        add(sendButton);

        JButton cancelButton = new JButton("Ακύρωση");
        cancelButton.setBackground(Color.decode("#FF6666"));
        cancelButton.setBounds(240, 420, 100, 30);
        add(cancelButton);

    searchButton.addActionListener(e -> {
    String searchTerm = toField.getText().trim().toLowerCase();
    recipientListModel.clear();

    if (currentUserType.equals("STUDENT")) {
        for (com.mycompany.baseClasses.Counselor c : com.mycompany.baseClasses.Counselor.sample) {
            String fullName = c.name + " " + c.lastName;
            if (fullName.toLowerCase().contains(searchTerm) || c.getUserName().toLowerCase().contains(searchTerm)) {
                recipientListModel.addElement(c.getUserName() + " - " + fullName + " (counselor)");
            }
        }
        for (com.mycompany.baseClasses.University u : com.mycompany.baseClasses.University.sample) {
            String fullName = u.name;
            if (fullName.toLowerCase().contains(searchTerm) || u.getUserName().toLowerCase().contains(searchTerm)) {
                recipientListModel.addElement(u.getUserName() + " - " + fullName + " (university)");
            }
        }
    } else if (currentUserType.equals("COUNSELOR")) {
        for (com.mycompany.baseClasses.Student s : com.mycompany.baseClasses.Student.sample) {
            String fullName = s.name + " " + s.lastName;
            if (fullName.toLowerCase().contains(searchTerm) || s.getUserName().toLowerCase().contains(searchTerm)) {
                recipientListModel.addElement(s.getUserName() + " - " + fullName + " (student)");
            }
        }
        for (com.mycompany.baseClasses.University u : com.mycompany.baseClasses.University.sample) {
            String fullName = u.name;
            if (fullName.toLowerCase().contains(searchTerm) || u.getUserName().toLowerCase().contains(searchTerm)) {
                recipientListModel.addElement(u.getUserName() + " - " + fullName + " (university)");
            }
        }
    } else if (currentUserType.equals("UNIVERSITY")) {
        for (com.mycompany.baseClasses.Student s : com.mycompany.baseClasses.Student.sample) {
            String fullName = s.name + " " + s.lastName;
            if (fullName.toLowerCase().contains(searchTerm) || s.getUserName().toLowerCase().contains(searchTerm)) {
                recipientListModel.addElement(s.getUserName() + " - " + fullName + " (student)");
            }
        }
        for (com.mycompany.baseClasses.Counselor c : com.mycompany.baseClasses.Counselor.sample) {
            String fullName = c.name + " " + c.lastName;
            if (fullName.toLowerCase().contains(searchTerm) || c.getUserName().toLowerCase().contains(searchTerm)) {
                recipientListModel.addElement(c.getUserName() + " - " + fullName + " (counselor)");
            }
        }
    }

    recipientScrollPane.setVisible(true);
    repaint();
});
    recipientScrollPane.setVisible(true);
    repaint();


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

        sendButton.addActionListener(e -> {
            String recipient = toField.getText().trim();
            String subject = subjectField.getText().trim();
            String content = contentArea.getText().trim();
            String date = dateField.getText().trim();
            String time = timeField.getText().trim();

            if (recipient.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ επίλεξε παραλήπτη.", "Κενό Πεδίο", JOptionPane.WARNING_MESSAGE);
                toField.requestFocus();
                return;
            }

            if (subject.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ γράψε θέμα.", "Κενό Πεδίο", JOptionPane.WARNING_MESSAGE);
                subjectField.requestFocus();
                return;
            }

            if (content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ γράψε περιεχόμενο.", "Κενό Πεδίο", JOptionPane.WARNING_MESSAGE);
                contentArea.requestFocus();
                return;
            }

            if (date.isEmpty() || time.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ συμπλήρωσε ημερομηνία και ώρα.", "Κενό Πεδίο", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String recipientId = parseRecipientId(recipient);
            String recipientName = parseRecipientName(recipient);
            String recipientType = parseRecipientType(recipient);

            if (recipientId == null) {
                JOptionPane.showMessageDialog(this, "Μη έγκυρος παραλήπτης. Παρακαλώ χρησιμοποίησε την αναζήτηση.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String dateTime = date + " " + time;
            if (!isValidDateTime(dateTime)) {
                JOptionPane.showMessageDialog(this, "Μη έγκυρη μορφή ημερομηνίας/ώρας. Χρησιμοποίησε: dd/mm/yyyy hh:mm", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = messageService.sendMessage(
                currentUserId, currentUserName, currentUserType,
                recipientId, recipientName, recipientType,
                subject, content, dateTime
            );

            if (success) {
                JOptionPane.showMessageDialog(this, "Το μήνυμα στάλθηκε επιτυχώς!", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
                if (parentFrame != null) parentFrame.refreshMessages();

                int choice = JOptionPane.showConfirmDialog(this,
                        "Θέλεις να στείλεις άλλο μήνυμα;", "Επιτυχία", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
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
                JOptionPane.showMessageDialog(this, "Σφάλμα κατά την αποστολή του μηνύματος. Προσπάθησε ξανά.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            }
        });

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
        return recipient;
    }

    private String parseRecipientType(String recipient) {
        if (recipient.contains("(") && recipient.contains(")")) {
            String type = recipient.substring(recipient.lastIndexOf("(") + 1, recipient.lastIndexOf(")"));
            return type.toUpperCase();
        }
        return "STUDENT";
    }

    private boolean isValidDateTime(String dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
