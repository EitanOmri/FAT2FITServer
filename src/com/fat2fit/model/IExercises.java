package com.fat2fit.model;

public interface IExercises {
    Exercises getExercise (int id) throws DBException;
    void saveExercise(Exercises exercises) throws DBException;
    boolean isExerciseExsists(int id) throws DBException;
    Exercises [] getAllExercises() throws DBException;
    void deleteExercise(int id) throws DBException;

}
