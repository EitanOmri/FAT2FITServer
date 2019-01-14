package com.fat2fit.model;

/**
 * The interface Message from admin.
 */
public interface IMessageFromAdminDAO {

    /**
     * Save message.
     *
     * @param messageFromAdmin the message from admin
     * @throws DBException the db exception
     */
    void saveMessage(MessageFromAdmin messageFromAdmin) throws DBException;

    /**
     * Get message from admin.
     *
     * @param id the id
     * @return the message from admin
     * @throws DBException the db exception
     */
    MessageFromAdmin getMessageFromAdmin(int id) throws DBException;

    /**
     * Delete message.
     *
     * @param id the id
     * @throws DBException the db exception
     */
    void deleteMessage(int id) throws DBException;

    /**
     * Get all message from admin message from admin in array.
     *
     * @return the message from admin [ ]
     * @throws DBException the db exception
     */
    MessageFromAdmin[] getAllMessageFromAdmin() throws DBException;

}
