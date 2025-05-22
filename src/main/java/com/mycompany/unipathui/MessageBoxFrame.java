
package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class MessageBoxFrame extends JFrame {
    private DefaultListModel<String> messageListModel;
    private JList<String> messageList;
    private JTextArea messageContent;

    public MessageBoxFrame() {
        setTitle("Τα Μηνύματά Μου");
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
        JScrollPane listScrollPane = new JScrollPane(messageList);
        listScrollPane.setBounds(40, 60, 300, 280);
        add(listScrollPane);

        // Περιοχή Περιεχομένου
        messageContent = new JTextArea();
        messageContent.setLineWrap(true);
        messageContent.setWrapStyleWord(true);
        messageContent.setEditable(false);
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

        // Κουμπί Πίσω
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.decode("#FFCC66"));
        backButton.setBounds(580, 350, 160, 30);
        add(backButton);

        // Dummy μηνύματα για δοκιμή
        messageListModel.addElement("Μήνυμα 1: Καλωσόρισμα");
        messageListModel.addElement("Μήνυμα 2: Υπενθύμιση Ραντεβού");

        // Λειτουργία: Προβολή Μηνύματος
        viewButton.addActionListener(e -> {
        int index = messageList.getSelectedIndex();
        if (index != -1) {
        String selected = messageListModel.getElementAt(index);
        String dummyContent = "Περιεχόμενο για: " + selected; // αντικατάστησέ το με πραγματικό περιεχόμενο αν έχεις
        new MessageViewFrame(selected, dummyContent).setVisible(true);
         }
           });

        // Λειτουργία: Διαγραφή Μηνύματος
        deleteButton.addActionListener(e -> {
            int index = messageList.getSelectedIndex();
            if (index != -1) {
                messageListModel.remove(index);
                messageContent.setText("");
            }
        });

        // Λειτουργία: Νέο Μήνυμα
        newMessageButton.addActionListener(e -> {
            new NewMessageFrame(this).setVisible(true);
        });

        // Λειτουργία: Πίσω
        backButton.addActionListener(e -> dispose());
    }

    // Προσθήκη μηνύματος από NewMessageFrame
    public void addMessage(String title, String content) {
        messageListModel.addElement(title);
        messageContent.setText(content);
    }
}