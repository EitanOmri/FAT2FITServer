package com.fat2fit.model;

public interface IMessageToAdmin {
    public void saveMessage(MessageToAdmin messageToAdmin) throws DBException;
    public MessageToAdmin getMessageToAdmin(int id) throws  DBException;
    public void deleteMessage(int id) throws DBException;
    public MessageToAdmin [] getAllMessageToAdmin() throws DBException;

}
