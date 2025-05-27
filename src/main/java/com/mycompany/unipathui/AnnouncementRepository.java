package com.mycompany.unipathui;

import java.util.*;
import java.util.List;

public class AnnouncementRepository {
     private static final Map<String, List<AnnouncementView>> departmentAnnouncements = new HashMap<>();
     
     static {
         departmentAnnouncements.put("Τμήμα Πληροφορικής", List.of(
            new AnnouncementView("Καλωσόρισμα φοιτητών","Καλως ορίσατε στο τμήμα πληροφορικής..."),
            new AnnouncementView("Πρόγραμμα μαθημάτων", "Δεν υπάρχει πρόγραμμα..."),
            new AnnouncementView("Αιτήσεις εγγραφής", "Οι αιτήσεις εγγραφής είναι απενεργοποιημένες προς το παρόν...")
         ));
         
         departmentAnnouncements.put("Τμήμα Μηχανικών Υπολογιστών", List.of(
            new AnnouncementView("Γειά σας νέοι ceidάδες", "Συγγνώμη..."),
            new AnnouncementView("Ανακοίνωση σειντ 2", "Περιεχόμενο σειντ 2"),
            new AnnouncementView("Ανακοίνωση σειντ 3", "Περιεχόμενο σειντ 3"),
            new AnnouncementView("Ανακοίνωση σειντ 4", "Περιεχόμενο σειντ 4"),
            new AnnouncementView("Ανακοίνωση σειντ 5", "Περιεχόμενο σειντ 5"),
            new AnnouncementView("Ανακοίνωση σειντ 6", "Περιεχόμενο σειντ 6"),
            new AnnouncementView("Ανακοίνωση σειντ 7", "Περιεχόμενο σειντ 7"),
            new AnnouncementView("Ανακοίνωση σειντ 8", "Περιεχόμενο σειντ 8"),
            new AnnouncementView("Ανακοίνωση σειντ 9", "Περιεχόμενο σειντ 9"),
            new AnnouncementView("Ανακοίνωση σειντ 10", "Περιεχόμενο σειντ 10"),
            new AnnouncementView("Ανακοίνωση σειντ 11", "Περιεχόμενο σειντ 11"),
            new AnnouncementView("Ανακοίνωση σειντ 12", "Περιεχόμενο σειντ 12"),
            new AnnouncementView("Ανακοίνωση σειντ 13", "Περιεχόμενο σειντ 13"),
            new AnnouncementView("Ανακοίνωση σειντ 14", "Περιεχόμενο σειντ 14"),
            new AnnouncementView("Ανακοίνωση σειντ 15", "Περιεχόμενο σειντ 15"),
            new AnnouncementView("Ανακοίνωση σειντ 16", "Περιεχόμενο σειντ 16"),
            new AnnouncementView("Ανακοίνωση σειντ 17", "Περιεχόμενο σειντ 17")
         )); 
         
         departmentAnnouncements.put("Τμήμα Νομικής", List.of(
            new AnnouncementView("Ανακοίνωση νομικής 1", "Περιεχόμενο νομικής 1"),
            new AnnouncementView("Ανακοίνωση νομικής 2", "Περιεχόμενο νομικής 2"),
            new AnnouncementView("Ανακοίνωση νομικής 3", "Περιεχόμενο νομικής 3"),
            new AnnouncementView("Ανακοίνωση νομικής 4", "Περιεχόμενο νομικής 4")
         )); 
         
         departmentAnnouncements.put("Τμήμα Ιατρικής", List.of(
            new AnnouncementView("Ανακοίνωση ιατρικής 1", "Περιεχόμενο ιατρικής 1"),
            new AnnouncementView("Ανακοίνωση ιατρικής 2", "Περιεχόμενο ιατρικής 2")
         )); 
         
       /*  departmentAnnouncements.put("Τμήμα Ψυχολογίας", List.of(
            new AnnouncementView("Ανακοίνωση ψυχολογίας 1", "Περιεχόμενο ψυχολογίας 1")
         )); */
         
         departmentAnnouncements.put("Τμήμα Καλών Τεχνών", List.of(
            new AnnouncementView("Ανακοίνωση Καλών Τεχνών 1", "Περιεχόμενο Καλών Τεχνών 1")
         )); 
         
         departmentAnnouncements.put("Τμήμα Φιλοσοφίας", List.of(
            new AnnouncementView("Ανακοίνωση Φιλοσοφίας 1", "Περιεχόμενο Φιλοσοφίας 1")
         )); 
         
         departmentAnnouncements.put("Τμήμα Φιλολογίας", List.of(
            new AnnouncementView("Ανακοίνωση Φιλολογίας 1", "Περιεχόμενο Φιλολογίας 1")
         )); 
           
         departmentAnnouncements.put("Τμήμα Διοίκησης Επιχειρήσεων", List.of(
            new AnnouncementView("Ανακοίνωση Διοίκησης Επιχειρήσεων 1", "Περιεχόμενο Διοίκησης Επιχειρήσεων 1")
         )); 
         
         departmentAnnouncements.put("Τμήμα Οικονομικών", List.of(
            new AnnouncementView("Ανακοίνωση Οικονομικών 1", "Περιεχόμενο Οικονομικών 1")
         )); 
         
         departmentAnnouncements.put("Τμήμα Φαρμακευτικής", List.of(
            new AnnouncementView("Ανακοίνωση Φαρμακευτικής 1", "Περιεχόμενο Φαρμακευτικής 1")
         )); 
         
         departmentAnnouncements.put("Τμήμα Μαθηματικών", List.of(
            new AnnouncementView("Ανακοίνωση Μαθηματικών 1", "Περιεχόμενο Μαθηματικών 1")
         )); 
         
         departmentAnnouncements.put("Τμήμα Χημικών Μηχανικών", List.of(
            new AnnouncementView("Ανακοίνωση Χημικών Μηχανικών 1", "Περιεχόμενο Χημικών Μηχανικών 1")
         )); 
         
        /* departmentAnnouncements.put("Τμήμα Λογοθεραπείας", List.of(
            new AnnouncementView("Ανακοίνωση Λογοθεραπείας 1", "Περιεχόμενο Λογοθεραπείας 1")
         )); */
        
        //ola ta tmhmata
         
     }
     
     public static List<AnnouncementView> getAnnouncements(String departmentName){
         //return departmentAnnouncements.getOrDefault(departmentName, List.of());
         return departmentAnnouncements.getOrDefault(departmentName, new ArrayList<>());
     }
     
     public static void addAnnouncement(String departmentName, AnnouncementView announcement)
     {
         departmentAnnouncements.computeIfAbsent(departmentName, k-> new ArrayList<>()).add(announcement);
     }
     
     public static List<String> getAllDepartmentNames()
     {
         return new ArrayList<>(departmentAnnouncements.keySet());
     }
}
