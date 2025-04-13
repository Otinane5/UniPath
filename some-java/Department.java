package com.mycompany.unipath;

import java.util.List;

public class Department {
    public String name;
    public String description;
    public List<Announcement> announcements;

    public Department(String name) {
        this.name = name;
    }

    public void updateDescription(String description) {

    }

    public void addAnnouncement(Announcement announcement) {
    }

}