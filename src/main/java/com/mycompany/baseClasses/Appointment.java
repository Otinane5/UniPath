package com.mycompany.baseClasses;

import java.util.ArrayList;
import java.util.List;

public class Appointment {
    //ATTRIBUTES
    public String fullName;
    public String phone;
    public String email;
    public String interests;
    public String status; // "sent", "approved", "rejected"
    
    public static List<Appointment> appointmentsForCounselor = new ArrayList<>();

    public Appointment(String fullName, String phone, String email, String interests) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.status = "Εκκρεμές"; // default state
        this.interests = interests;
    }

    public static void init() {
        appointmentsForCounselor.clear(); // optional: reset on re-init
        appointmentsForCounselor.add(new Appointment("Μαρία Ιωάννου", "6941234567", "maria@example.com", "Πληροφορική, Μαθηματικά"));
        appointmentsForCounselor.add(new Appointment("Γιάννης Παπαδόπουλος", "6971111111", "giannis@example.com", "Μηχανολογία, Φυσική"));
        appointmentsForCounselor.add(new Appointment("Άννα Λεωνίδα", "6987654321", "anna@example.com", "Ιατρική, Βιολογία"));
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}