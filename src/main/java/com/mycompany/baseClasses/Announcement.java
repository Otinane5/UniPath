package com.mycompany.baseClasses;

// Η κλάση Announcement αναπαριστά μια Ανακοίνωση ενός Τμήματος, η οποία περιέχει τίτλο και σώμα.
// Μέθοδοι: ανάγνωσης, τροποποίησης και εμφάνισης της ανακοίνωσης.

public class Announcement 
{
    private String title;
    private String body;
    
    //Constructor
    public Announcement(String title, String body)
    {   
        this.title=title;
        this.body=body;
    }

    //επιστρέφει τον τίτλο της Ανακοίνωσης
    public String getTitle()
    {
        return title;
    }

    //επιστρέφει το σώμα της Ανακοίνωσης
    public String getBody()
    {
        return body;
    }

    //ορισμός νέου τίτλου
    public void setTitle(String title)
    {
        this.title=title;
    }

    //ορισμός νέου σώματος
    public void setBody(String body)
    {
        this.body=body;
    }

    //επιστρέφει τον τίτλο της ανακοίνωσης ως συμβολοσειρά
    @Override
    public String toString()
    {
        return title;
    }
}

    
  
    
   