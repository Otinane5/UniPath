package com.mycompany.unipathui;

import com.mycompany.baseClasses.Counselor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewScreenCounselor extends JFrame {

    public ViewScreenCounselor() {
        setTitle("Προφίλ Συμβούλου");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("UniPath", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(300, 10, 200, 30);
        add(title);

        JButton messagesButton = new JButton("Τα μηνύματά μου");
        ImageIcon envelopeIcon = new ImageIcon(getClass().getResource("/icons/envelope.png"));
        Image envelopeImage = envelopeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        messagesButton.setIcon(new ImageIcon(envelopeImage));
        messagesButton.setBounds(630, 10, 150, 30);
        add(messagesButton);
        messagesButton.addActionListener(e -> new MessageBoxFrame().setVisible(true));

        JLabel advisorTitle = new JLabel("Διαθέσιμοι Σύμβουλοι:");
        advisorTitle.setFont(new Font("Arial", Font.BOLD, 16));
        advisorTitle.setBounds(40, 80, 300, 25);
        add(advisorTitle);

        JPanel advisorsPanel = new JPanel();
        advisorsPanel.setLayout(new GridLayout(0, 1, 10, 10));
        advisorsPanel.setBackground(Color.LIGHT_GRAY);

        List<Counselor> counselors = Counselor.sample; 

        for (Counselor counselor : counselors) {
            JPanel row = new JPanel(new GridLayout(3, 2, 5, 5));
            row.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel name = new JLabel(counselor.name + " " + counselor.lastName);
            JLabel phone = new JLabel(counselor.phoneNum);
            JButton profileBtn = new JButton("Προβολή προφίλ");
            JButton appointmentBtn = new JButton("Ραντεβού");
            appointmentBtn.addActionListener(e -> {
             String studentName = "Όνομα Μαθητή"; 
            String counselorFullName = counselor.name + " " + counselor.lastName;
            new CounselorFormScreen(studentName, counselorFullName).setVisible(true);
            });

            profileBtn.setBackground(Color.decode("#E6B3FF"));
            appointmentBtn.setBackground(Color.GREEN);

            JLabel reviewLabel = new JLabel("Βαθμολόγηση:");
            SpinnerModel model = new SpinnerNumberModel(0, 0, 5, 1);
            JSpinner reviewSpinner = new JSpinner(model);

            JButton submitReviewBtn = new JButton("Υποβολή");
            submitReviewBtn.addActionListener(e -> {
                System.out.println("Review submitted for " + counselor.userName);
            });

            // Assign selected counselor before opening the profile
    profileBtn.addActionListener(e -> {
    CounselorProfilePanel.CounselorToDisplay = counselor;

    // Create a JFrame to hold the profile panel
    JFrame profileFrame = new JFrame("Προφίλ Συμβούλου");
    profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    profileFrame.setSize(500, 400);
    profileFrame.setLocationRelativeTo(null);

    // Provide the required callbacks to the panel
    CounselorProfilePanel panel = new CounselorProfilePanel(
        () -> profileFrame.dispose(), // onBackToMainMenu
        () -> {
            profileFrame.dispose();
            // You can add your edit screen here
            JOptionPane.showMessageDialog(null, "Επεξεργασία προφίλ δεν υλοποιήθηκε ακόμα.");
        }
    );

    profileFrame.setContentPane(panel);
    profileFrame.setVisible(true);
});

            row.add(name);
            row.add(profileBtn);
            row.add(phone);
            row.add(appointmentBtn);
            row.add(reviewLabel);

            JPanel reviewPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            reviewPanel.add(reviewSpinner);
            reviewPanel.add(submitReviewBtn);
            row.add(reviewPanel);

            advisorsPanel.add(row);
        }

        JScrollPane scrollPane = new JScrollPane(advisorsPanel);
        scrollPane.setBounds(40, 110, 720, 250);
        add(scrollPane);

        JButton logoutBtn = new JButton("Αποσύνδεση");
        logoutBtn.setBackground(Color.decode("#FF6666"));
        logoutBtn.setBounds(40, 400, 180, 30);
        add(logoutBtn);

        JButton homeBtn = new JButton("Αρχική Σελίδα");
        homeBtn.setBackground(Color.decode("#B3FF66"));
        homeBtn.setBounds(300, 400, 180, 30);
        add(homeBtn);

        JButton backBtn = new JButton("Πίσω");
        backBtn.setBackground(Color.decode("#FFCC66"));
        backBtn.setBounds(580, 400, 180, 30);
        add(backBtn);

        JButton suggestionsBtn = new JButton("Προτάσεις Για Εσένα");
        suggestionsBtn.setBackground(Color.decode("#66CCFF"));
        suggestionsBtn.setBounds(300, 360, 180, 30);
        add(suggestionsBtn);

        suggestionsBtn.addActionListener(e -> System.out.println("pressed!"));

        logoutBtn.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this, "Είστε σίγουρος ότι θέλετε να αποσυνδεθείτε;", "Επιβεβαίωση", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });

        homeBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        backBtn.addActionListener(e -> {
            dispose();
            new StudentMenuFrame().setVisible(true);
        });
    }
}
