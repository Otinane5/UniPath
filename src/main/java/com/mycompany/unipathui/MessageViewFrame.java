package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageViewFrame extends JFrame {
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
        JTextArea textArea = new JTextArea(content);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        textArea.setMargin(new Insets(10, 10, 10, 10));
        
        // Set background color for better readability
        textArea.setBackground(new Color(250, 250, 250));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Περιεχόμενο Μηνύματος"));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton printButton = new JButton("Εκτύπωση");
        printButton.setBackground(Color.decode("#E6B3FF"));
        printButton.setPreferredSize(new Dimension(100, 30));
        
        JButton copyButton = new JButton("Αντιγραφή");
        copyButton.setBackground(Color.decode("#B3FFE6"));
        copyButton.setPreferredSize(new Dimension(100, 30));
        
        JButton closeButton = new JButton("Κλείσιμο");
        closeButton.setBackground(Color.decode("#FFCC66"));
        closeButton.setPreferredSize(new Dimension(100, 30));
        
        bottomPanel.add(printButton);
        bottomPanel.add(copyButton);
        bottomPanel.add(closeButton);
        
        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.print();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(MessageViewFrame.this, 
                        "Σφάλμα κατά την εκτύπωση: " + ex.getMessage(), 
                        "Σφάλμα Εκτύπωσης", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.selectAll();
                textArea.copy();
                textArea.setSelectionStart(0);
                textArea.setSelectionEnd(0);
                JOptionPane.showMessageDialog(MessageViewFrame.this, 
                    "Το περιεχόμενο αντιγράφηκε στο clipboard!", 
                    "Αντιγραφή", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Make window resizable and set minimum size
        setMinimumSize(new Dimension(400, 300));
        setResizable(true);
    }
}