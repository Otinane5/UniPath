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
    public String counselorUsername;
    
    public static List<Appointment> appointmentsForCounselor = new ArrayList<>();

    public Appointment(String fullName, String phone, String email, String interests, String counselorUsername) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.status = "Εκκρεμές"; // default state
        this.interests = interests;
        this.counselorUsername = counselorUsername;
    }

    public static void init() {
        appointmentsForCounselor.clear(); // optional: reset on re-init
        appointmentsForCounselor.add(new Appointment("Σοφία Ιωάννου", "6941234567", "maria@example.com", "Πληροφορική", "sampleCounselor"));
        appointmentsForCounselor.add(new Appointment("Γιάννης Παπαδόπουλος", "6971111111", "giannis@example.com", "Μηχανικών Υπολογιστών", "sampleCounselor"));
        appointmentsForCounselor.add(new Appointment("Άννα Λεωνίδα", "6987654321", "anna@example.com", "Νομική, Ψυχολογία", "sampleCounselor"));
        appointmentsForCounselor.add(new Appointment("Ευαγγελία Καραντάση", "6922222222", "georgia@example.com", "Ιατρική", "sampleCounselor"));
        
        appointmentsForCounselor.add(new Appointment("Νίκος Παπαχρήστου", "6988888888", "nikos@example.com", "Φιλοσοφία", "gdimtriou"));
        appointmentsForCounselor.add(new Appointment("Κώστας Χατζηνικολάου", "6933333333", "kostas@example.com", "Καλών Τεχνών", "gdimtriou"));
        appointmentsForCounselor.add(new Appointment("Δημήτρης Παναγιωτόπουλος", "6944444444", "dimitri@example.com", "Φιλολογίας", "gdimtriou"));
        
        appointmentsForCounselor.add(new Appointment("Κατερίνα Σκάζα", "6955555555", "katerina@example.com", "Διοίκηση Επιχειρήσεων, Οικονομικά", "akaragianni"));
        
        appointmentsForCounselor.add(new Appointment("Βασιλική Δημοπούλου", "6966666666", "vasiliki@example.com", "Φαρμακευτική, Χημικών Μηχανικών", "nvasileiou"));
        appointmentsForCounselor.add(new Appointment("Ειρήνη Νικολάου", "6977777777", "eirini@example.com", "Μαθηματικά, Λογοθεραπεία", "nvasileiou"));
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}