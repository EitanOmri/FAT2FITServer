package com.fat2fit.model;

public interface IMessageFromAdmin {

    public void saveMessage(MessageFromAdmin messageFromAdmin) throws DBException;
    public MessageFromAdmin getMessageFromAdmin(int id) throws  DBException;
    public void deleteMessage(int id) throws DBException;
    public MessageFromAdmin [] getAllMessageFromAdmin() throws DBException;

}
