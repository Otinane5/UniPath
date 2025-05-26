package com.mycompany.baseClasses;
import java.util.HashMap;
import java.util.Map;
//δεν ξέρω ακόμη αν όντως θα χρησιμεύσει ως Οντότητα/base class
public class Description {
    //private static String departmentDescription = "αρχικη Περιγραφή...";

   /* private static final Map<String, String> departmentDescriptions = new HashMap<>();

    static {
        departmentDescriptions.put("Τμήμα Πληροφορικής", "Περιγραφή τμήματος πληροφορικής...");
        departmentDescriptions.put("Τμήμα Μηχανικών Υπολογιστών", "Περιγραφή τμήματος ceid...");
//...
    }
    
    
    public static String getDepartmentDescription(String departmentName) {
        //return departmentDescription;
                return departmentDescriptions.getOrDefault(departmentName, "Δεν υπάρχει περιγραφή για το τμήμα.");

    }

    /*public static void setDepartmentDescription(String newDescription) {
        departmentDescription = newDescription;
    }
    
    public static void setDescription(String departmentName, String newDescription) {
        departmentDescriptions.put(departmentName, newDescription);
    }

    public static Map<String, String> getAllDescriptions() {
        return departmentDescriptions;
    }*/

}
