package com.fat2fit.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * The type Hibernate training list exercises dao.
 */
public class HibernateTrainingListExercisesDAO implements ITrainingListExercisesDAO {
    private Factory factoryInstance;

    /**
     * Instantiates a new Hibernate training list exercises dao.
     */
    public HibernateTrainingListExercisesDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public void add(TrainingListExercises trainingListExercises) throws DBException {
        //the basic way to save object by session
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(trainingListExercises);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("add training list exercises error", e);
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) throws DBException {
        //the basic way to delete object by session
        TrainingListExercises trainingListExercises = getTrainigList(id);
        if (trainingListExercises != null) {
            Transaction tx = null;
            Session session = factoryInstance.getFactory().openSession();
            try {
                tx = session.beginTransaction();
                session.delete(trainingListExercises);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw new DBException("delete training list exercise error", e);
            } finally {
                session.close();
            }
        }
    }

    @Override
    public TrainingListExercises getTrainigList(int id) throws DBException {
        //the basic way to get object by session
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        TrainingListExercises trainingListExercises = null;
        try {
            tx = session.beginTransaction();
            trainingListExercises = (TrainingListExercises) session.get(TrainingListExercises.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get training list exercises error", e);
        } finally {
            session.close();
            return trainingListExercises;
        }
    }

    @Override
    public TrainingListExercises[] getbyTrainigId(int trainingId) throws DBException {
        //getting array of all training list exercises
        Session session = factoryInstance.getFactory().openSession();
        List training = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            training = session.createQuery("FROM com.fat2fit.model.TrainingListExercises WHERE IdTraining=:parm").setParameter("parm", trainingId).list();// hql
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get all training list exercise by training id admin", e);
        } finally {
            TrainingListExercises[] returnArr = new TrainingListExercises[training.size()];
            returnArr = (TrainingListExercises[]) training.toArray(returnArr);
            return returnArr;
        }
    }
}
