package com.fat2fit.model;

import org.hibernate.Session;

import java.util.List;

/**
 * The type Hibernate user dao.
 */
public class HibernateUserDAO implements IUser {
    private Factory factoryInstance;

    /**
     * Instantiates a new Hibernate user dao.
     */
    public HibernateUserDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public User[] getUseresWithOutAdmin() throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List users = session.createQuery("FROM com.fat2fit.model.User where isManager=0").list();// hql
        session.close();
        User[] returnArr = new User[users.size()];
        returnArr = (User[]) users.toArray(returnArr);
        return returnArr;
    }

    @Override
    public boolean isManager(String username) throws DBException {
        User user = getUser(username);
        if (user == null)
            return false;
        if (user.getIsManager() == 1)
            return true;
        return false;
    }

    @Override
    public void addAdmin(String username) throws DBException {
        User user = getUser(username);
        if (user != null) {
            user.setIsManager(1);

            Session session = factoryInstance.getFactory().openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public User getUser(String username) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, username);
        session.close();
        return user;
    }

    @Override
    public void removeUser(String username) throws DBException {
        if (isUserExsits(username)) {
            User user = getUser(username);
            Session session = factoryInstance.getFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public User[] getUseres() throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List users = session.createQuery("FROM com.fat2fit.model.User").list();// hql
        session.close();
        User[] returnArr = new User[users.size()];
        returnArr = (User[]) users.toArray(returnArr);
        return returnArr;
    }

    @Override
    public boolean isUserExsits(String username) throws DBException {
        if (getUser(username) != null)
            return true;
        return false;
    }

    @Override
    public void saveUser(User user) throws DBException {
        if (!isUserExsits(user.getUsername())) {
            Session session = factoryInstance.getFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean testLogin(String username, String password) throws DBException {
        User user = getUser(username);
        if (user != null)
            if (password.equals(user.getPassword()))
                return true;
        return false;
    }

    @Override
    public void updateUser(String username, double weight, double height) throws DBException {
        User user = getUser(username);
        if (user != null) {
            user.setHeight(height);
            user.setWeight(weight);

            Session session = factoryInstance.getFactory().openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.close();
        }
    }
}
