package com.mycompany.unipathui;

import java.util.*;
import java.util.List;
import java.util.Arrays;

public class AnnouncementRepository {
     private static final Map<String, List<AnnouncementView>> departmentAnnouncements = new HashMap<>();
     
     static {
         
          departmentAnnouncements.put("Τμήμα Πληροφορικής", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Καλωσόρισμα φοιτητών","Καλως ορίσατε στο τμήμα πληροφορικής..."),
            new AnnouncementView("Πρόγραμμα μαθημάτων", "Δεν υπάρχει πρόγραμμα..."),
            new AnnouncementView("Αιτήσεις εγγραφής", "Οι αιτήσεις εγγραφής είναι απενεργοποιημένες προς το παρόν...")
          )));
          
           departmentAnnouncements.put("Τμήμα Μηχανικών Υπολογιστών", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Γειά σας νέοι ceidάδες", "Συγγνώμη..."),
            new AnnouncementView("Ανακοίνωση σειντ 2", "Περιεχόμενο σειντ 2"),
            new AnnouncementView("Ανακοίνωση σειντ 3", "Περιεχόμενο σειντ 3"),
            new AnnouncementView("Ανακοίνωση σειντ 4", "Περιεχόμενο σειντ 4"),
            new AnnouncementView("Ανακοίνωση σειντ 5", "Περιεχόμενο σειντ 5")
            
          ))); 
           
           departmentAnnouncements.put("Τμήμα Νομικής", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Ανακοίνωση νομικής 1", "Περιεχόμενο νομικής 1"),
            new AnnouncementView("Ανακοίνωση νομικής 2", "Περιεχόμενο νομικής 2"),
            new AnnouncementView("Ανακοίνωση νομικής 3", "Περιεχόμενο νομικής 3"),
            new AnnouncementView("Ανακοίνωση νομικής 4", "Περιεχόμενο νομικής 4")
         ))); 
           
           departmentAnnouncements.put("Τμήμα Ιατρικής", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Ανακοίνωση ιατρικής 1", "Περιεχόμενο ιατρικής 1"),
            new AnnouncementView("Ανακοίνωση ιατρικής 2", "Περιεχόμενο ιατρικής 2")
         ))); 
           
           departmentAnnouncements.put("Τμήμα Ψυχολογίας", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Ανακοίνωση ψυχολογίας 1", "Περιεχόμενο ψυχολογίας 1")
         )));
          

         
         departmentAnnouncements.put("Τμήμα Καλών Τεχνών", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Ανακοίνωση Καλών Τεχνών 1", "Περιεχόμενο Καλών Τεχνών 1")
           ))); 
         
         departmentAnnouncements.put("Τμήμα Φιλοσοφίας",new ArrayList<>(Arrays.asList(
            new AnnouncementView("Ανακοίνωση Φιλοσοφίας 1", "Περιεχόμενο Φιλοσοφίας 1")
           ))); 
         
         departmentAnnouncements.put("Τμήμα Φιλολογίας",new ArrayList<>(Arrays.asList(
            new AnnouncementView("Ανακοίνωση Φιλολογίας 1", "Περιεχόμενο Φιλολογίας 1")
         ))); 
           
         departmentAnnouncements.put("Τμήμα Διοίκησης Επιχειρήσεων", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Ανακοίνωση Διοίκησης Επιχειρήσεων 1", "Περιεχόμενο Διοίκησης Επιχειρήσεων 1")
         ))); 
         
         departmentAnnouncements.put("Τμήμα Οικονομικών", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Ανακοίνωση Οικονομικών 1", "Περιεχόμενο Οικονομικών 1")
         ))); 
         
         departmentAnnouncements.put("Τμήμα Φαρμακευτικής",new ArrayList<>(Arrays.asList(
            new AnnouncementView("Ανακοίνωση Φαρμακευτικής 1", "Περιεχόμενο Φαρμακευτικής 1")
           ))); 
         
         departmentAnnouncements.put("Τμήμα Μαθηματικών", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Ανακοίνωση Μαθηματικών 1", "Περιεχόμενο Μαθηματικών 1")
         ))); 
         
         departmentAnnouncements.put("Τμήμα Χημικών Μηχανικών", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Ανακοίνωση Χημικών Μηχανικών 1", "Περιεχόμενο Χημικών Μηχανικών 1")
         ))); 
         
        departmentAnnouncements.put("Τμήμα Λογοθεραπείας", new ArrayList<>(Arrays.asList(
            new AnnouncementView("Ανακοίνωση Λογοθεραπείας 1", "Περιεχόμενο Λογοθεραπείας 1")
         ))); 
        
        //ola ta tmhmata!!
        //na phgainei pisw otan dhmosieysh->ok
        
         
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
