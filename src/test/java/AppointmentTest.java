//Zeta here! I got help from
//https://www.browserstack.com/guide/unit-testing-java
//Otherwise I wouldn't know how to do the test cases

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mycompany.baseClasses.Appointment;

public class AppointmentTest {
    @Test
     public void testAppointmentConstructor() {
        Appointment ap = new Appointment(
            "Μαρία Παπαδοπούλου",
            "6901010101",
            "maria@example.com",
            "Καλών Τεχνών",
            "akaragianni"
        );

        Assertions.assertEquals("Μαρία Παπαδοπούλου", ap.fullName);
        Assertions.assertEquals("6901010101", ap.phone);
        Assertions.assertEquals("maria@example.com", ap.email);
        Assertions.assertEquals("Καλών Τεχνών", ap.interests);
        Assertions.assertEquals("akaragianni", ap.counselorUsername);
        //Assertions.assertEquals("Εκκρεμές", ap.status); //Default status is "Εκκρεμές"
    }
}
