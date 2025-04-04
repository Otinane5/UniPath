package com.mycompany.unipath;
import java.util.List;

public class MessageBox {
    public List<String> messages;
    public int[] usersID; //δλδ μεταξύ ποιών χρηστών είναι το τσατ
    public int messageNumDis = 10; /* Μπορούμε με αυτή την μεταβλητή να ελέγχουμε οτι θα εμφανίζονται/φορτώνουν 
    μόνο τα πχ10 πιο πρόσφατα μνμτα.*/

    public MessageBox(int[] IDs) {
        this.usersID = IDs;
    }
}
