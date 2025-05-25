package com.mycompany.baseClasses;

import java.util.*;
import java.util.stream.Collectors;

public class MessageService {
    private static MessageService instance;
    private List<Message> allMessages;
    private Map<String, List<String>> userContacts; // userId -> list of contact userIds
    
    private MessageService() {
        this.allMessages = new ArrayList<>();
        this.userContacts = new HashMap<>();
        initializeTestMessages();
    }
    
    public static MessageService getInstance() {
        if (instance == null) {
            instance = new MessageService();
        }
        return instance;
    }
    
    // Send a new message
    public boolean sendMessage(String senderId, String senderName, String senderType,
                              String recipientId, String recipientName, String recipientType,
                              String subject, String content) {
        try {
            Message message = new Message(senderId, senderName, senderType,
                                        recipientId, recipientName, recipientType,
                                        subject, content);
            allMessages.add(message);
            
            // Add to contacts if not already present
            addToContacts(senderId, recipientId);
            addToContacts(recipientId, senderId);
            
            return true;
        } catch (Exception e) {
            System.err.println("Error sending message: " + e.getMessage());
            return false;
        }
    }
    
    // Send message with custom date/time
    public boolean sendMessage(String senderId, String senderName, String senderType,
                              String recipientId, String recipientName, String recipientType,
                              String subject, String content, String dateTime) {
        try {
            Message message = new Message(senderId, senderName, senderType,
                                        recipientId, recipientName, recipientType,
                                        subject, content, dateTime);
            allMessages.add(message);
            
            addToContacts(senderId, recipientId);
            addToContacts(recipientId, senderId);
            
            return true;
        } catch (Exception e) {
            System.err.println("Error sending message: " + e.getMessage());
            return false;
        }
    }
    
    // Get all messages for a specific user (received)
    public List<Message> getMessagesForUser(String userId) {
        return allMessages.stream()
                .filter(msg -> msg.getRecipientId().equals(userId))
                .sorted((m1, m2) -> m2.getTimestamp().compareTo(m1.getTimestamp())) // Latest first
                .collect(Collectors.toList());
    }
    
    // Get sent messages by a user
    public List<Message> getSentMessagesByUser(String userId) {
        return allMessages.stream()
                .filter(msg -> msg.getSenderId().equals(userId))
                .sorted((m1, m2) -> m2.getTimestamp().compareTo(m1.getTimestamp()))
                .collect(Collectors.toList());
    }
    
    // Mark message as read
    public void markAsRead(int messageId) {
        allMessages.stream()
                .filter(msg -> msg.getMessageId() == messageId)
                .findFirst()
                .ifPresent(msg -> msg.setRead(true));
    }
    
    // Delete message
    public boolean deleteMessage(int messageId, String userId) {
        return allMessages.removeIf(msg -> 
            msg.getMessageId() == messageId && 
            (msg.getRecipientId().equals(userId) || msg.getSenderId().equals(userId))
        );
    }
    
    // Get contacts for a user
    public List<String> getContactsForUser(String userId) {
        return userContacts.getOrDefault(userId, new ArrayList<>());
    }
    
    // Find users by name or ID (for sending messages)
    public List<UserInfo> findUsers(String searchTerm, String currentUserId) {
        List<UserInfo> results = new ArrayList<>();
        
        // This is a simplified search - in a real application, 
        // you would search through your actual user database
        // For now, we'll return some example users based on contacts
        Set<String> allUserIds = new HashSet<>();
        allMessages.forEach(msg -> {
            allUserIds.add(msg.getSenderId());
            allUserIds.add(msg.getRecipientId());
        });
        
        // Remove current user from results
        allUserIds.remove(currentUserId);
        
        // Convert to UserInfo objects (you'll need to implement this based on your user classes)
        for (String userId : allUserIds) {
            // Get user info from your user management system
            UserInfo userInfo = getUserInfo(userId);
            if (userInfo != null && 
                (userInfo.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                 userInfo.getId().toLowerCase().contains(searchTerm.toLowerCase()))) {
                results.add(userInfo);
            }
        }
        
        return results;
    }
    
    // Helper method to get user info - you'll need to implement this
    private UserInfo getUserInfo(String userId) {
        // This should connect to your actual user management system
        // For now, return a dummy implementation
        if (userId.startsWith("student")) {
            return new UserInfo(userId, "Student " + userId, "STUDENT");
        } else if (userId.startsWith("counselor")) {
            return new UserInfo(userId, "Counselor " + userId, "COUNSELOR");
        } else if (userId.startsWith("university")) {
            return new UserInfo(userId, "University " + userId, "UNIVERSITY");
        }
        return null;
    }
    
    private void addToContacts(String userId, String contactId) {
        userContacts.computeIfAbsent(userId, k -> new ArrayList<>());
        if (!userContacts.get(userId).contains(contactId)) {
            userContacts.get(userId).add(contactId);
        }
    }
    
    // Initialize some test messages
    private void initializeTestMessages() {
        // Add some sample messages for testing
        sendMessage("counselor1", "Σύμβουλος Παπαδόπουλος", "COUNSELOR",
                   "student1", "Μαρία Ιωάννου", "STUDENT",
                   "Καλωσόρισμα", "Καλώς ήρθες στο UniPath! Είμαι εδώ για να σε βοηθήσω με τις επιλογές σου.");
        
        sendMessage("university1", "Πανεπιστήμιο Αθηνών", "UNIVERSITY",
                   "student1", "Μαρία Ιωάννου", "STUDENT",
                   "Νέες Ανακοινώσεις", "Έχουμε νέες ανακοινώσεις για τις εγγραφές. Παρακαλώ ελέγξτε την ιστοσελίδα μας.");
    }
    
    // Inner class for user information
    public static class UserInfo {
        private String id;
        private String name;
        private String type;
        
        public UserInfo(String id, String name, String type) {
            this.id = id;
            this.name = name;
            this.type = type;
        }
        
        public String getId() { return id; }
        public String getName() { return name; }
        public String getType() { return type; }
        
        @Override
        public String toString() {
            return name + " (" + type.toLowerCase() + ")";
        }
    }
}