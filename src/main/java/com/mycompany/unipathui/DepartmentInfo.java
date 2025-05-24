package com.mycompany.unipathui;

public class DepartmentInfo {
    public String name;        //Όνομα τμήματος
    public String type;        //Τύπος τμήματος
    public int tuitionFee;     //Δίδακτρα
    public int academicPoints; //Απαιτούμενα μόρια
    
    public DepartmentInfo(String name, String type, int tuitionFee, int academicPoints){
        this.name = name;
        this.type = type;
        this.tuitionFee = tuitionFee;
        this.academicPoints = academicPoints;
    }
}
