package com.mycompany.baseClasses;

import java.util.List;

public class University extends User{
    
    public List<Department> departmentList;
    
    public University(String password, int userType, String userName) {
        super(password, userType, userName);
    }
    
}
