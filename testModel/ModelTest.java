import com.fat2fit.model.*;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * The type Model test.
 */
public class ModelTest {


    /**
     * User model test.
     */
    @Test
    public void userModelTest() {

        IUser userDAO = new HibernateUserDAO();
        try {
            User omri = new User("omrieitan", "omri", "eitan", "omrieitan@gmail.com", "12345", new Date(1993, 9, 06), 50, 170, 1);
            userDAO.saveUser(omri);

            assertEquals("omrieitan", userDAO.getUser("omrieitan").getUsername());
            User user = userDAO.getUser("omrieitan");
            userDAO.updateUser(user.getUsername(), 100, 190);
            assertEquals(100, userDAO.getUser("omrieitan").getWeight(), 1);
            assertEquals(190, userDAO.getUser("omrieitan").getHeight(), 1);

            userDAO.updateUser(user.getUsername(), 80, 170);
            assertEquals(80, userDAO.getUser("omrieitan").getWeight(), 1);
            assertEquals(170, userDAO.getUser("omrieitan").getHeight(), 1);
            user = new User("tomerShats", "tomer", "shats", "tomershats14@gmail.com", "12345T", new Date(1994, 01, 31), 50, 170, 0);

            int preSaveCount = userDAO.getUseres().length;
            userDAO.saveUser(user);
            assertEquals(preSaveCount + 1, userDAO.getUseres().length);
            assertTrue(userDAO.testLogin("tomerShats", "12345T"));
            assertFalse(userDAO.testLogin("tomerShats1", "12345T"));
            assertFalse(userDAO.testLogin("tomerShats", "123456"));
            assertFalse(userDAO.testLogin("tomerShats", "12345t"));
            assertTrue(userDAO.isManager("omrieitan"));
            assertFalse(userDAO.isManager("tomerShats"));

            userDAO.removeUser(user.getUsername());
            assertEquals(preSaveCount, userDAO.getUseres().length);
            userDAO.removeUser(omri.getUsername());

        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Training list name model test.
     */
    @Test
    public void trainingListNameModelTest() {
        ITrainingListName listNameDAO = new HibernateTrainingListNameDAO();
        try {
            TrainingListName trainingListName = new TrainingListName(1, "test");
            listNameDAO.add(trainingListName);
            assertEquals("test", listNameDAO.getTrainigListName(1).getName());
            listNameDAO.delete(trainingListName.getId());
            assertNull(listNameDAO.getTrainigListName(1));
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Training list exercises model test.
     */
    @Test
    public void trainingListExercisesModelTest() {
        ITrainingListExercises listExercisesDAO = new HibernateTrainingListExercisesDAO();
        try {
            TrainingListExercises trainingListExercises1 = new TrainingListExercises(1, 1000, 1000, 5, 5);
            TrainingListExercises trainingListExercises2 = new TrainingListExercises(2, 1000, 1001, 5, 5);
            TrainingListExercises trainingListExercises3 = new TrainingListExercises(3, 1001, 1001, 5, 5);
            listExercisesDAO.add(trainingListExercises1);
            listExercisesDAO.add(trainingListExercises2);
            listExercisesDAO.add(trainingListExercises3);
            assertEquals(2, listExercisesDAO.getbyTrainigId(1000).length);
            assertEquals(1, listExercisesDAO.getbyTrainigId(1001).length);
            listExercisesDAO.delete(trainingListExercises1.getId());
            listExercisesDAO.delete(trainingListExercises2.getId());
            listExercisesDAO.delete(trainingListExercises3.getId());
            assertEquals(0, listExercisesDAO.getbyTrainigId(1000).length);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }

    /**
     * Message from admin model test.
     */
    @Test
    public void messageFromAdminModelTest() {
        IMessageFromAdmin messageFromAdminDAO = new HibernateMessageFromAdminDAO();
        try {
            MessageFromAdmin messageFromAdmin = new MessageFromAdmin(1, new Date(2018, 01, 31), "test-Hello Admin!");
            int countBeforeSave = messageFromAdminDAO.getAllMessageFromAdmin().length;
            messageFromAdminDAO.saveMessage(messageFromAdmin);
            assertEquals(countBeforeSave + 1, messageFromAdminDAO.getAllMessageFromAdmin().length);
            messageFromAdminDAO.deleteMessage(1);
            assertEquals(countBeforeSave, messageFromAdminDAO.getAllMessageFromAdmin().length);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Message to admin model test.
     */
    @Test
    public void messageToAdminModelTest() {
        IMessageToAdmin messageToAdminDAO = new HibernateMessageToAdminDAO();
        try {
            MessageToAdmin messageToAdmin = new MessageToAdmin(1, new Date(2018, 01, 31), "test-Hello Admin!", "omrieitan");
            int countBeforeSave = messageToAdminDAO.getAllMessageToAdmin().length;
            messageToAdminDAO.saveMessage(messageToAdmin);
            assertEquals(countBeforeSave + 1, messageToAdminDAO.getAllMessageToAdmin().length);
            messageToAdminDAO.deleteMessage(1);
            assertEquals(countBeforeSave, messageToAdminDAO.getAllMessageToAdmin().length);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exercises and history model test.
     */
    @Test
    public void exercisesAndHistoryModelTest() {
        IExercises exercisesDAO = new HibernateExercisesDAO();
        IExerciseHistory hibernateExerciseHistoryDAO = new HibernateExerciseHistoryDAO();
        ExerciseHistory exerciseHistory1 = new ExerciseHistory("tomer", 1, 4, 5, new Date(2018, 01, 31), 3);
        ExerciseHistory exerciseHistory2 = new ExerciseHistory("tomer", 1, 4, 5, new Date(2018, 01, 31), 3);
        ExerciseHistory exerciseHistory3 = new ExerciseHistory("omri", 1, 5, 5, new Date(2018, 01, 31), 3);
        ExerciseHistory exerciseHistory4 = new ExerciseHistory("tomer1", 1, 4, 5, new Date(2018, 01, 31), 3);
        ExerciseHistory exerciseHistory5 = new ExerciseHistory("omri1", 1, 40, 5, new Date(2018, 01, 31), 3);

        try {
            Exercises exercises = new Exercises(1, "testExercise", 100, 10000);
            int countBeforeSave = exercisesDAO.getAllExercises().length;
            assertFalse(exercisesDAO.isExerciseExists(1));
            exercisesDAO.saveExercise(exercises);
            assertTrue(exercisesDAO.isExerciseExists(1));
            assertEquals(countBeforeSave + 1, exercisesDAO.getAllExercises().length);
            assertEquals(exercises, exercisesDAO.getExercise(1));
            //history
            hibernateExerciseHistoryDAO.saveExercise(exerciseHistory1);
            hibernateExerciseHistoryDAO.saveExercise(exerciseHistory2);
            hibernateExerciseHistoryDAO.saveExercise(exerciseHistory3);
            hibernateExerciseHistoryDAO.saveExercise(exerciseHistory4);
            hibernateExerciseHistoryDAO.saveExercise(exerciseHistory5);
            TopNMapping[] arr = hibernateExerciseHistoryDAO.getTop3();
            assertEquals("omri1", arr[0].getUsername());
            assertEquals("tomer", arr[1].getUsername());
            assertEquals("omri", arr[2].getUsername());
            assertEquals(2, hibernateExerciseHistoryDAO.getAllHistoryPerUser("tomer").length);
            assertEquals(1, hibernateExerciseHistoryDAO.getAllHistoryPerUser("omri").length);
            hibernateExerciseHistoryDAO.updateExercise(1, 100, 1000);
            assertEquals(100, hibernateExerciseHistoryDAO.getExercise(1).getReps());
            assertEquals(1000, hibernateExerciseHistoryDAO.getExercise(1).getSets());
            for (int i = 1; i < 6; i++)
                hibernateExerciseHistoryDAO.deleteExercise(i);
            exercisesDAO.deleteExercise(1);
            assertEquals(countBeforeSave, exercisesDAO.getAllExercises().length);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Category model test.
     */
    @Test
    public void categoryModelTest() {
        ICategory categoryDAO = new HibernateCategoryDAO();
        Category category = new Category(1, "testCtegoty");
        try {
            int countBeforeSave = categoryDAO.getCategories().length;
            categoryDAO.addCategory(category);
            assertEquals(countBeforeSave + 1, categoryDAO.getCategories().length);
            assertEquals("testCtegoty", categoryDAO.getCategory(1).getName());
            categoryDAO.deleteCategory(1);
            assertEquals(countBeforeSave, categoryDAO.getCategories().length);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}