package com.mycompany.baseClasses;

import com.mycompany.unipathui.LoginFrame;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Unipath {
    public static User currentUser;
    public static LoginFrame login = new LoginFrame();
    //+other data

public static void main (String[] args){
    User.init();
    Counselor.init();
    Application.init();
    Appointment.init();
    }
}

