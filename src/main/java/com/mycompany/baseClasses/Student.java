package com.mycompany.baseClasses;

public class Student extends User {
    public String name;
    public int[] quizAnswers = new int[18];

    public Student(String password, int userType, String userName) {
        super(password, userType, userName);
    }

    public static void takeTest() {
    
    }
   
}
