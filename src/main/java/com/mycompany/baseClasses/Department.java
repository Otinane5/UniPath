package com.mycompany.baseClasses;

import java.util.List;
import java.util.ArrayList;

//Η κλάση Department αναπαριστά ένα πανεπιστημιακό Τμήμα
//Το Τμήμα χαρακτηρίζεται από το ID, το όνομα, την Περιγραφή και τις Ανακοινώσεις του.
public class Department 
{
    private int id;
    public String name;
    public String description;
    public List<Announcement> announcements;

    public Department(int id, String name) 
    {
        this.id=id;
        this.name = name;
        this.description = Description.getDepartmentDescription(name); //ανάκτηση της Περιγραφής
        this.announcements = new ArrayList<>(); //αρχικοποίηση λίστας ανακοινώσεων
    }

    //Αυτή η μέθοδος ενημερώνει την περιγραφή του Τμήματος
    public void updateDepartmentDescription(String description) 
    {
        this.description = description;
    }

    //Προσθέτει την νέα ανακοίνωση στην λίστα του Τμήματος
    public void addAnnouncement(Announcement announcement) 
    {
        announcements.add(announcement);
    }
    
    public int getId() 
    {
        return id;
    }
    
    public String getName() 
    {
        return name;
    }

    public String getDescription() 
    {
        return description;
    }

    //επιστροφή της λίστας ανακοινώσεων του Τμήματος
    public List<Announcement> getAnnouncements() 
    {
        return announcements;
    }
}

