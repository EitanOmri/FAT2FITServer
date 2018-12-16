import com.fat2fit.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ModelTest {


    @Test
    public void userModelTest() {
        HibernateUserDAO userDAO = new HibernateUserDAO();
        try {
            assertEquals("omrieitan", userDAO.getUser("omrieitan").getUsername());

            User user = userDAO.getUser("omrieitan");
            userDAO.updateUser(user.getUsername(), 100, 190);
            assertEquals(100, userDAO.getUser("omrieitan").getWeight(), 1);
            assertEquals(190, userDAO.getUser("omrieitan").getHeight(), 1);

            userDAO.updateUser(user.getUsername(), 80, 170);
            assertEquals(80, userDAO.getUser("omrieitan").getWeight(), 1);
            assertEquals(170, userDAO.getUser("omrieitan").getHeight(), 1);
            user = new User("tomerShats", "tomer", "shats", "tomershats14@gmail.com", "12345", new Date(1994, 01, 31), 50, 170, true);

            int preSaveCount = userDAO.getUseres().length;
            userDAO.saveUser(user);
            assertEquals(preSaveCount + 1, userDAO.getUseres().length);

            assertTrue(userDAO.testLogin("tomerShats", "12345"));
            assertFalse(userDAO.testLogin("tomerShats1", "12345"));
            assertFalse(userDAO.testLogin("tomerShats", "123456"));
            userDAO.removeUser(user.getUsername());
            assertEquals(preSaveCount, userDAO.getUseres().length);

        } catch (DBException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void trainingListNameModelTest() {
        HibernateTrainingListNameDAO listNameDAO = new HibernateTrainingListNameDAO();
        try {
            TrainingListName trainingListName = new TrainingListName(10000, "test");
            listNameDAO.add(trainingListName);
            assertEquals("test", listNameDAO.getTrainigListName(10000).getName());
            listNameDAO.delete(trainingListName.getId());
            assertNull(listNameDAO.getTrainigListName(10000));
        } catch (DBException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void trainingListExercisesModelTest() {
        HibernateTrainingListExercisesDAO listExercisesDAO = new HibernateTrainingListExercisesDAO();
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
            assertEquals(0,listExercisesDAO.getbyTrainigId(1000).length);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void messageFromAdminModelTest() {
        HibernateMessageFromAdminDAO messageFromAdminDAO = new HibernateMessageFromAdminDAO();
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

    @Test
    public void messageToAdminModelTest() {
        HibernateMessageToAdminDAO messageToAdminDAO = new HibernateMessageToAdminDAO();
        try {
            MessageToAdmin messageToAdmin = new MessageToAdmin(1, new Date(2018, 01, 31), "test-Hello Admin!","omrieitan");
            int countBeforeSave = messageToAdminDAO.getAllMessageToAdmin().length;
            messageToAdminDAO.saveMessage(messageToAdmin);
            assertEquals(countBeforeSave + 1, messageToAdminDAO.getAllMessageToAdmin().length);
            messageToAdminDAO.deleteMessage(1);
            assertEquals(countBeforeSave, messageToAdminDAO.getAllMessageToAdmin().length);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void exercisesModelTest() {
        HibernateExercisesDAO exercisesDAO = new HibernateExercisesDAO();
        try {
            Exercises exercises = new Exercises(1, "testExercise", 100,10000);
            int countBeforeSave = exercisesDAO.getAllExercises().length;
            assertFalse(exercisesDAO.isExerciseExsists(1));
            exercisesDAO.saveExercise(exercises);
            assertEquals(countBeforeSave + 1, exercisesDAO.getAllExercises().length);
            assertTrue(exercisesDAO.isExerciseExsists(1));
            assertEquals(exercises,exercisesDAO.getExercise(1));
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
    //TODO: add test methods for ExercisesHistory and category
}
