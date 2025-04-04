package com.mycompany.unipath;

public class User {
  public int id;
  public int password;
  public int userType; // 1=πανεπιστήμιο, 2=σύμβουλος 3=μαθητής ??? (enum???)

public User(int password, int userType) {
     /*εδώ υποθέτω θα πρέπει να ανοίγει το savefile με τους χρήστες, να διαβάζει 
        τον αριθμό των χρηστών και να αναθέσει τον επόμενο στο id*/
        //έπίσης πρέπει να ενημερώνουμε τον χρήστη για το id του ώστε να κάνει log in με αυτό.
        this.password = password;
        this.userType = userType;
}
}
