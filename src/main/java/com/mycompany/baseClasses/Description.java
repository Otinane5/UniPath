package com.mycompany.baseClasses;

import java.util.HashMap;
import java.util.Map;

public class Description 
{
    private static final Map<String, String> departmentDescriptions = new HashMap<>();
    
    static {
        //ΟΛΑ ΑΥΤΑ ΕΜΦΑΝΙΖΟΝΤΑΙ ΣΤΟ ΠΕΡΙΒΑΛΛΟΝ ΤΟΥ ΜΑΘΗΤΗ. πρέπει να εμφανιζονται και στο πανεπιστημιο
        departmentDescriptions.put("Τμήμα Πληροφορικής", "Το τμήμα πληροφορικής ιδρύθηκε το...");
        departmentDescriptions.put("Τμήμα Μηχανικών Υπολογιστών", "Το τμήμα Μηχανικών Υπολογιστών ιδρύθηκε το...");
        departmentDescriptions.put("Τμήμα Νομικής", "Η Νομική ιδρύθηκε...");
        departmentDescriptions.put("Τμήμα Ιατρικής", "Η Ιατρική ιδρύθηκε..");
        departmentDescriptions.put("Τμήμα Ψυχολογίας", "Η Ψυχολογία είναι ένα νέο τμήμα...");
        departmentDescriptions.put("Τμήμα Καλών Τεχνών", "Οι Καλές Τέχνες...");
        departmentDescriptions.put("Τμήμα Φιλοσοφίας", "Το τμήμα Φιλοσοφίας προσφέρει στους αποφοίτους του...");
        departmentDescriptions.put("Τμήμα Φιλολογίας", "Το τμήμα Φιλολογίας ιδρύθηκε..");
        departmentDescriptions.put("Τμήμα Διοίκησης Επιχειρήσεων", "Το τμήμα Διοίκησης επιχειρήσεων προσφέρει στους αποφοίτους του....");
        departmentDescriptions.put("Τμήμα Οικονομικών", "Το τμήμα Οικονομικών προσφέρει στους αποφοίτους του...");
        departmentDescriptions.put("Τμήμα Φαρμακευτικής", "Η Φαρμακευτική προσφέρει στους αποφοίτους του...");
        departmentDescriptions.put("Τμήμα Μαθηματικών", "Το τμήμα Μαθηματικών προσφέρει στους αποφοίτους του...");
        departmentDescriptions.put("Τμήμα Χημικών Μηχανικών", "Το τμήμα Χημικών Μηχανικών προσφέρει στους αποφοίτους του...");
        departmentDescriptions.put("Τμήμα Λογοθεραπείας", "Το νεοσύστατο τμήμα Λογοθεραπείας..");       
    }

    public static String getDepartmentDescription(String departmentName) {
        return departmentDescriptions.getOrDefault(departmentName, "Δεν υπάρχει περιγραφή για το τμήμα.");
    }

    public static void setDepartmentDescription(String departmentName, String newDescription) {
        departmentDescriptions.put(departmentName, newDescription);
    }

    public static Map<String, String> getAllDescriptions() {
        return departmentDescriptions;
    }

    
}
