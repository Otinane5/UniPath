package com.mycompany.baseClasses;

import java.util.List;

public class Counselor extends User{
    public List<Apointment> apointments;

    public Counselor(String password, int userType, String userName) {
        super(password, userType,userName);
    }

}
