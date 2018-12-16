package com.fat2fit.model;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HibernateExerciseHistoryDAO implements IExerciseHistory {
    Factory factoryInstance;

    public HibernateExerciseHistoryDAO() {
        this.factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public Map<String, Integer> getTop3() throws DBException {

        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List  history = session.createQuery("  " +
                "select new com.fat2fit.model.TopNMapping(exerciseHistory.username," + "sum(exerciseHistory.reps*exerciseHistory.sets *exercises.caloriesPerReps)) " +
                "FROM ExerciseHistory exerciseHistory, Exercises exercises " +
                "where exerciseHistory.idExercise=exercises.id  " +
                "group by Username " +
                "order by sum(exerciseHistory.reps*exerciseHistory.sets *exercises.caloriesPerReps)" +
                " desc").setMaxResults(3).list();// hql
        session.close();
        TopNMapping[] returnArr = new TopNMapping[history.size()];
        returnArr = (TopNMapping[]) history.toArray(returnArr);
        for(int i=0;i<returnArr.length;i++)
            System.out.println(returnArr[i].getUsername()+" "+returnArr[i].getTotalCal());



//        Pair<String, Integer>[] returnArr = new Pair[history.size()];
//        returnArr = (Pair<String, Integer>[]) history.toArray(returnArr);
//
//        for(int i=0;i<returnArr.length;i++)
//            System.out.println(returnArr[i].getKey()+"  "+returnArr[i].getValue());
//    return returnArr;
   return null;
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
        List history = session.createQuery("FROM ExerciseHistory WHERE Username=:parm").setParameter("parm",username).list();// hql

        session.close();
        System.out.println("There are " + history.size() + " exercise(s)");
        ExerciseHistory[] returnArr = new ExerciseHistory[history.size()];
        returnArr = (ExerciseHistory[]) history.toArray(returnArr);
        return returnArr;
    }
}