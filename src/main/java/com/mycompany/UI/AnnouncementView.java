package com.mycompany.unipathui;

public class AnnouncementView {
    public final String title;
    public final String body;
    
    public AnnouncementView(String title, String body){
        this.title = title;
        this.body = body;
    }
    
    @Override
    public String toString() {
        return title; //Στην λίστα ανακοινώσεων θα φαίνονται οι τίτλοι
    }
}
