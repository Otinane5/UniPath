package com.mycompany.baseClasses;
import com.mycompany.unipathui.AnswerLog;
import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    public String name;
    public String lastName;
    public static AnswerLog answerLog = new AnswerLog();
    public static boolean hasAnswerLog = false;

    
    public static List<Student> sample = new ArrayList<>();

    public Student(String password, int userType, String userName) {
        super(password, userType, userName);
    }

  
    public Student() {}

    //  Sample init method
    public static void init() {
        Student s1 = new Student("pass1", 3, "student1");
        s1.name = "Μαρία";
        s1.lastName = "Ιωάννου";

        Student s2 = new Student("pass2", 3, "student2");
        s2.name = "Γιάννης";
        s2.lastName = "Παπαδόπουλος";

        sample.add(s1);
        sample.add(s2);
        User.sample.add(s1);
        User.sample.add(s2);
    }

    public static void takeTest() {
   
    }
}
