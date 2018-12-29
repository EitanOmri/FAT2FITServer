package com.fat2fit.model;

public interface ITrainingListName {
    void add(TrainingListName trainingListName) throws DBException;

    TrainingListName[] getTrainingListNames() throws DBException;

    void delete(int id) throws DBException;

    TrainingListName getTrainigListName(int id) throws DBException;
}
