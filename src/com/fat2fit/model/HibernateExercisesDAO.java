package com.fat2fit.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * The type Hibernate exercises dao.
 * this class responsible to makes queries to the exercise table in the data base.
 */
public class HibernateExercisesDAO implements IExercisesDAO {
    private Factory factoryInstance;

    /**
     * Instantiates a new Hibernate exercises dao.
     */
    public HibernateExercisesDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public Exercises getExercise(int id) throws DBException {
        //the basic way to get object by session
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        Exercises exercises = null;
        try {
            tx = session.beginTransaction();
            exercises = (Exercises) session.get(Exercises.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get exercise error", e);
        } finally {
            session.close();
            return exercises;
        }
    }

    @Override
    public void saveExercise(Exercises exercises) throws DBException {
        //the basic way to save new object by session
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        try {
            if (!isExerciseExists(exercises.getId())) {
                tx = session.beginTransaction();
                session.save(exercises);
                tx.commit();
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("add exercise error", e);
        } finally {
            session.close();
        }

    }

    @Override
    public boolean isExerciseExists(int id) throws DBException {
        //check if exercise exist
        if (getExercise(id) != null)
            return true;
        return false;
    }

    @Override
    public Exercises[] getAllExercises() throws DBException {
        //getting array of all exercises
        List exercises = null;
        Transaction tx = null;
        Session session = factoryInstance.getFactory().openSession();
        try {
            tx = session.beginTransaction();
            exercises = session.createQuery("FROM com.fat2fit.model.Exercises").list();// hql
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get all exercise", e);
        } finally {
            session.close();
            Exercises[] returnArr = new Exercises[exercises.size()];
            returnArr = (Exercises[]) exercises.toArray(returnArr);
            return returnArr;
        }
    }

    @Override
    public void deleteExercise(int id) throws DBException {
        //the basic way to delete new object by session
        Exercises exercises = getExercise(id);
        if (exercises != null) {
            Session session = factoryInstance.getFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(exercises);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw new DBException("delete exercise error", e);
            } finally {
                session.close();
            }
        }
    }

    @Override
    public Exercises[] getExercisesByCategory(int id) throws DBException {
        //getting array of all exercises by category
        Session session = factoryInstance.getFactory().openSession();
        List exercises=null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            exercises = session.createQuery("FROM com.fat2fit.model.Exercises where CategoryID=:parm").setParameter("parm", id).list();// hql
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get all exercises by category error", e);
        } finally {
            session.close();    Exercises[] returnArr = new Exercises[exercises.size()];
            returnArr = (Exercises[]) exercises.toArray(returnArr);
            return returnArr;
        }
    }
}
