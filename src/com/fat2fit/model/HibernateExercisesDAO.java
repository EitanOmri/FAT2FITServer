package com.fat2fit.model;

import org.hibernate.Session;

import java.util.List;

/**
 * The type Hibernate exercises dao.
 * this class responsible to makes queries to the exercise table in the data base.
 */
public class HibernateExercisesDAO implements IExercises {
    private Factory factoryInstance;

    /**
     * Instantiates a new Hibernate exercises dao.
     */
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
        if (!isExerciseExists(exercises.getId())) {
            Session session = factoryInstance.getFactory().openSession();
            session.beginTransaction();
            session.save(exercises);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean isExerciseExists(int id) throws DBException {
        if (getExercise(id) != null)
            return true;
        return false;
    }

    @Override
    public Exercises[] getAllExercises() throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List exercises = session.createQuery("FROM com.fat2fit.model.Exercises").list();// hql
        session.close();
        Exercises[] returnArr = new Exercises[exercises.size()];
        returnArr = (Exercises[]) exercises.toArray(returnArr);
        return returnArr;
    }

    @Override
    public void deleteExercise(int id) throws DBException {
        Exercises exercises = getExercise(id);
        if (exercises != null) {
            Session session = factoryInstance.getFactory().openSession();
            session.beginTransaction();
            session.delete(exercises);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public Exercises[] getExercisesByCategory(int id) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List exercises = session.createQuery("FROM com.fat2fit.model.Exercises where CategoryID=:parm").setParameter("parm", id).list();// hql
        session.close();
        Exercises[] returnArr = new Exercises[exercises.size()];
        returnArr = (Exercises[]) exercises.toArray(returnArr);
        return returnArr;
    }
}
