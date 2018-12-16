package com.fat2fit.model;

import org.hibernate.Session;

import java.util.List;

public class HibernateTrainingListExercisesDAO implements ITrainingListExercises {
    Factory factoryInstance;
    public HibernateTrainingListExercisesDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }


    @Override
    public void add(TrainingListExercises trainingListExercises) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        session.save(trainingListExercises);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) throws DBException {
       if(getTrainigList(id)!=null) {
           Session session = factoryInstance.getFactory().openSession();
           session.beginTransaction();
           session.delete(getTrainigList(id));
           session.getTransaction().commit();
           session.close();
       }
    }

    @Override
    public TrainingListExercises getTrainigList(int id) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        TrainingListExercises trainingListExercises = (TrainingListExercises) session.get(TrainingListExercises.class, id);
        session.close();
        return trainingListExercises;
    }

    @Override
    public TrainingListExercises[] getbyTrainigId(int id) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List training = session.createQuery("FROM com.fat2fit.model.TrainingListExercises WHERE IdTraining=:parm").setParameter("parm", id).list();// hql
        session.close();
        System.out.println("There are " + training.size() + " training(s)");
        TrainingListExercises[] returnArr = new TrainingListExercises[training.size()];
        returnArr = (TrainingListExercises[]) training.toArray(returnArr);
        return returnArr;

    }
}
