package com.mycompany.baseClasses;

import com.mycompany.unipathui.AnswerLog;
import java.util.ArrayList;
import java.util.List;

public class Counselor extends User {
    public List<Appointment> appointments;
    public String name;
    public String lastName;
    public String email;
    public String phoneNum;
    public String bio;
    public AnswerLog log;
    public List<Integer> reviews = new ArrayList<>();

    public static List<Counselor> sample = new ArrayList<>();

    public static AnswerLog createAnswerLog(int[] answerValues) {
            AnswerLog log = new AnswerLog();
            for (int i = 0; i < answerValues.length; i++) {
                log.setAnswer(i, answerValues[i]);
            }
            return log;
        }
    
    public static void init() {
        

        // Counselor 1
        Counselor c1 = new Counselor("s2", 2, "sampleCounselor");
        c1.name = "Μαρία";
        c1.lastName = "Παπαδοπούλου";
        c1.email = "maria.pap@example.com";
        c1.phoneNum = "2101234567";
        c1.bio = "Ειδική στην επαγγελματική καθοδήγηση.";
        c1.reviews = List.of(5, 4, 5, 5);
        c1.log = createAnswerLog(new int[]{
            1, 0, 2, 1, 3, 4, 0, 2, 1, 3, 4, 2, 1, 0, 2, 1, 3, 4
        });

        // Counselor 2
        Counselor c2 = new Counselor("pass2", 2, "gdimtriou");
        c2.name = "Γιάννης";
        c2.lastName = "Δημητρίου";
        c2.email = "giannis.d@example.com";
        c2.phoneNum = "2107654321";
        c2.bio = "Σύμβουλος με εμπειρία σε πανεπιστημιακές σπουδές.";
        c2.reviews = List.of(4, 3, 4, 5);
        c2.log = createAnswerLog(new int[]{
            2, 3, 1, 0, 4, 3, 2, 1, 0, 3, 2, 1, 4, 0, 1, 2, 3, 0
        });

        // Counselor 3
        Counselor c3 = new Counselor("pass3", 2, "akaragianni");
        c3.name = "Άννα";
        c3.lastName = "Καραγιάννη";
        c3.email = "anna.k@example.com";
        c3.phoneNum = "2109988776";
        c3.bio = "Βοηθώ μαθητές να βρουν το σωστό μονοπάτι.";
        c3.reviews = List.of(5, 4, 5);
        c3.log = createAnswerLog(new int[]{
            0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 0, 1, 2, 3, 4, 0, 1, 2
        });

        // Counselor 4
        Counselor c4 = new Counselor("pass4", 2, "nvasileiou");
        c4.name = "Νίκος";
        c4.lastName = "Βασιλείου";
        c4.email = "nikos.v@example.com";
        c4.phoneNum = "2103332211";
        c4.bio = "Εξειδίκευση σε επιλογή σχολών.";
        c4.reviews = List.of(4, 4, 3, 5);
        c4.log = createAnswerLog(new int[]{
            3, 2, 1, 0, 1, 2, 3, 4, 2, 0, 1, 3, 2, 1, 0, 4, 3, 2
        });

        // Add counselors to the sample list
        sample.add(c1);
        sample.add(c2);
        sample.add(c3);
        sample.add(c4);
        User.sample.add(c1);
        User.sample.add(c2);
        User.sample.add(c3);
        User.sample.add(c4);
    }

    public Counselor(String password, int userType, String userName) {
        super(password, userType, userName);
    }

    public Counselor() {
    }
}
