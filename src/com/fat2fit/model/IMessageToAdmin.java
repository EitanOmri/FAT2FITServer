package com.fat2fit.model;

public interface IMessageToAdmin {
    void saveMessage(MessageToAdmin messageToAdmin) throws DBException;

    MessageToAdmin getMessageToAdmin(int id) throws DBException;

    void deleteMessage(int id) throws DBException;

    MessageToAdmin[] getAllMessageToAdmin() throws DBException;

}
