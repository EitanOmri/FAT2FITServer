package com.fat2fit.model;

public interface ITrainingListExercises {
    public void add(TrainingListExercises trainingListExercises) throws DBException;
    public void delete(int id) throws DBException;
    public TrainingListExercises getTrainigList(int id) throws DBException;
    public TrainingListExercises[] getbyTrainigId(int id) throws DBException;
}
