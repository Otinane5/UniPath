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
    public String department;

    public static List<Application> sample = new ArrayList<>();

    public Application(String fullName, String residence, String birthDate, String phone, String email, String gradePoints, String department) {
        this.fullName = fullName;
        this.residence = residence;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.gradePoints = gradePoints;
        this.state = "sent"; // default state
        this.department=department;
    }

    public static void init() {
        sample.clear(); // optional: reset on re-init
        sample.add(new Application("Γιάννης Παπαδόπουλος", "Αθήνα", "01/01/2000", "2101234567", "giannis@example.com", "18500","Ιατρικής"));
        sample.add(new Application("Μαρία Κωνσταντίνου", "Θεσσαλονίκη", "15/05/2001", "2310123456", "maria@example.com", "19200","Πληροφορικής"));
        sample.add(new Application("Νίκος Δημητρίου", "Πάτρα", "22/11/1999", "2610123456", "nikos@example.com", "17800","Εργοθεραπείας"));
        sample.add(new Application("Κωνσταντίνος Κωνσταντίνου", "Αθήνα", "22/11/2004", "2100000000", "kostas@example.com", "18100","Μηχανικών Υπολογιστών"));
        sample.add(new Application("Ευτυχία Ευτυχίου", "Πάτρα", "27/11/2002", "2610123456", "eytyxia@example.com", "15000","Πολιτικών Επιστημών"));
    }
}
