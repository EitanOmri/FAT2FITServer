package com.fat2fit.model;

public interface IUser  {
    User getUser (String username) throws DBException;
    User [] getUseres () throws DBException;
    public void saveUser(User user) throws DBException;
}
