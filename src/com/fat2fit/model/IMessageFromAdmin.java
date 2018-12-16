package com.fat2fit.model;

public interface IMessageFromAdmin {

    void saveMessage(MessageFromAdmin messageFromAdmin) throws DBException;

    MessageFromAdmin getMessageFromAdmin(int id) throws DBException;

    void deleteMessage(int id) throws DBException;

    MessageFromAdmin[] getAllMessageFromAdmin() throws DBException;

}
