package com.mycompany.baseClasses;

import java.util.List;
import java.util.ArrayList;


public class Department {
    private int id;
    
    public String name;
    public String description;
    public List<Announcement> announcements;

    public Department(int id) {
        this.id=id;
        this.name = name;
        this.description = "Περιγραφή για το τμήμα " + name;
        this.announcements = new ArrayList<>();
    }

    public void updateDescription(String description) {
                this.description = description;


    }

    public void addAnnouncement(Announcement announcement) {
            announcements.add(announcement);

    }
    
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }
    
    public int getId() {
        return id;
    }

}

