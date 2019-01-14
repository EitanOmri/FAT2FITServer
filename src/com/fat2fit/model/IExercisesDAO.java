package com.fat2fit.model;

/**
 * The interface Exercises.
 */
public interface IExercisesDAO {
    /**
     * Get exercise.
     *
     * @param id the id
     * @return the exercise
     * @throws DBException the db exception
     */
    Exercises getExercise(int id) throws DBException;

    /**
     * Add exercise.
     *
     * @param exercises the exercises
     * @throws DBException the db exception
     */
    void saveExercise(Exercises exercises) throws DBException;

    /**
     * Is exercise exist.
     *
     * @param id the id
     * @return the boolean
     * @throws DBException the db exception
     */
    boolean isExerciseExists(int id) throws DBException;

    /**
     * Get all exercises in array.
     *
     * @return the exercises [ ]
     * @throws DBException the db exception
     */
    Exercises[] getAllExercises() throws DBException;

    /**
     * Get all exercise history by category id.
     *
     * @param id the category id
     * @return the exercises [ ]
     * @throws DBException the db exception
     */
    Exercises[] getExercisesByCategory(int id) throws DBException;

    /**
     * Delete exercise.
     *
     * @param id the exercise id
     * @throws DBException the db exception
     */
    void deleteExercise(int id) throws DBException;

}
