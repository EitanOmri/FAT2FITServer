package com.fat2fit.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * The type Hibernate training list name dao.
 */
public class HibernateTrainingListNameDAO implements ITrainingListNameDAO {
    private Factory factoryInstance;

    /**
     * Instantiates a new Hibernate training list name dao.
     */
    public HibernateTrainingListNameDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public void add(TrainingListName trainingListName) throws DBException {
        //the basic way to save object by session
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(trainingListName);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("add training list name error", e);
        } finally {
            session.close();
        }
    }

    @Override
    public TrainingListName getTrainingListName(int id) throws DBException {
        //the basic way to get object by session
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        TrainingListName trainingListName = null;
        try {
            tx = session.beginTransaction();
            trainingListName = (TrainingListName) session.get(TrainingListName.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get training list name error", e);
        } finally {
            session.close();
            return trainingListName;
        }
    }

    @Override
    public void delete(int id) throws DBException {
        //the basic way to delete object by session
        TrainingListName trainingListName = getTrainingListName(id);
        if (trainingListName != null) {
                Transaction tx = null;
                Session session = factoryInstance.getFactory().openSession();
                try {
                    tx = session.beginTransaction();
                    session.delete(trainingListName);
                    tx.commit();
                } catch (Exception e) {
                    if (tx != null) tx.rollback();
                    throw new DBException("delete training list name error", e);
                } finally {
                    session.close();
                }
            }
    }

    @Override
    public TrainingListName[] getTrainingListNames() throws DBException {
        //getting array of all training list name
        Session session = factoryInstance.getFactory().openSession();
        List trainingListNames = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
             trainingListNames = session.createQuery("FROM com.fat2fit.model.TrainingListName").list();// hql
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get all training list name admin", e);
        } finally {
            TrainingListName[] returnArr = new TrainingListName[trainingListNames.size()];
            returnArr = (TrainingListName[]) trainingListNames.toArray(returnArr);
            return returnArr;
        }

    }



}
