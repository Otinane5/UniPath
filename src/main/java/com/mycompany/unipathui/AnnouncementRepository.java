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
         new AnnouncementView("Γειά σας νέοι ceidάδες","Συγγνώμη..."),
            new AnnouncementView("Ανακοίνωση σειντ 2", "Περιεχόμενο σειντ 2"),
            new AnnouncementView("Ανακοίνωση σειντ 3", "Περιεχόμενο σειντ 3"),
            new AnnouncementView("Ανακοίνωση σειντ 4", "Περιεχόμενο σειντ 4")
         ));        
     }
     
     public static List<AnnouncementView> getAnnouncements(String departmentName){
         return departmentAnnouncements.getOrDefault(departmentName, List.of());
     }
}
