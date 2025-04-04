package com.mycompany.unipath;

import java.util.List;

public class University extends User{
    
    public List<Department> departmentList;
    
    public University(int password, int userType) {
        super(password, userType);
    }
    
}
