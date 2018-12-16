package com.fat2fit.model;

public interface IExerciseHistory {
    TopNMapping[] getTop3 () throws DBException;
    public void saveExercise(ExerciseHistory exerciseHistory) throws DBException;
    ExerciseHistory [] getAllHistoryPerUser(String username) throws DBException;

}
