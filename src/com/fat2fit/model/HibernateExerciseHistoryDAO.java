package com.fat2fit.model;

import org.hibernate.Session;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The type Hibernate exercise history dao.
 * this class responsible to makes queries to the exercise history table in the data base.
 */
public class HibernateExerciseHistoryDAO implements IExerciseHistory {
    private Factory factoryInstance;

    /**
     * Instantiates a new Hibernate exercise history dao.
     */
    public HibernateExerciseHistoryDAO() {
        this.factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public TopNMapping[] getTop3() throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -7);// go back 7 days
        Date minDate = cal.getTime();
        /*the query returns a list of TopNMapping.
        we found the 3 users that burn the most calories in the last week and the total calories each of them burned.
         */
        List history = session.createQuery("  " +
                "select new com.fat2fit.model.TopNMapping(exerciseHistory.username, " +
                "sum(exerciseHistory.reps*exerciseHistory.sets *exercises.caloriesPerReps)) " +
                "FROM com.fat2fit.model.ExerciseHistory exerciseHistory, com.fat2fit.model.Exercises exercises " +
                "where exerciseHistory.idExercise=exercises.id  and exerciseHistory.date >=:parm " +
                "group by Username " +
                "order by sum(exerciseHistory.reps*exerciseHistory.sets *exercises.caloriesPerReps) " +
                "desc").setParameter("parm", minDate).setMaxResults(3).list();// hql
        session.close();
        //converts list to array
        TopNMapping[] returnArr = new TopNMapping[history.size()];
        returnArr = (TopNMapping[]) history.toArray(returnArr);
        return returnArr;
    }

    @Override
    public void deleteExercise(int id) throws DBException {
        //the basic way to delete new object by session
        ExerciseHistory exercises = getExercise(id);
        if (exercises != null) {
            Session session = factoryInstance.getFactory().openSession();
            session.beginTransaction();
            session.delete(exercises);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public ExerciseHistory getExercise(int id) throws DBException {
        //the basic way to get object by session
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        ExerciseHistory exerciseHistory = (ExerciseHistory) session.get(ExerciseHistory.class, id);
        session.close();
        return exerciseHistory;
    }

    @Override
    public void saveExercise(ExerciseHistory exerciseHistory) throws DBException {
        //the basic way to save new object by session
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        session.save(exerciseHistory);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public ExerciseHistory[] getAllHistoryPerUser(String username) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        //getting list of all exercises by username
        List history = session.createQuery("FROM com.fat2fit.model.ExerciseHistory exerciseHistory WHERE Username=:parm order by exerciseHistory.date desc").setParameter("parm", username).list();// hql
        session.close();
        ExerciseHistory[] returnArr = new ExerciseHistory[history.size()];
        returnArr = (ExerciseHistory[]) history.toArray(returnArr);
        return returnArr;
    }

    @Override
    public WeeklyCalMapping[] getStatisticsWeeklyCal(String username) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List history = session.createQuery(
                "select new com.fat2fit.model.WeeklyCalMapping(exerciseHistory.date,sum(exerciseHistory.reps*exerciseHistory.sets *exercises.caloriesPerReps)) " +
                        "FROM com.fat2fit.model.ExerciseHistory exerciseHistory, com.fat2fit.model.Exercises exercises " +
                        " where exerciseHistory.idExercise=exercises.id and exerciseHistory.username=:parm" +
                        " group by exerciseHistory.date order by exerciseHistory.date desc").setParameter("parm", username).setMaxResults(7).list();// hql
        session.close();
        WeeklyCalMapping[] returnArr = new WeeklyCalMapping[history.size()];
        returnArr = (WeeklyCalMapping[]) history.toArray(returnArr);
        return returnArr;
    }

    @Override
    public void updateExercise(int id, int reps, int sets) throws DBException {
        ExerciseHistory exerciseHistory = getExercise(id);
        if (exerciseHistory != null) {
            exerciseHistory.setReps(reps);
            exerciseHistory.setSets(sets);
            Session session = factoryInstance.getFactory().openSession();
            session.beginTransaction();
            session.update(exerciseHistory);
            session.getTransaction().commit();
            session.close();


        }
    }

    @Override
    public CategoryMapping[] getStatisticsCategory(String username) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List categoryStat = session.createQuery(
                "select new com.fat2fit.model.CategoryMapping(category.name,sum(exerciseHistory.reps*exerciseHistory.sets)) " +
                        "FROM com.fat2fit.model.ExerciseHistory exerciseHistory, com.fat2fit.model.Exercises exercises, com.fat2fit.model.Category category " +
                        " where exerciseHistory.idExercise=exercises.id and exercises.categoryID=category.id and exerciseHistory.username=:parm" +
                        " group by category.id").setParameter("parm", username).list();// hql
        session.close();
        CategoryMapping[] returnArr = new CategoryMapping[categoryStat.size()];
        returnArr = (CategoryMapping[]) categoryStat.toArray(returnArr);
        return returnArr;
    }


}
