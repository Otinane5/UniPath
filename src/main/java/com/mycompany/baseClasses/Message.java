package com.mycompany.baseClasses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Message {
    //ATTRIBUTES
    private static int nextId = 1;
    private int messageId;
    private String senderId;
    private String senderName;
    private String recipientId;
    private String recipientName;
    private String subject;
    private String content;
    private LocalDateTime timestamp;
    private boolean isRead;
    private String senderType; // "STUDENT", "COUNSELOR", "UNIVERSITY"
    private String recipientType;

    // ✅ In-memory storage
    public static List<Message> sample = new ArrayList<>();

    // ✅ Store a new message
    public static void sendMessage(Message message) {
        sample.add(message);
        System.out.println("📩 Μήνυμα αποθηκεύτηκε: " + message.getDisplayTitle());
    }

    // ✅ Get inbox for a user
    public static List<Message> getMessagesForUser(String userId) {
        List<Message> result = new ArrayList<>();
        for (Message msg : sample) {
            if (msg.getRecipientId().equals(userId)) {
                result.add(msg);
            }
        }
        return result;
    }

    public Message(String senderId, String senderName, String senderType,
                   String recipientId, String recipientName, String recipientType,
                   String subject, String content) {
        this.messageId = nextId++;
        this.senderId = senderId;
        this.senderName = senderName;
        this.senderType = senderType;
        this.recipientId = recipientId;
        this.recipientName = recipientName;
        this.recipientType = recipientType;
        this.subject = subject;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.isRead = false;
    }

    public Message(String senderId, String senderName, String senderType,
                   String recipientId, String recipientName, String recipientType,
                   String subject, String content, String dateTime) {
        this.messageId = nextId++;
        this.senderId = senderId;
        this.senderName = senderName;
        this.senderType = senderType;
        this.recipientId = recipientId;
        this.recipientName = recipientName;
        this.recipientType = recipientType;
        this.subject = subject;
        this.content = content;
        this.isRead = false;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            this.timestamp = LocalDateTime.parse(dateTime, formatter);
        } catch (Exception e) {
            this.timestamp = LocalDateTime.now();
        }
    }

    // Getters
    public int getMessageId() { return messageId; }
    public String getSenderId() { return senderId; }
    public String getSenderName() { return senderName; }
    public String getRecipientId() { return recipientId; }
    public String getRecipientName() { return recipientName; }
    public String getSubject() { return subject; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public boolean isRead() { return isRead; }
    public String getSenderType() { return senderType; }
    public String getRecipientType() { return recipientType; }

    // Setters
    public void setRead(boolean read) { this.isRead = read; }

    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return timestamp.format(formatter);
    }

    public String getDisplayTitle() {
        return (isRead ? "" : "[ΝΕΟ] ") + subject + " - από: " + senderName;
    }

    public String getFullContent() {
        return "Από: " + senderName + " (" + senderType.toLowerCase() + ")\n" +
               "Προς: " + recipientName + " (" + recipientType.toLowerCase() + ")\n" +
               "Θέμα: " + subject + "\n" +
               "Ημερομηνία: " + getFormattedTimestamp() + "\n\n" +
               content;
    }
    
    //Method to send Accepting Message to Student
    public void sendAcceptingMessageToStudent(){}
    //Method to send Rejecting Message to Student
    public void sendRejectingMessageToStudent(){}
    
    @Override
    public String toString() {
        return getDisplayTitle();
    }
}