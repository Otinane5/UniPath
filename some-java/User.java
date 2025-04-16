package com.mycompany.unipath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    public int id;
    public String password;
    public int userType; // 1=πανεπιστήμιο, 2=σύμβουλος, 3=μαθητής

    private static final String SAVE_FILE = "users.json"; // static και final

    public User(String password, int userType) {
        this.password = password;
        this.userType = userType;
        this.id = getNextId();
        saveUser(); // προσθέτει τον user στο savefile
    }
    // No-argument constructor for Jackson
    public User() {
    }

    private static int getNextId() { // βρίσκει διαθέσιμο ID, πολύ inefficient, θα μπορούσαμε να κάνουμε store τα χρησιμοποιημένα id κάπου αλλού
        List<User> users = loadUsers();
        return users.isEmpty() ? 1 : users.get(users.size() - 1).id + 1;
    }


    private void saveUser() { // ενδεχομένως να πρέπει να το μεταφέρουμε μέσα στον constructor
        List<User> users = loadUsers();
        users.add(this);
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        try { // εδώ υπάρχει ένα try-catch σε περίπτωση error κατά την εγγραφή
            objectMapper.writeValue(new File(SAVE_FILE), users);
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    private static List<User> loadUsers() { // επιστρέφει λίστα απο user-objects 
    ObjectMapper objectMapper = new ObjectMapper();
    List<User> users = new ArrayList<>();

    try {
        // Read the JSON file and convert it to a List<User>
        File file = new File(SAVE_FILE);
        if (file.exists()) {
            users = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
        }
    } catch (IOException e) {
        e.printStackTrace(); // Handle the exception (you might want to log this instead)
    }

    return users; // Return the list of users
}

    public static User login(int id, String password) { // Αυτή η μέθοδος επιστρέφει τον user αν αυτός βρεθεί.
        List<User> users = loadUsers(); // Φορτώνει όλους τους users
        for (User user : users) {
            if (user.id == id && user.password.equals(password)) {
                return user; // Επιστρέφουμε το χρήστη αν βρεθεί
            }
        }
        return null; // Επιστρέφουμε null αν δεν βρεθεί ο χρήστης
    }
}