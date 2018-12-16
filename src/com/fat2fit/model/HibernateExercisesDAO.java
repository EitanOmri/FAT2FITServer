package com.fat2fit.model;

import org.hibernate.Session;

import java.util.List;

public class HibernateExercisesDAO implements IExercises {
    Factory factoryInstance;

    public HibernateExercisesDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public Exercises getExercise(int id) throws DBException {

        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        Exercises exercises = (Exercises) session.get(Exercises.class, id);
        session.close();
        return exercises;
    }

    @Override
    public void saveExercise(Exercises exercises) throws DBException {
        if(!isExerciseExsists(exercises.getId())){
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        session.save(exercises);
        session.getTransaction().commit();
        session.close();
        }
    }

    @Override
    public boolean isExerciseExsists(int id) throws DBException {
    if(getExercise(id)!=null)
        return true;
    return false;
    }
    @Override
    public Exercises[] getAllExercises() throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List exercises = session.createQuery("FROM com.fat2fit.model.Exercises").list();// hql
        session.close();
        System.out.println("There are " + exercises.size() + " Exercise(s)");
        Exercises[] returnArr = new Exercises[exercises.size()];
        returnArr = (Exercises[]) exercises.toArray(returnArr);
        return returnArr;
    }
}
