package com.mycompany.unipathui;

import java.util.*;
import java.util.List;
import java.util.Arrays;

// This class manages all announcements that are related to university departments.
// It stores predefined announcements and allows access or modification of them.
public class AnnouncementRepository {

    // A map that stores lists of announcements per department. Each key is a
    // department name, and the value is a list of announcement objects.
    private static final Map<String, List<AnnouncementView>> departmentAnnouncements = new HashMap<>();

    // This static block initializes the departmentAnnouncements map with
    // example data for multiple departments, each having its own announcements.
    static {
        // Department of Informatics - Welcoming students and giving
        // info about the upcoming semester and important dates.
        departmentAnnouncements.put("Τμήμα Πληροφορικής", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Καλωσόρισμα φοιτητών", "Καλως ορίσατε στο τμήμα Πληροφορικής!"),
            new AnnouncementView("Πρόγραμμα μαθημάτων", "Το πρόγραμμα μαθημάτων του Προπτυχιακού θα ανακοινωθεί σύντομα."),
            new AnnouncementView("Αιτήσεις εγγραφής", "Οι αιτήσεις εγγραφής είναι απενεργοποιημένες προς το παρόν.Αναμένετε νέα ανακοίνωση.")
        )));

        // CEID announcements - Contains fun and formal updates
        // like seminars and course or lab info.
        departmentAnnouncements.put("Τμήμα Μηχανικών Υπολογιστών", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Γειά σας νέοι ceidάδες", "Συγγνώμη..."),
            new AnnouncementView("CEID Seminar", "Social Hour την Παρασκευή στις 15.00. Stay Tuned!"),
            new AnnouncementView("Έναρξη Εργαστηρίων", "Τα εργαστήρια ξεκινάνε την 2η εβδομάδα του τρέχοντος εξαμήνου."),
            new AnnouncementView("Αποτελέσματα Εξέτασης Τεχνολογίας Λογισμικού", "Τα αποτελέσματα της εξέτασης και του Project έχουν ανακοινωθεί!"),
            new AnnouncementView("Υποτροφίες", "Νέα προθεσμία υποβολής δηλώσεων: 30-05-2025 23.59μμ.")
        )));

        // Legal Studies Department - Focused on course starts,
        // scholarships, and transfer announcements.
        departmentAnnouncements.put("Τμήμα Νομικής", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Έναρξη Μαθημάτων Αστικού Δικαίου", "Τα μαθήματα ξεκινάνε την Τετάρτη στις 15.00."),
            new AnnouncementView("Μετεγγραφές 2025", "Οι επιτυχόντες των μετεγγραφών έχουν ανακοινωθεί από το Υπουργείο Παιδείας"),
            new AnnouncementView("Υποτροφίες", "Νέα προθεσμία υποβολής δηλώσεων: 30-05-2025 23.59μμ.")
        )));

        // Medical School - From exams to Erasmus,
        // everything a med student needs to know.
        departmentAnnouncements.put("Τμήμα Ιατρικής", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Πρόγραμμα Εξεταστικής", "Το πρόγραμμα της εξεταστικής έχει ανακοινωθεί.Καλή Επιτυχία!"),
            new AnnouncementView("Erasmus", "Οι αιτήσεις για το πρόγραμμα Erasmus της επόμενης Ακαδημαϊκής χρονιάς έχουν αρχίσει.Δηλώστε τώρα το ενδιαφέρον σας!"),
            new AnnouncementView("Έναρξη Κλινικών Ασκήσεων", "Οι κλινικές ξεκινάνε από Δευτέρα 03/03.")
        )));

        // Psychology Department - Students must register for
        // class groups and ensure academic ID procedures are done.
        departmentAnnouncements.put("Τμήμα Ψυχολογίας", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Πρόγραμμα μαθημάτων", "Το πρόγραμμα θα ανακοινωθεί σύντομα."),
            new AnnouncementView("Ομάδες", "Οι ομάδες έχουν ανοίξει στο Eclass. Παρακαλούμε να εγγραφείτε"),
            new AnnouncementView("Ακαδημαϊκή Ταυτότητα", "Παρακαλούμε τους νέους φοιτητές να προσκομίσουν τα απαραίτητα έγγραφα τους για την δημιουργία του πάσου τους.")
        )));

        // Arts Department - News about exhibitions, registration
        // periods and student document submissions.
        departmentAnnouncements.put("Τμήμα Καλών Τεχνών", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Έκθεση Ζωγραφικής", "Σας προσκαλούμε στην έκθεση των έργων τέχνης των φοιτητών μας στο Αμφιθέατρο αύριο όλη την ημέρα."),
            new AnnouncementView("Αιτήσεις εγγραφής", "Οι αιτήσεις εγγραφής είναι απενεργοποιημένες προς το παρόν.Αναμένετε νέα ανακοίνωση."),
            new AnnouncementView("Ακαδημαϊκή Ταυτότητα", "Παρακαλούμε τους νέους φοιτητές μας να προσκομίσουν τα απαραίτητα έγγραφα τους για την δημιουργία του πάσου τους.")
        )));

        // Philosophy announcements - classic topics
        // such as course registration and exams.
        departmentAnnouncements.put("Τμήμα Φιλοσοφίας", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Δήλωση Μαθημάτων", "Μπορείτε πλέον να δηλώσετε τα μαθήματά σας για το τρέχον εξάμηνο."),
            new AnnouncementView("Πρόγραμμα Εξεταστικής", "Το πρόγραμμα της εξεταστικής έχει ανακοινωθεί.Καλή Επιτυχία!"),
            new AnnouncementView("Ακαδημαϊκή Ταυτότητα", "Παρακαλούμε τους νέους φοιτητές μας να προσκομίσουν τα απαραίτητα έγγραφα τους για την δημιουργία του πάσου τους.")
        )));

        departmentAnnouncements.put("Τμήμα Φιλολογίας", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Σύμβουλος Καθηγητής", "Έχουν ανατεθεί οι σύμβουλοι καθηγητές για τα νέα μέλη της πανεπιστημιακής κοινότητας."),
            new AnnouncementView("Αιτήσεις εγγραφής", "Οι αιτήσεις εγγραφής είναι απενεργοποιημένες προς το παρόν.Αναμένετε νέα ανακοίνωση."),
            new AnnouncementView("Ακαδημαϊκή Ταυτότητα", "Παρακαλούμε τους νέους φοιτητές μας να προσκομίσουν τα απαραίτητα έγγραφα τους για την δημιουργία του πάσου τους.")
        )));

        departmentAnnouncements.put("Τμήμα Διοίκησης Επιχειρήσεων", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Workshop: Soft Skills", "Παρασκευή στις 14.00, Αίθουσα Α"),
            new AnnouncementView("Erasmus", "Οι αιτήσεις για το πρόγραμμα Erasmus έχουν αρχίσει.Δηλώστε τώρα το ενδιαφέρον σας!"),
            new AnnouncementView("Πρακτική Άσκηση", "Δηλώστε το ενδιαφέρον σας για την πρακτική άσκηση του εξαμήνου για να σας συνδέσουμε με τον φορέα που επιθυμείτε.")
        )));

        departmentAnnouncements.put("Τμήμα Οικονομικών", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Δήλωση Μαθημάτων", "Μπορείτε πλέον να δηλώσετε τα μαθήματά σας για το τρέχον εξάμηνο."),
            new AnnouncementView("Ομάδες", "Οι ομάδες για την εργασία στην Μακροοικονομία Ι έχουν ανοίξει στο Eclass.Παρακαλούμε να εγγραφείτε"),
            new AnnouncementView("Ακαδημαϊκή Ταυτότητα", "Παρακαλούμε τους νέους φοιτητές μας να προσκομίσουν τα απαραίτητα έγγραφα τους για την δημιουργία του πάσου τους.")
        )));

        departmentAnnouncements.put("Τμήμα Φαρμακευτικής", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Πρόγραμμα Εξεταστικής", "Το πρόγραμμα της εξεταστικής έχει ανακοινωθεί.Καλή Επιτυχία!"),
            new AnnouncementView("Έναρξη Εργαστηρίων", "Τα εργαστήρια ξεκινάνε την 2η εβδομάδα του τρέχοντος εξαμήνου."),
            new AnnouncementView("Έναρξη Εργαστηρίων Βιοχημείας", "Τα εργαστήρια ξεκινάνε την Τετάρτη στις 15.00.")
        )));

        departmentAnnouncements.put("Τμήμα Μαθηματικών", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Μετεγγραφές 2025", "Οι επιτυχόντες των μετεγγραφών έχουν ανακοινωθεί από το Υπουργείο Παιδείας."),
            new AnnouncementView("Σύμβουλος Καθηγητής", "Έχουν ανατεθεί οι σύμβουλοι καθηγητές για τα νέα μέλη της πανεπιστημιακής κοινότητας"),
            new AnnouncementView("Ομάδες", "Οι ομάδες έχουν ανοίξει στο Eclass.Παρακαλούμε να εγγραφείτε")
        )));

        departmentAnnouncements.put("Τμήμα Χημικών Μηχανικών", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Πρόγραμμα μαθημάτων", "Το πρόγραμμα θα ανακοινωθεί σύντομα."),
            new AnnouncementView("Πρόγραμμα Εξεταστικής", "Το πρόγραμμα της εξεταστικής έχει ανακοινωθεί.Καλή Επιτυχία!"),
            new AnnouncementView("Έναρξη Μαθημάτων Θερμοδυναμικής Ι", "Τα μαθήματα ξεκινάνε την Τετάρτη στις 11.00.")
        )));

        departmentAnnouncements.put("Τμήμα Λογοθεραπείας", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Erasmus", "Οι αιτήσεις για το πρόγραμμα Erasmus έχουν αρχίσει. Δηλώστε τώρα το ενδιαφέρον σας!"),
            new AnnouncementView("Πρακτική Άσκηση", "Δηλώστε το ενδιαφέρον σας για την πρακτική άσκηση του εξαμήνου για να σας συνδέσουμε με τον φορέα που επιθυμείτε.")
        )));

        departmentAnnouncements.put("Τμήμα Πολιτικών Επιστημών", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Πρόγραμμα Εξεταστικής", "Το πρόγραμμα της εξεταστικής έχει ανακοινωθεί.Καλή Επιτυχία!"),
            new AnnouncementView("Ακαδημαϊκή Ταυτότητα", "Παρακαλούμε τους νέους φοιτητές μας να προσκομίσουν τα απαραίτητα έγγραφα τους για την δημιουργία του πάσου τους."),
            new AnnouncementView("Έναρξη Μαθήματος Ευρωπαική και Πολιτική Οικονομία", "Τα μαθήματα ξεκινάνε την Παρασκευή στις 09.00.")
        )));

        departmentAnnouncements.put("Τμήμα Εργοθεραπείας", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Πρόγραμμα μαθημάτων", "Το πρόγραμμα ανακοινώθηκε"),
            new AnnouncementView("Αιτήσεις εγγραφής", "Οι αιτήσεις εγγραφής είναι απενεργοποιημένες προς το παρόν.Αναμένετε νέα ανακοίνωση.")
        )));

        departmentAnnouncements.put("Τμήμα Ηλεκτρολόγων Μηχανικών", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Πρακτική Άσκηση", "Δηλώστε το ενδιαφέρον σας για την πρακτική άσκηση."),
            new AnnouncementView("Έξέταση Εργαστηρίου Ηλεκτρικών Μηχανών", "Τρίτη στις 10.00!"),
            new AnnouncementView("Έναρξη Μαθήματος Συστήματα Αυτόματου Ελέγχου", "Τα μαθήματα ξεκινάνε την Τετάρτη στις 15.00.")
        )));
    }

    // This method returns a list of announcements for a given department name.
    // If there are no announcements, it returns an empty list instead.
    public static List<AnnouncementView> getAnnouncements(String departmentName) {
        return departmentAnnouncements.getOrDefault(departmentName, new ArrayList<>());
    }

    // Adds a new announcement to a department. If the department does not exist,
    // it will be created and then the announcement will be added.
    public static void addAnnouncement(String departmentName, AnnouncementView announcement) {
        departmentAnnouncements.computeIfAbsent(departmentName, k -> new ArrayList<>()).add(announcement);
    }

    // Retrieves a list of all department names that currently have
    // at least one registered announcement in the system.
    public static List<String> getAllDepartmentNames() {
        return new ArrayList<>(departmentAnnouncements.keySet());
    }
}
