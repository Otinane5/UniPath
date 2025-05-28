//Zeta here! I got help from
//https://www.browserstack.com/guide/unit-testing-java
//Otherwise I wouldn't know how to do the test cases

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

import com.mycompany.baseClasses.Department;
import com.mycompany.baseClasses.Announcement;

public class DepartmentTest {
    @Test
    public void testDepartmentInitialization() {
        Department dept = new Department(1, "Πληροφορική");

        Assertions.assertEquals(1, dept.getId());
        Assertions.assertEquals("Πληροφορική", dept.getName());
        Assertions.assertNotNull(dept.getDescription()); // Εφόσον παίρνει περιγραφή από Description.getDepartmentDescription
        Assertions.assertTrue(dept.getAnnouncements().isEmpty());
    }

    @Test
    public void testUpdateDescription() {
        Department dept = new Department(2, "Μηχανολογία");
        dept.updateDepartmentDescription("Νέα περιγραφή");

        Assertions.assertEquals("Νέα περιγραφή", dept.getDescription());
    }

    @Test
    public void testAddAnnouncement() {
        Department dept = new Department(3, "Οικονομικά");
        Announcement ann = new Announcement("Ανακοίνωση", "Κείμενο");

        dept.addAnnouncement(ann);

        List<Announcement> anns = dept.getAnnouncements();
        Assertions.assertEquals(1, anns.size());
        //Assertions.assertEquals("Ανακοίνωση", anns.get(0).title);
        //The title needs to be public and I'm scared of changing its type
    }    
}
