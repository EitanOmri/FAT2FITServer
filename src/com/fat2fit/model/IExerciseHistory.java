package com.fat2fit.model;

/**
 * The interface Exercise history.
 */
public interface IExerciseHistory {
    /**
     * Get top 3 top n mapping [ ].
     *
     * @return the top n mapping [ ]
     * @throws DBException the db exception
     */
    TopNMapping[] getTop3() throws DBException;

    /**
     * Save exercise.
     *
     * @param exerciseHistory the exercise history
     * @throws DBException the db exception
     */
    void saveExercise(ExerciseHistory exerciseHistory) throws DBException;

    /**
     * Delete exercise.
     *
     * @param id the id
     * @throws DBException the db exception
     */
    void deleteExercise(int id) throws DBException;

    /**
     * Update exercise.
     *
     * @param id   the id
     * @param reps the reps
     * @param sets the sets
     * @throws DBException the db exception
     */
    void updateExercise(int id, int reps, int sets) throws DBException;

    /**
     * Get all history per user exercise history [ ].
     *
     * @param username the username
     * @return the exercise history [ ]
     * @throws DBException the db exception
     */
    ExerciseHistory[] getAllHistoryPerUser(String username) throws DBException;

    /**
     * Get statistics weekly cal weekly cal mapping [ ].
     *
     * @param username the username
     * @return the weekly cal mapping [ ]
     * @throws DBException the db exception
     */
    WeeklyCalMapping[] getStatisticsWeeklyCal(String username) throws DBException;

    /**
     * Gets exercise.
     *
     * @param id the id
     * @return the exercise
     * @throws DBException the db exception
     */
    ExerciseHistory getExercise(int id) throws DBException;

    /**
     * Get statistics category category mapping [ ].
     *
     * @param username the username
     * @return the category mapping [ ]
     * @throws DBException the db exception
     */
    CategoryMapping[] getStatisticsCategory(String username) throws DBException;


}
