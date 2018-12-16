package com.fat2fit.model;

import org.hibernate.Session;

public class HibernateTrainingListNameDAO implements ITrainingListName{
    Factory factoryInstance;
    public HibernateTrainingListNameDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public void add(TrainingListName trainingListName) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        session.save(trainingListName);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TrainingListName getTrainigListName(int id) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        TrainingListName trainingListName = (TrainingListName) session.get(TrainingListName.class, id);
        session.close();
        return trainingListName;
    }

    @Override
    public void delete(int id) throws DBException {
        if(getTrainigListName(id)!=null) {
            Session session = factoryInstance.getFactory().openSession();
            session.beginTransaction();
            session.delete(getTrainigListName(id));
            session.getTransaction().commit();
            session.close();
        }
    }
}
