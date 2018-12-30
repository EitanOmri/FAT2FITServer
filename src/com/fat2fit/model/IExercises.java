package com.fat2fit.model;

/**
 * The interface Exercises.
 */
public interface IExercises {
    /**
     * Gets exercise.
     *
     * @param id the id
     * @return the exercise
     * @throws DBException the db exception
     */
    Exercises getExercise(int id) throws DBException;

    /**
     * Save exercise.
     *
     * @param exercises the exercises
     * @throws DBException the db exception
     */
    void saveExercise(Exercises exercises) throws DBException;

    /**
     * Is exercise exsists boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DBException the db exception
     */
    boolean isExerciseExsists(int id) throws DBException;

    /**
     * Get all exercises exercises [ ].
     *
     * @return the exercises [ ]
     * @throws DBException the db exception
     */
    Exercises[] getAllExercises() throws DBException;

    /**
     * Get exercises by category exercises [ ].
     *
     * @param id the id
     * @return the exercises [ ]
     * @throws DBException the db exception
     */
    Exercises[] getExercisesByCategory(int id) throws DBException;

    /**
     * Delete exercise.
     *
     * @param id the id
     * @throws DBException the db exception
     */
    void deleteExercise(int id) throws DBException;

}
