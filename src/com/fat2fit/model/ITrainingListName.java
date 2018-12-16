package com.fat2fit.model;

public interface ITrainingListName {
    void add(TrainingListName trainingListName) throws DBException;

    void delete(int id) throws DBException;

    TrainingListName getTrainigListName(int id) throws DBException;
}
