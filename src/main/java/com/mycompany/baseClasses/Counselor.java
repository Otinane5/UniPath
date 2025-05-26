package com.mycompany.baseClasses;

import java.util.ArrayList;
import java.util.List;

public class Counselor extends User {
    public List<Apointment> appointments;
    public String name;
    public String lastName;
    public String email;
    public String phoneNum;
    public String bio;
    public List<Integer> reviews = new ArrayList<>(); // Reviews will be integers (e.g., ratings 1-5)

    public static List<Counselor> sample = new ArrayList<>();

    public static void init() {
        
        // Counselor 1
        Counselor c1 = new Counselor("pass1", 2, "mpapadopoulou");
        c1.name = "Μαρία";
        c1.lastName = "Παπαδοπούλου";
        c1.email = "maria.pap@example.com";
        c1.phoneNum = "2101234567";
        c1.bio = "Ειδική στην επαγγελματική καθοδήγηση.";
        c1.reviews = List.of(5, 4, 5, 5);  // Example reviews (ratings from 1 to 5)

        // Counselor 2
        Counselor c2 = new Counselor("pass2", 2, "gdimtriou");
        c2.name = "Γιάννης";
        c2.lastName = "Δημητρίου";
        c2.email = "giannis.d@example.com";
        c2.phoneNum = "2107654321";
        c2.bio = "Σύμβουλος με εμπειρία σε πανεπιστημιακές σπουδές.";
        c2.reviews = List.of(4, 3, 4, 5);


        // Counselor 3
        Counselor c3 = new Counselor("pass3", 2, "akaragianni");
        c3.name = "Άννα";
        c3.lastName = "Καραγιάννη";
        c3.email = "anna.k@example.com";
        c3.phoneNum = "2109988776";
        c3.bio = "Βοηθώ μαθητές να βρουν το σωστό μονοπάτι.";
        c3.reviews = List.of(5, 4, 5);
        // Counselor 4
        Counselor c4 = new Counselor("pass4", 2, "nvasileiou");
        c4.name = "Νίκος";
        c4.lastName = "Βασιλείου";
        c4.email = "nikos.v@example.com";
        c4.phoneNum = "2103332211";
        c4.bio = "Εξειδίκευση σε επιλογή σχολών.";
        c4.reviews = List.of(4, 4, 3, 5);

        // Add counselors to the sample list
        sample.add(c1);
        sample.add(c2);
        sample.add(c3);
        sample.add(c4);
    }

    public Counselor(String password, int userType, String userName) {
        super(password, userType, userName);
    }

    public Counselor() {
    }
}
