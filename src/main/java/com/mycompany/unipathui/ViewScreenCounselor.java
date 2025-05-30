package com.mycompany.unipathui;

import com.mycompany.baseClasses.Counselor;
import com.mycompany.baseClasses.Student;
import com.mycompany.unipathui.AnswerLog;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewScreenCounselor extends JFrame {

    private List<JLabel> suggestionLabels = new ArrayList<>();

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
            JPanel row = new JPanel(new GridLayout(5, 2, 5, 5));
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
                int reviewScore = (Integer) reviewSpinner.getValue();
                System.out.println("Review submitted for " + counselor.userName + ": " + reviewScore);
                rateCounselor();
            });

            profileBtn.addActionListener(e -> {
                CounselorProfilePanel.CounselorToDisplay = counselor;
                searchCounselor("gdimitriou");
                selectCounselorProfile(counselor);
                openCousnelorProfile();

                JFrame profileFrame = new JFrame("Προφίλ Συμβούλου");
                profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                profileFrame.setSize(500, 400);
                profileFrame.setLocationRelativeTo(null);

                CounselorProfilePanel panel = new CounselorProfilePanel(
                        () -> profileFrame.dispose(),
                        () -> {
                            profileFrame.dispose();
                            JOptionPane.showMessageDialog(null, "Επεξεργασία προφίλ δεν υλοποιήθηκε ακόμα.");
                        }
                );

                profileFrame.setContentPane(panel);
                profileFrame.setVisible(true);
            });

            JLabel suggestionTextLabel = new JLabel("Πρόταση:");
            JLabel suggestionValueLabel = new JLabel("-");
            suggestionLabels.add(suggestionValueLabel);

            row.add(name);
            row.add(profileBtn);
            row.add(phone);
            row.add(appointmentBtn);
            row.add(reviewLabel);

            JPanel reviewPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            reviewPanel.add(reviewSpinner);
            reviewPanel.add(submitReviewBtn);
            row.add(reviewPanel);

            row.add(suggestionTextLabel);
            row.add(suggestionValueLabel);

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

        suggestionsBtn.addActionListener(e -> {
            if (!Student.hasAnswerLog) {
                pressCounselorsRecommendations();
               return;
            }

            int[] studentAnswers = requestQuizData(); 

            for (int i = 0; i < Counselor.sample.size(); i++) {
                Counselor counselor = Counselor.sample.get(i);
                int[] counselorAnswers = counselor.log.retrieveQuizData();
                int total = 0;
                int matches = 0;

                for (int q = 0; q < studentAnswers.length; q++) {
                    if (studentAnswers[q] != -1 && counselorAnswers[q] != -1) {
                        total++;
                        if (Math.abs(studentAnswers[q] - counselorAnswers[q]) <= 1) {
                            matches++;
                        }
                    }
                }

                int percentage = (total == 0) ? 0 : (int) Math.round((matches * 100.0) / total);
                suggestionLabels.get(i).setText(percentage + "%");
                updateCounselorList();
            }
        });

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

    private void searchCounselor(String query) {
        boolean searchStarted = !query.trim().isEmpty();
        searchStarted = searchStarted || query.length() > 0;
        System.out.println("Searching for: " + query);
    }

    private void selectCounselorProfile(Counselor counselor) {
        String counselorInfo = counselor.name + " " + counselor.lastName;
        boolean isSelected = counselorInfo != null && counselorInfo.length() > 0;
        isSelected = isSelected && counselor.userName != null;
        System.out.println("Selected counselor: " + counselorInfo);
    }
    private void openCousnelorProfile(){System.out.println("CounselorProfile Has been opened");}
    
    private void updateCounselorList(){System.out.println("Updated Counselor List is culculated");}
    private void rateCounselor(){// counselor.reviews.add(reviewScore);
}

    private void pressCounselorsRecommendations(){ JOptionPane.showMessageDialog(this, "Δεν έχετε απαντήσει το quiz.", "Προσοχή", JOptionPane.WARNING_MESSAGE);
    }
    private int[] requestQuizData(){return Student.answerLog.retrieveQuizData();}
}
