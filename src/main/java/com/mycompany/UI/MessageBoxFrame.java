package com.mycompany.unipathui;

import com.mycompany.baseClasses.Message;
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
        this.currentUserId = Unipath.currentUser.userName;
        this.currentUserName = Unipath.currentUser.userName;
        this.currentUserType = determineUserType();

        this.messageService = MessageService.getInstance();

        setTitle("Τα Μηνύματά Μου - " + currentUserName);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("Λίστα Μηνυμάτων", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(300, 10, 200, 30);
        add(title);

        messageListModel = new DefaultListModel<>();
        messageList = new JList<>(messageListModel);
        messageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        messageList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Message msg) {
                    setText(msg.getDisplayTitle());
                    setFont(getFont().deriveFont(msg.isRead() ? Font.PLAIN : Font.BOLD));
                    if (!msg.isRead()) {
                        setBackground(isSelected ? Color.BLUE : new Color(230, 230, 255));
                    }
                }
                return this;
            }
        });

        JScrollPane listScrollPane = new JScrollPane(messageList);
        listScrollPane.setBounds(40, 60, 300, 280);
        add(listScrollPane);

        messageContent = new JTextArea();
        messageContent.setLineWrap(true);
        messageContent.setWrapStyleWord(true);
        messageContent.setEditable(false);
        messageContent.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane contentScrollPane = new JScrollPane(messageContent);
        contentScrollPane.setBounds(360, 60, 380, 280);
        add(contentScrollPane);

        JButton viewButton = new JButton("Προβολή");
        viewButton.setBackground(Color.decode("#E6B3FF"));
        viewButton.setBounds(40, 350, 140, 30);
        add(viewButton);

        JButton newMessageButton = new JButton("Νέο Μήνυμα");
        newMessageButton.setBackground(Color.decode("#B3FF66"));
        newMessageButton.setBounds(200, 350, 140, 30);
        add(newMessageButton);

        JButton refreshButton = new JButton("Ανανέωση");
        refreshButton.setBackground(Color.decode("#66B3FF"));
        refreshButton.setBounds(360, 350, 140, 30);
        add(refreshButton);

        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.decode("#FFCC66"));
        backButton.setBounds(520, 350, 140, 30);
        add(backButton);

        loadMessages();

        messageList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Message selectedMessage = messageList.getSelectedValue();
                if (selectedMessage != null) {
                    messageContent.setText(selectedMessage.getFullContent());
                    if (!selectedMessage.isRead()) {
                        selectedMessage.setRead(true); // mark as read in memory
                        messageList.repaint();
                    }
                }
            }
        });

        viewButton.addActionListener(e -> {
            Message selectedMessage = messageList.getSelectedValue();
            if (selectedMessage != null) {
                JOptionPane.showMessageDialog(this,
                        selectedMessage.getFullContent(),
                        selectedMessage.getSubject(),
                        JOptionPane.INFORMATION_MESSAGE);
                selectedMessage.setRead(true);
                messageList.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Παρακαλώ επίλεξε ένα μήνυμα για προβολή.");
            }
        });

        newMessageButton.addActionListener(e ->
            new NewMessageFrame(this, currentUserId, currentUserName, currentUserType).setVisible(true)
        );

        refreshButton.addActionListener(e -> {
            loadMessages();
            messageContent.setText("");
        });

        backButton.addActionListener(e -> dispose());
    }

    private void loadMessages() {
        messageListModel.clear();
        List<Message> messages = messageService.getMessagesForUser(currentUserId);
        for (Message message : messages) {
            messageListModel.addElement(message);
        }
    }

    public void refreshMessages() {
        loadMessages();
    }

    private String determineUserType() {
        if (Unipath.currentUser.userName.toLowerCase().contains("student")) return "STUDENT";
        if (Unipath.currentUser.userName.toLowerCase().contains("counselor")) return "COUNSELOR";
        if (Unipath.currentUser.userName.toLowerCase().contains("university")) return "UNIVERSITY";
        return "STUDENT";
    }
}