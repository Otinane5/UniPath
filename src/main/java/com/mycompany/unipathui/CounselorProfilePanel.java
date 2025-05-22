package com.mycompany.unipathui;

import com.mycompany.baseClasses.Counselor;
import com.mycompany.baseClasses.Unipath;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CounselorProfilePanel extends JPanel {
    public static Counselor CounselorToDisplay = new Counselor(); // This needs to be set before use

    public CounselorProfilePanel(Runnable onBackToMainMenu, Runnable onEditProfile) {
        setLayout(new BorderLayout(10, 10));

        // Title label
        JLabel titleLabel = new JLabel("Προφίλ Συμβούλου", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel to display counselor information
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10)); // Adjusted for 7 rows
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        formPanel.add(new JLabel("Όνομα:"));
        formPanel.add(new JLabel(CounselorToDisplay.name));
        formPanel.add(new JLabel("Επώνυμο:"));
        formPanel.add(new JLabel(CounselorToDisplay.lastName));
        formPanel.add(new JLabel("Email:"));
        formPanel.add(new JLabel(CounselorToDisplay.email));
        formPanel.add(new JLabel("Τηλέφωνο:"));
        formPanel.add(new JLabel(CounselorToDisplay.phoneNum));
        formPanel.add(new JLabel("Περιγραφή (Bio):"));
        JTextArea bio = new JTextArea(CounselorToDisplay.bio);
        bio.setLineWrap(true);
        bio.setWrapStyleWord(true);
        bio.setEditable(false);
        formPanel.add(new JScrollPane(bio));

        // Add reviews section only if the logged-in user is the counselor
        if (Unipath.currentUser.userName.equals(CounselorToDisplay.userName)) {
            // Display average review rating
            formPanel.add(new JLabel("Μέσος Όρος Αξιολόγησης:"));
            formPanel.add(createAverageRatingPanel(CounselorToDisplay.reviews));

            // Display individual reviews as a scrollable list
            formPanel.add(new JLabel("Αξιολογήσεις:"));
            JScrollPane reviewsScrollPane = new JScrollPane(createIndividualReviewsPanel(CounselorToDisplay.reviews));
            reviewsScrollPane.setPreferredSize(new Dimension(300, 150));  // Adjust the size as needed
            formPanel.add(reviewsScrollPane);

            // Add "Edit Profile" button if it's the counselor's profile
            JButton editButton = new JButton("Επεξεργασία Προφίλ");
            editButton.setBackground(new Color(173, 216, 230));
            editButton.addActionListener(e -> onEditProfile.run());
            formPanel.add(editButton);
        }

        add(formPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton backToMain = new JButton("Πίσω Στο Αρχικό Μενού");
        backToMain.setBackground(Color.decode("#B3FF66"));
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.decode("#FFCC66"));

        // Back buttons
        backToMain.addActionListener(e -> onBackToMainMenu.run());
        backButton.addActionListener(e -> onBackToMainMenu.run());

        buttonPanel.add(backToMain);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Create a progress bar to show the average review score
    private JPanel createAverageRatingPanel(List<Integer> reviews) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Calculate the average rating
        double averageRating = reviews.stream().mapToInt(Integer::intValue).average().orElse(0);
        
        // Create a progress bar to visually show the rating
        JProgressBar progressBar = new JProgressBar(0, 5);
        progressBar.setValue((int) averageRating); // Set the average rating as the value
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.GREEN);
        progressBar.setPreferredSize(new Dimension(200, 25));

        // Add a label with the average rating
        JLabel ratingLabel = new JLabel(String.format("Μέσος Όρος: %.1f", averageRating));

        // Add components to the panel
        panel.add(ratingLabel);
        panel.add(progressBar);

        return panel;
    }

    // Create a panel to show individual reviews as stars or numbers
    private JPanel createIndividualReviewsPanel(List<Integer> reviews) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (Integer review : reviews) {
            // Create a label for each review (can be stars or numbers)
            panel.add(new JLabel("Αξιολόγηση: " + "★".repeat(review) + "☆".repeat(5 - review)));
        }

        return panel;
    }
}
