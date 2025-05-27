package com.mycompany.baseClasses;
import java.util.HashMap;
import java.util.Map;

//δεν ξέρω ακόμη αν όντως θα χρησιμεύσει ως Οντότητα/base class
public class Description 
{
     private static final Map<String, String> departmentDescriptions = new HashMap<>();

    static {
        departmentDescriptions.put("Τμήμα Πληροφορικής", "Περιγραφή τμήματος Πληροφορικής...");
        departmentDescriptions.put("Τμήμα Μηχανικών Υπολογιστών", "Περιγραφή τμήματος CEID...");
        // ...
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
