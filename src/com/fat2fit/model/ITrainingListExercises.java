package com.fat2fit.model;

/**
 * The interface Training list exercises.
 */
public interface ITrainingListExercises {
    /**
     * Add.
     *
     * @param trainingListExercises the training list exercises
     * @throws DBException the db exception
     */
    void add(TrainingListExercises trainingListExercises) throws DBException;

    /**
     * Delete.
     *
     * @param id the id
     * @throws DBException the db exception
     */
    void delete(int id) throws DBException;

    /**
     * Gets trainig list.
     *
     * @param id the id
     * @return the trainig list
     * @throws DBException the db exception
     */
    TrainingListExercises getTrainigList(int id) throws DBException;

    /**
     * Getby trainig id training list exercises [ ].
     *
     * @param trainingId the training id
     * @return the training list exercises [ ]
     * @throws DBException the db exception
     */
    TrainingListExercises[] getbyTrainigId(int trainingId) throws DBException;
}
