package com.mycompany.unipathui;

import com.mycompany.baseClasses.Message;
import java.util.List;

public class MessageService {
    private static MessageService instance;

    private MessageService() {}

    public static MessageService getInstance() {
        if (instance == null) {
            instance = new MessageService();
        }
        return instance;
    }

    // Create and send a message — stores it in memory (Message.sample)
    public boolean sendMessage(
        String senderId, String senderName, String senderType,
        String recipientId, String recipientName, String recipientType,
        String subject, String content, String dateTime
    ) {
        try {
            Message msg = new Message(
                senderId, senderName, senderType,
                recipientId, recipientName, recipientType,
                subject, content, dateTime
            );
            Message.sendMessage(msg); // Save to in-memory list
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get inbox for a user
    public List<Message> getMessagesForUser(String userId) {
        return Message.getMessagesForUser(userId);
    }

    // Optional: mark message as read
    public void markMessageAsRead(Message msg) {
        msg.setRead(true);
    }
}
