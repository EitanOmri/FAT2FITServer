package com.fat2fit.model;

/**
 * The interface User.
 */
public interface IUser {
    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     * @throws DBException the db exception
     */
    User getUser(String username) throws DBException;

    /**
     * Get useres user [ ].
     *
     * @return the user [ ]
     * @throws DBException the db exception
     */
    User[] getUseres() throws DBException;

    /**
     * Save user.
     *
     * @param user the user
     * @throws DBException the db exception
     */
    void saveUser(User user) throws DBException;

    /**
     * Test login boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     * @throws DBException the db exception
     */
    boolean testLogin(String username, String password) throws DBException;

    /**
     * Is user exsits boolean.
     *
     * @param username the username
     * @return the boolean
     * @throws DBException the db exception
     */
    boolean isUserExsits(String username) throws DBException;

    /**
     * Update user.
     *
     * @param username the username
     * @param weight   the weight
     * @param height   the height
     * @throws DBException the db exception
     */
    void updateUser(String username, double weight, double height) throws DBException;

    /**
     * Remove user.
     *
     * @param username the username
     * @throws DBException the db exception
     */
    void removeUser(String username) throws DBException;

    /**
     * Is manager boolean.
     *
     * @param username the username
     * @return the boolean
     * @throws DBException the db exception
     */
    boolean isManager(String username) throws DBException;

    /**
     * Add admin.
     *
     * @param username the username
     * @throws DBException the db exception
     */
    void addAdmin(String username) throws DBException;

    /**
     * Get useres with out admin user [ ].
     *
     * @return the user [ ]
     * @throws DBException the db exception
     */
    User[] getUseresWithOutAdmin() throws DBException;
}
