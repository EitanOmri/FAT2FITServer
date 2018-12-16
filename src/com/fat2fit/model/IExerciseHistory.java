package com.fat2fit.model;

public interface IExerciseHistory {
    User getTop3 () throws DBException;
    public void saveExercise(ExerciseHistory exerciseHistory) throws DBException;
}
