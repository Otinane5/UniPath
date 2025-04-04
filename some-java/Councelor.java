package com.mycompany.unipath;

import java.util.List;

public class Councelor extends User{
    public List<Apointment> apointments;

    public Councelor(int password, int userType) {
        super(password, userType);
    }

}
