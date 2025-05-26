package com.mycompany.baseClasses;


/* OLD IMPORTS FOR JSON LOGIN
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException; */
import java.util.ArrayList;

import java.util.List;

public class User {
    public String userName;
    public String password;
    public int userType; // 1=πανεπιστήμιο, 2=σύμβουλος, 3=μαθητής
    
    public static List<User> sample = new ArrayList<>();

    public static void init() {
        sample.add(new User("s1", 1, "sampleUniversity"));     
        sample.add(new User("s3", 3, "sampleStudent"));   
        sample.add(new User("pass4", 3, "nvasileiou"));    
    }
    
    
    public User(String password, int userType, String userName) {
        this.password = password;
        this.userType = userType;
        this.userName = userName;
    }
    
        public User() {
        // No-argument constructor for Jackson
    }


    /* OLD LOGIN WITH JSON FILES
        
    private static final String SAVE_FILE = "C:/Users/User/Documents/NetBeansProjects/github_manual1/src/main/java/com/mycompany/saveFiles/users.json";
    
    private static List<User> loadUsers() { // επιστρέφει λίστα απο user-objects 
    ObjectMapper objectMapper = new ObjectMapper();
    List<User> users = new ArrayList<>();

    try {
        // Read the JSON file and convert it to a List<User>
         File file = new File(SAVE_FILE);       
        if (file.exists()) {
            users = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
        }else{System.out.println("File does not Exist!");}
    } catch (IOException e) {
        e.printStackTrace(); // Handle the exception (you might want to log this instead)
    }

    return users; // Return the list of users
}*/

    public static User login(String userName, String password) { // Αυτή η μέθοδος επιστρέφει τον user αν αυτός βρεθεί.
        //List<User> users = loadUsers(); for json
        for (User user : sample) {
            if (user.userName.equals(userName) && user.password.equals(password)) {
                System.out.println("Success");
                
                return user; // Επιστρέφουμε το χρήστη αν βρεθεί
            }
        }
        System.out.println("Not-Found");
        return null; // Επιστρέφουμε null αν δεν βρεθεί ο χρήστης
    }
 public String getUserName() {
    return this.userName;
}   
}