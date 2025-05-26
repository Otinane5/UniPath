package com.mycompany.baseClasses;

import java.util.List;
import java.util.ArrayList;


public class Department {
    public String name;
    public String description;
    public List<Announcement> announcements;

    public Department(String name) {
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
    
}

