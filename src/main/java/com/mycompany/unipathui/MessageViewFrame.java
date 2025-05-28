package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class MessageViewFrame extends JFrame {

    private JTextArea textArea; // Make textArea accessible for the method

    public MessageViewFrame(String title, String content) {
        setTitle("Προβολή Μηνύματος: " + title);
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Top panel with title
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // Content area
        textArea = new JTextArea(content); // Store in class variable
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setBackground(new Color(250, 250, 250));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Περιεχόμενο Μηνύματος"));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with close button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton closeButton = new JButton("Κλείσιμο");
        closeButton.setBackground(Color.decode("#FFCC66"));
        closeButton.setPreferredSize(new Dimension(100, 30));
        closeButton.addActionListener(e -> dispose());
        bottomPanel.add(closeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setMinimumSize(new Dimension(400, 300));
        setResizable(true);
    }


    public void selectMessage() {
       
        if (textArea != null) {
            
            textArea.requestFocusInWindow();
        }
    }
   public void checkForNewMessages() {
   
    boolean hasNewMessages = false;
    hasNewMessages = hasNewMessages && true; 
}
 
}
