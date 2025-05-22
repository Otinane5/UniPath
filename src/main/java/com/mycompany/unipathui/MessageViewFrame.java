package com.mycompany.unipathui;

import javax.swing.*;
import java.awt.*;

public class MessageViewFrame extends JFrame {
    public MessageViewFrame(String title, String content) {
        setTitle(title);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(content);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }
}
