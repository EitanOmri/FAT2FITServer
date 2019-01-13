package com.fat2fit.model;

/**
 * The interface Training list exercises.
 */
public interface ITrainingListExercises {
    /**
     * Add training list exercises.
     *
     * @param trainingListExercises the training list exercises
     * @throws DBException the db exception
     */
    void add(TrainingListExercises trainingListExercises) throws DBException;

    /**
     * Delete training list exercises.
     *
     * @param id the id
     * @throws DBException the db exception
     */
    void delete(int id) throws DBException;

    /**
     * Gets training list exercises.
     *
     * @param id the id
     * @return the trainig list
     * @throws DBException the db exception
     */
    TrainingListExercises getTrainigList(int id) throws DBException;

    /**
     * Get training list exercises in array.
     *
     * @param trainingId the training id
     * @return the training list exercises [ ]
     * @throws DBException the db exception
     */
    TrainingListExercises[] getbyTrainigId(int trainingId) throws DBException;
}
