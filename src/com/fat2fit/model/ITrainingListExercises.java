package com.fat2fit.model;

public interface ITrainingListExercises {
    void add(TrainingListExercises trainingListExercises) throws DBException;

    void delete(int id) throws DBException;

    TrainingListExercises getTrainigList(int id) throws DBException;

    TrainingListExercises[] getbyTrainigId(int trainingId) throws DBException;
}
