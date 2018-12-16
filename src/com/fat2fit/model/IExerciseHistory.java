package com.fat2fit.model;

public interface IExerciseHistory {
    TopNMapping[] getTop3() throws DBException;

    void saveExercise(ExerciseHistory exerciseHistory) throws DBException;

    void deleteExercise(int id) throws DBException;

    ExerciseHistory[] getAllHistoryPerUser(String username) throws DBException;

    ExerciseHistory getExercise(int id) throws DBException;
}
