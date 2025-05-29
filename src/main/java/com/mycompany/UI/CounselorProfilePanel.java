package com.mycompany.unipathui;

import com.mycompany.baseClasses.Counselor;
import com.mycompany.baseClasses.Unipath;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CounselorProfilePanel extends JPanel {
    //ATTRIBUTES
    public static Counselor CounselorToDisplay = new Counselor(); // This needs to be set before use
    private final JLabel nameValueLabel, lastNameValueLabel, emailValueLabel, phoneValueLabel;
    private final JTextArea bioTextArea;
    //CONSTRUCTOR
    public CounselorProfilePanel(Runnable onBackToMainMenu, Runnable onEditProfile) {
        setLayout(new BorderLayout(10, 10));

        // Title label
        JLabel titleLabel = new JLabel("Προφίλ Συμβούλου", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        // Central Panel with info
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Helpful method for label and value
        int row = 0;
        formPanel.add(makeLabel("Όνομα:"), makeGbc(0, row));
        //formPanel.add(makeValueLabel(CounselorToDisplay.name), makeGbc(1, row++));
        nameValueLabel = makeValueLabel(CounselorToDisplay.name);
        formPanel.add(nameValueLabel, makeGbc(1, row++));
        
        formPanel.add(makeLabel("Επώνυμο:"), makeGbc(0, row));
        //formPanel.add(makeValueLabel(CounselorToDisplay.lastName), makeGbc(1, row++));
        lastNameValueLabel = makeValueLabel(CounselorToDisplay.lastName);
        formPanel.add(lastNameValueLabel, makeGbc(1, row++));
        
        formPanel.add(makeLabel("Email:"), makeGbc(0, row));
        //formPanel.add(makeValueLabel(CounselorToDisplay.email), makeGbc(1, row++));
        emailValueLabel = makeValueLabel(CounselorToDisplay.email);
        formPanel.add(emailValueLabel, makeGbc(1, row++));

        formPanel.add(makeLabel("Τηλέφωνο:"), makeGbc(0, row));
        //formPanel.add(makeValueLabel(CounselorToDisplay.phoneNum), makeGbc(1, row++));
        phoneValueLabel = makeValueLabel(CounselorToDisplay.phoneNum);
        formPanel.add(phoneValueLabel, makeGbc(1, row++));

        // Bio
        formPanel.add(makeLabel("Περιγραφή (Bio):"), makeGbc(0, row));
        bioTextArea = new JTextArea(CounselorToDisplay.bio);
        bioTextArea.setLineWrap(true);
        bioTextArea.setWrapStyleWord(true);
        bioTextArea.setEditable(false);
        bioTextArea.setFont(new Font("Arial", Font.PLAIN, 13));
        
        JScrollPane bioScroll = new JScrollPane(bioTextArea);
        bioScroll.setPreferredSize(new Dimension(300, 80));
        formPanel.add(bioScroll, makeGbc(1, row++));
        
        // Add reviews section only if the logged-in user is the counselor
        if (Unipath.currentUser.userName.equals(CounselorToDisplay.userName)) {
            // Display average review rating
            formPanel.add(new JLabel("Μέσος Όρος Αξιολόγησης:"), makeGbc(0, row));
            formPanel.add(createAverageRatingPanel(CounselorToDisplay.reviews), makeGbc(1, row++));

            // Display individual reviews as a scrollable list
            formPanel.add(new JLabel("Αξιολογήσεις:"), makeGbc(0, row));
            JScrollPane reviewsScrollPane = new JScrollPane(createIndividualReviewsPanel(CounselorToDisplay.reviews));
            reviewsScrollPane.setPreferredSize(new Dimension(300, 150));  // Adjust the size as needed
            formPanel.add(reviewsScrollPane, makeGbc(1, row++));

            // Add "Edit Profile" button if it's the counselor's profile
            JButton editButton = new JButton("Επεξεργασία Προφίλ");
            editButton.setBackground(new Color(173, 216, 230));
            editButton.setFocusPainted(false);
            editButton.setFont(new Font("Arial", Font.BOLD, 13));
            editButton.addActionListener(e -> onEditProfile.run());
            gbc.gridwidth = 2;
            gbc.gridx = 0;
            gbc.gridy = row++;
            
            formPanel.add(editButton, gbc);
        }

        add(formPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton backToMain = new JButton("Αρχική Σελίδα");
        backToMain.setBackground(Color.decode("#B3FF66"));
        backToMain.addActionListener(e -> onBackToMainMenu.run());
        
        JButton backButton = new JButton("Πίσω");
        backButton.setBackground(Color.decode("#FFCC66"));
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
    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        return label;
    }
    private JLabel makeValueLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }
    private GridBagConstraints makeGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }
    public void refresh() {
        // Απλά ενημερώνουμε τα labels με τα νέα δεδομένα
        nameValueLabel.setText(CounselorToDisplay.name);
        lastNameValueLabel.setText(CounselorToDisplay.lastName);
        emailValueLabel.setText(CounselorToDisplay.email);
        phoneValueLabel.setText(CounselorToDisplay.phoneNum);
        bioTextArea.setText(CounselorToDisplay.bio);

        // Αν έχεις δυναμικά στοιχεία (π.χ. reviews), θα πρέπει να τα ανανεώσεις επίσης εδώ
        // Π.χ. αν τα reviews είναι σε ξεχωριστό panel, καθάρισε και ξαναπρόσθεσε τα components.

        revalidate();
        repaint();
    }
}
