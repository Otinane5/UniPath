package com.mycompany.unipath;

public class Student extends User {
    public String name;
    public int[] quizAnswers = new int[18];

    public Student(String password, int userType) {
        super(password, userType);
    }

    public static void takeTest() {
    
    }
   
}
