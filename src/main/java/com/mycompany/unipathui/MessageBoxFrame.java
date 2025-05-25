package com.mycompany.unipathui;

import com.mycompany.baseClasses.Message;
import com.mycompany.baseClasses.MessageService;
import com.mycompany.baseClasses.Unipath;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MessageBoxFrame extends JFrame {
    private DefaultListModel<Message> messageListModel;
    private JList<Message> messageList;
    private JTextArea messageContent;
    private MessageService messageService;
    private String currentUserId;
    private String currentUserName;
    private String currentUserType;

    public MessageBoxFrame() {
        // Get current user info from Unipath
        this.currentUserId = Unipath.currentUser.userName; // Assuming this is the user ID
        this.currentUserName = Unipath.currentUser.userName;
        this.currentUserType = determineUserType(); // You'll need to implement this
        
        this.messageService = MessageService.getInstance();
        
        setTitle("Τα Μηνύματά Μου - " + currentUserName);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Τίτλος
        JLabel title = new JLabel("Λίστα Μηνυμάτων", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(300, 10, 200, 30);
        add(title);

        // Λίστα Μηνυμάτων
        messageListModel = new DefaultListModel<>();
        messageList = new JList<>(messageListModel);
        messageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Custom renderer to show unread messages differently
        messageList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                
                if (value instanceof Message) {
                    Message msg = (Message) value;
                    setText(msg.getDisplayTitle());
                    if (!msg.isRead()) {
                        setFont(getFont().deriveFont(Font.BOLD));
                        setBackground(isSelected ? Color.BLUE : new Color(230, 230, 255));
                    } else {
                        setFont(getFont().deriveFont(Font.PLAIN));
                    }
                }
                return this;
            }
        });
        
        JScrollPane listScrollPane = new JScrollPane(messageList);
        listScrollPane.setBounds(40, 60, 300, 280);
        add(listScrollPane);

        // Περιοχή Περιεχομένου
        messageContent = new JTextArea();
        messageContent.setLineWrap(true);
        messageContent.setWrapStyleWord(true);
        messageContent.setEditable(false);
        messageContent.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane contentScrollPane = new JScrollPane(messageContent);
        contentScrollPane.setBounds(360, 60, 380, 280);
        add(contentScrollPane);

        // Κουμπί Προβολής
        JButton viewButton = new JButton("Προβολή");
        viewButton.setBackground(Color.decode("#E6B3FF"));
        viewButton.setBounds(40, 350, 140, 30);
        add(viewButton);

        // Κουμπί Διαγραφής
        JButton deleteButton = new JButton("Διαγραφή");
        deleteButton.setBackground(Color.decode("#FF6666"));
        deleteButton.setBounds(200, 350, 140, 30);
        add(deleteButton);

        // Κουμπί Νέου Μηνύματος
        JButton newMessageButton = new JButton("Νέο Μήνυμα");
        newMessageButton.setBackground(Color.decode("#B3FF66"));
        newMessageButton.setBounds(360, 350, 180, 30);
        add(newMessageButton);

        // Κουμπί Ανανέωσης
        JButton refreshButton = new JButton("Ανανέωση");
        refreshButton.setBackground(Color.decode("#66B3FF"));
        refreshButton.setBounds(550, 350, 100, 30);
        add(refreshButton);

        // Κουμπί Πίσω
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.decode("#FFCC66"));
        backButton.setBounds(660, 350, 80, 30);
        add(backButton);

        // Load messages
        loadMessages();

        // Selection listener for message list
        messageList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Message selectedMessage = messageList.getSelectedValue();
                if (selectedMessage != null) {
                    messageContent.setText(selectedMessage.getFullContent());
                    // Mark as read when selected
                    if (!selectedMessage.isRead()) {
                        messageService.markAsRead(selectedMessage.getMessageId());
                        selectedMessage.setRead(true);
                        messageList.repaint(); // Refresh display
                    }
                }
            }
        });

        // Λειτουργία: Προβολή Μηνύματος
        viewButton.addActionListener(e -> {
            Message selectedMessage = messageList.getSelectedValue();
            if (selectedMessage != null) {
                new MessageViewFrame(selectedMessage.getSubject(), selectedMessage.getFullContent()).setVisible(true);
                // Mark as read
                if (!selectedMessage.isRead()) {
                    messageService.markAsRead(selectedMessage.getMessageId());
                    selectedMessage.setRead(true);
                    messageList.repaint();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Παρακαλώ επίλεξε ένα μήνυμα για προβολή.", 
                                            "Καμία Επιλογή", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Λειτουργία: Διαγραφή Μηνύματος
        deleteButton.addActionListener(e -> {
            Message selectedMessage = messageList.getSelectedValue();
            if (selectedMessage != null) {
                int result = JOptionPane.showConfirmDialog(this,
                    "Είσαι σίγουρος ότι θέλεις να διαγράψεις αυτό το μήνυμα;",
                    "Επιβεβαίωση Διαγραφής",
                    JOptionPane.YES_NO_OPTION);
                
                if (result == JOptionPane.YES_OPTION) {
                    if (messageService.deleteMessage(selectedMessage.getMessageId(), currentUserId)) {
                        messageListModel.removeElement(selectedMessage);
                        messageContent.setText("");
                        JOptionPane.showMessageDialog(this, "Το μήνυμα διαγράφηκε επιτυχώς.", 
                                                    "Διαγραφή", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Σφάλμα κατά τη διαγραφή του μηνύματος.", 
                                                    "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Παρακαλώ επίλεξε ένα μήνυμα για διαγραφή.", 
                                            "Καμία Επιλογή", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Λειτουργία: Νέο Μήνυμα
        newMessageButton.addActionListener(e -> {
            new NewMessageFrame(this, currentUserId, currentUserName, currentUserType).setVisible(true);
        });

        // Λειτουργία: Ανανέωση
        refreshButton.addActionListener(e -> {
            loadMessages();
            messageContent.setText("");
            JOptionPane.showMessageDialog(this, "Τα μηνύματα ανανεώθηκαν.", 
                                        "Ανανέωση", JOptionPane.INFORMATION_MESSAGE);
        });

        // Λειτουργία: Πίσω
        backButton.addActionListener(e -> dispose());
    }

    private void loadMessages() {
        messageListModel.clear();
        List<Message> messages = messageService.getMessagesForUser(currentUserId);
        for (Message message : messages) {
            messageListModel.addElement(message);
        }
    }

    // This method will be called from NewMessageFrame when a message is sent
    public void refreshMessages() {
        loadMessages();
    }

    // Determine user type based on current user - you'll need to implement this based on your user classes
    private String determineUserType() {
        // This is a simplified implementation - you should check the actual type of Unipath.currentUser
        if (Unipath.currentUser.userName.toLowerCase().contains("student")) {
            return "STUDENT";
        } else if (Unipath.currentUser.userName.toLowerCase().contains("counselor")) {
            return "COUNSELOR";
        } else if (Unipath.currentUser.userName.toLowerCase().contains("university")) {
            return "UNIVERSITY";
        }
        return "STUDENT"; // Default fallback
    }
}