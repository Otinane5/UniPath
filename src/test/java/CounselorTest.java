//Zeta here! I got help from
//https://www.browserstack.com/guide/unit-testing-java
//Otherwise I wouldn't know how to do the test cases

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

import com.mycompany.baseClasses.Counselor;

public class CounselorTest {
    @Test
     public void testCounselorInitialization() {
        Counselor c = new Counselor("1234", 2, "john123");
        c.name = "Γιάννης";
        c.lastName = "Παπαδόπουλος";
        c.email = "giannis@example.com";
        c.phoneNum = "2100000000";
        c.bio = "Βιογραφικό";

        Assertions.assertEquals("1234", c.password);
        Assertions.assertEquals(2, c.userType);
        Assertions.assertEquals("john123", c.userName);
        Assertions.assertEquals("Γιάννης", c.name);
        Assertions.assertEquals("Παπαδόπουλος", c.lastName);
        Assertions.assertEquals("giannis@example.com", c.email);
        Assertions.assertEquals("2100000000", c.phoneNum);
        Assertions.assertEquals("Βιογραφικό", c.bio);
     }
     @Test
     public void testReviewAverage() {
        Counselor c = new Counselor();
        c.reviews = List.of(5, 4, 3, 5);
        
        double avg = c.reviews.stream().mapToInt(Integer::intValue).average().orElse(0);
        
        Assertions.assertEquals(4.25, avg, 0.001); // Δέχεται μικρή απόκλιση
    }
     @Test
    public void testSampleInit() {
        Counselor.sample.clear(); // Reset για test
        Counselor.init();
        
        Assertions.assertFalse(Counselor.sample.isEmpty());
        Assertions.assertEquals(4, Counselor.sample.size());
        Assertions.assertEquals("Μαρία", Counselor.sample.get(0).name);
    }
}
