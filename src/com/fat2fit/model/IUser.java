package com.fat2fit.model;

public interface IUser {
    User getUser(String username) throws DBException;

    User[] getUseres() throws DBException;

    void saveUser(User user) throws DBException;

    boolean testLogin(String username, String password) throws DBException;

    boolean isUserExsits(String username) throws DBException;

    void updateUser(String username, double weight, double height) throws DBException;

    void removeUser(String username) throws DBException;

    boolean isManager(String username) throws DBException;

    void addAdmin(String username) throws DBException;

    User[] getUseresWithOutAdmin() throws DBException;


}
