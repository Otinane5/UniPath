package com.mycompany.unipathui;

import com.mycompany.baseClasses.Counselor;
import com.mycompany.baseClasses.Unipath;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CounselorProfilePanel extends JPanel {

    public static Counselor CounselorToDisplay = new Counselor(); // Set externally before use

    public CounselorProfilePanel(Runnable onBackToMainMenu, Runnable onEditProfile) {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // ===== Title =====
        JLabel titleLabel = new JLabel("Προφίλ Συμβούλου", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel, BorderLayout.NORTH);

        // ===== Main Content Panel =====
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        int row = 0;

        // Basic Info
        contentPanel.add(makeLabel("Όνομα:"), makeGbc(0, row));
        contentPanel.add(makeValueLabel(CounselorToDisplay.name), makeGbc(1, row++));
        contentPanel.add(makeLabel("Επώνυμο:"), makeGbc(0, row));
        contentPanel.add(makeValueLabel(CounselorToDisplay.lastName), makeGbc(1, row++));
        contentPanel.add(makeLabel("Email:"), makeGbc(0, row));
        contentPanel.add(makeValueLabel(CounselorToDisplay.email), makeGbc(1, row++));
        contentPanel.add(makeLabel("Τηλέφωνο:"), makeGbc(0, row));
        contentPanel.add(makeValueLabel(CounselorToDisplay.phoneNum), makeGbc(1, row++));

        // Bio Section
        contentPanel.add(makeLabel("Περιγραφή (Bio):"), makeGbc(0, row));
        JTextArea bioArea = new JTextArea(CounselorToDisplay.bio);
        bioArea.setLineWrap(true);
        bioArea.setWrapStyleWord(true);
        bioArea.setEditable(false);
        bioArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane bioScroll = new JScrollPane(bioArea);
        bioScroll.setPreferredSize(new Dimension(350, 80));
        contentPanel.add(bioScroll, makeGbc(1, row++));

        // Show ratings if it's the counselor themselves
        if (Unipath.currentUser.userName.equals(CounselorToDisplay.userName)) {
            // Average Rating
            contentPanel.add(makeLabel("Μέσος Όρος Αξιολόγησης:"), makeGbc(0, row));
            contentPanel.add(createAverageRatingPanel(CounselorToDisplay.reviews), makeGbc(1, row++));

            // Individual Reviews
            contentPanel.add(makeLabel("Αξιολογήσεις:"), makeGbc(0, row));
            JScrollPane reviewsScrollPane = new JScrollPane(createIndividualReviewsPanel(CounselorToDisplay.reviews));
            reviewsScrollPane.setPreferredSize(new Dimension(650, 650));
            contentPanel.add(reviewsScrollPane, makeGbc(1, row++));

            // Edit Button
            JButton editProfileButton = new JButton("Επεξεργασία Προφίλ");
            styleButton(editProfileButton, new Color(173, 216, 230));
            editProfileButton.addActionListener(e -> onEditProfile.run());

            gbc.gridwidth = 2;
            gbc.gridx = 0;
            gbc.gridy = row++;
            gbc.anchor = GridBagConstraints.CENTER;
            contentPanel.add(editProfileButton, gbc);
        }

        add(contentPanel, BorderLayout.CENTER);

        // ===== Button Panel =====
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton backToMain = new JButton("Αρχική Σελίδα");
        styleButton(backToMain, Color.decode("#B3FF66"));
        backToMain.addActionListener(e -> onBackToMainMenu.run());

        JButton backButton = new JButton("Πίσω");
        styleButton(backButton, Color.decode("#FFCC66"));
        backButton.addActionListener(e -> onBackToMainMenu.run());

        bottomPanel.add(backToMain);
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
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

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 13));
    }

    private JPanel createAverageRatingPanel(List<Integer> reviews) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        double avg = reviews.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        JLabel avgLabel = new JLabel(String.format("Μέσος Όρος: %.1f", avg));
        avgLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JProgressBar ratingBar = new JProgressBar(0, 100);
        ratingBar.setValue((int)(20*avg));
        ratingBar.setStringPainted(true);
        ratingBar.setForeground(new Color(34, 139, 34));
        ratingBar.setPreferredSize(new Dimension(200, 20));

        panel.add(avgLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(ratingBar);
        return panel;
    }

    private JPanel createIndividualReviewsPanel(List<Integer> reviews) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        for (int i = 0; i < reviews.size(); i++) {
            int rating = reviews.get(i);
            String stars = "*".repeat(rating) + "☆".repeat(5 - rating);
            JLabel reviewLabel = new JLabel("Αξιολόγηση " + (i + 1) + ": " + stars);
            reviewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
            panel.add(reviewLabel);
        }

        return panel;
    }
}
