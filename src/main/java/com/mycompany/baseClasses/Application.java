package com.mycompany.baseClasses;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public String fullName;
    public String residence;
    public String birthDate;
    public String phone;
    public String email;
    public String gradePoints;
    public String state; // "sent", "approved", "rejected"

    public static List<Application> sample = new ArrayList<>();

    public Application(String fullName, String residence, String birthDate, String phone, String email, String gradePoints) {
        this.fullName = fullName;
        this.residence = residence;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.gradePoints = gradePoints;
        this.state = "sent"; // default state
    }

    public static void init() {
        sample.clear(); // optional: reset on re-init
        sample.add(new Application("Γιάννης Παπαδόπουλος", "Αθήνα", "01/01/2000", "2101234567", "giannis@example.com", "18500"));
        sample.add(new Application("Μαρία Κωνσταντίνου", "Θεσσαλονίκη", "15/05/2001", "2310123456", "maria@example.com", "19200"));
        sample.add(new Application("Νίκος Δημητρίου", "Πάτρα", "22/11/1999", "2610123456", "nikos@example.com", "17800"));
    }
}
