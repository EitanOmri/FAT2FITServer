package com.fat2fit.model;

import org.hibernate.Session;

import java.util.List;

public class HibernateExerciseHistoryDAO implements IExerciseHistory {
    Factory factoryInstance;

    public HibernateExerciseHistoryDAO() {
        this.factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public TopNMapping[] getTop3() throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List history = session.createQuery("  " +
                "select new com.fat2fit.model.TopNMapping(exerciseHistory.username," + "sum(exerciseHistory.reps*exerciseHistory.sets *exercises.caloriesPerReps)) " +
                "FROM ExerciseHistory exerciseHistory, Exercises exercises " +
                "where exerciseHistory.idExercise=exercises.id  " +
                "group by Username " +
                "order by sum(exerciseHistory.reps*exerciseHistory.sets *exercises.caloriesPerReps)" +
                " desc").setMaxResults(3).list();// hql
        session.close();
        TopNMapping[] returnArr = new TopNMapping[history.size()];
        returnArr = (TopNMapping[]) history.toArray(returnArr);
        return returnArr;

    }

    @Override
    public void deleteExercise(int id) throws DBException {
        ExerciseHistory exercises=getExercise(id);
        if(exercises!=null){
            Session session = factoryInstance.getFactory().openSession();
            session.beginTransaction();
            session.delete(exercises);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public ExerciseHistory getExercise(int id) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        ExerciseHistory exerciseHistory = (ExerciseHistory) session.get(ExerciseHistory.class, id);
        session.close();
        return exerciseHistory;
    }

    @Override
    public void saveExercise(ExerciseHistory exerciseHistory) throws DBException {
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
        List history = session.createQuery("FROM ExerciseHistory WHERE Username=:parm").setParameter("parm", username).list();// hql
        session.close();
        System.out.println("There are " + history.size() + " exercise(s)");
        ExerciseHistory[] returnArr = new ExerciseHistory[history.size()];
        returnArr = (ExerciseHistory[]) history.toArray(returnArr);
        return returnArr;
    }
}
