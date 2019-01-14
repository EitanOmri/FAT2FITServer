package com.fat2fit.model;

/**
 * The interface Message to admin.
 */
public interface IMessageToAdminDAO {
    /**
     * Save message.
     *
     * @param messageToAdmin the message to admin
     * @throws DBException the db exception
     */
    void saveMessage(MessageToAdmin messageToAdmin) throws DBException;

    /**
     * Gets message to admin.
     *
     * @param id the id
     * @return the message to admin
     * @throws DBException the db exception
     */
    MessageToAdmin getMessageToAdmin(int id) throws DBException;

    /**
     * Delete message.
     *
     * @param id the id
     * @throws DBException the db exception
     */
    void deleteMessage(int id) throws DBException;

    /**
     * Get all message to admin message to admin [ ].
     *
     * @return the message to admin [ ]
     * @throws DBException the db exception
     */
    MessageToAdmin[] getAllMessageToAdmin() throws DBException;

}
