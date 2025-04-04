package com.mycompany.unipath;

import java.util.List;

public class Apointment {
    public int studentID;
    public int councelorID;
    public List<String> info;
    public int state; //(0=SENT, -1=REJECTED, 1=ACCEPTED) Αυτό μπορεί να γίνει με enum
    
    public Apointment() {
        this.studentID = 10;
    }
}
