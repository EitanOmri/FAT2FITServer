package com.fat2fit.model;

public interface ITrainingListName {
    public void add(TrainingListName trainingListName) throws DBException;
    public void delete(int id) throws DBException;
    public TrainingListName getTrainigListName (int id) throws DBException;
}
