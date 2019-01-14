package com.fat2fit.model;

/**
 * The interface Exercise history.
 */
public interface IExerciseHistoryDAO {
    /**
     * Get top 3 burn calories users in the last week.
     *
     * @return the top n mapping [ ]
     * @throws DBException the db exception
     */
    TopNMapping[] getTop3() throws DBException;

    /**
     * add exercise.
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
     * Update reps and sets for existing exercise.
     *
     * @param id   the id
     * @param reps the reps
     * @param sets the sets
     * @throws DBException the db exception
     */
    void updateExercise(int id, int reps, int sets) throws DBException;

    /**
     * Get all exercise history by user.
     *
     * @param username the username
     * @return the exercise history [ ]
     * @throws DBException the db exception
     */
    ExerciseHistory[] getAllHistoryPerUser(String username) throws DBException;

    /**
     * Get statistics of the last 7 days that the user trains and the total calories he burns for each day.
     *
     * @param username the username
     * @return the weekly cal mapping [ ]
     * @throws DBException the db exception
     */
    WeeklyCalMapping[] getStatisticsWeeklyCal(String username) throws DBException;

    /**
     * Gets exercise by id.
     *
     * @param id the id
     * @return the exercise
     * @throws DBException the db exception
     */
    ExerciseHistory getExercise(int id) throws DBException;

    /**
     * Get statistics of the all burned calories divided per category.
     *
     * @param username the username
     * @return the category mapping [ ]
     * @throws DBException the db exception
     */
    CategoryMapping[] getStatisticsCategory(String username) throws DBException;


}
