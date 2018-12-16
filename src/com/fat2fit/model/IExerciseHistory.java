package com.fat2fit.model;

import javafx.util.Pair;

import java.util.Map;

public interface IExerciseHistory {
    Map<String,Integer> getTop3 () throws DBException;
    public void saveExercise(ExerciseHistory exerciseHistory) throws DBException;
    ExerciseHistory [] getAllHistoryPerUser(String username) throws DBException;

}
