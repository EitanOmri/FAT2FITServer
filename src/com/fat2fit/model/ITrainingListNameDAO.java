package com.fat2fit.model;

/**
 * The interface Training list name.
 */
public interface ITrainingListNameDAO {

    /**
     * Add training list name.
     *
     * @param trainingListName the training list name
     * @throws DBException the db exception
     */
    void add(TrainingListName trainingListName) throws DBException;

    /**
     * Get training list names in array.
     *
     * @return the training list name [ ]
     * @throws DBException the db exception
     */
    TrainingListName[] getTrainingListNames() throws DBException;

    /**
     * Delete training list name.
     *
     * @param id the id
     * @throws DBException the db exception
     */
    void delete(int id) throws DBException;

    /**
     * Gets training list name.
     *
     * @param id the id
     * @return the trainig list name
     * @throws DBException the db exception
     */
    TrainingListName getTrainingListName(int id) throws DBException;
}
