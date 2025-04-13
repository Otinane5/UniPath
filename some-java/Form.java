
package com.mycompany.unipath;

import java.util.List;


public class Form {
    public int studentID;
    public int receiverID;
    public List<String> data;
    public int state; //(0=SENT, -1=REJECTED, 1=ACCEPTED) Αυτό μπορεί να γίνει με enum

public Form (int studentID, int receiverID, List<String> data) {
    this.studentID = studentID;
    this.receiverID = receiverID;
    this.data = data;
    this.state = 0;
}
}
