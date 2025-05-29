package com.mycompany.baseClasses;

import java.util.ArrayList;
import java.util.List;

public class University extends User {
    public String name;
    public List<Department> departmentList;
    public static List<University> sample = new ArrayList<>();

    public University(String password, int userType, String userName) {
        super(password, userType, userName);
    }

    public University() {}

    public static void init() 
    {
        University u1 = new University("uni1", 1, "university1");
        u1.name = "Πανεπιστήμιο Αθηνών";

        University u2 = new University("uni2", 1, "university2");
        u2.name = "Αριστοτέλειο Πανεπιστήμιο Θεσσαλονίκης";
        
        University u3 = new University("uni3", 1, "university3");
        u3.name = "Πανεπιστήμιο Πατρών";

        sample.add(u1);
        sample.add(u2);
        User.sample.add(u1);
        User.sample.add(u2);
    }
}

