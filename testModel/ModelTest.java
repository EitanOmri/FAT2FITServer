import com.fat2fit.model.DBException;
import com.fat2fit.model.HibernateUserDAO;
import com.fat2fit.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ModelTest {


    @Test
    public void userModelTest() {
        HibernateUserDAO p = new HibernateUserDAO();
        try {
            assertEquals("omrieitan", p.getUser("omrieitan").getUsername());
        } catch (DBException e) {
            e.printStackTrace();
        }
        try {
            User user = p.getUser("omrieitan");
            p.updateUser(user.getUsername(), 100, 190);
            assertEquals(100, p.getUser("omrieitan").getWeight(), 1);
            assertEquals(190, p.getUser("omrieitan").getHeight(), 1);

            p.updateUser(user.getUsername(), 80, 170);
            assertEquals(80, p.getUser("omrieitan").getWeight(), 1);
            assertEquals(170, p.getUser("omrieitan").getHeight(), 1);
        } catch (DBException e) {
            e.printStackTrace();
        }

        User user = new User("tomerShats", "tomer", "shats", "tomershats14@gmail.com", "12345", new Date(1994, 01, 31), 50, 170, true);

        try {
            p.saveUser(user);
            User[] allPr = new User[0];
            allPr = p.getUseres();

        } catch (DBException e) {
            e.printStackTrace();
        }


    }
}
