package com.fat2fit.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.List;

public class HibernateUserDAO implements IUser {
    Factory factoryInstance;

    public HibernateUserDAO() {
        factoryInstance = Factory.getFactoryInstance();
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
    public User[] getUseres() throws DBException {

        Session session = factoryInstance.getFactory().openSession();

        session.beginTransaction();
        List users = session.createQuery("FROM User").list();// hql
        session.close();
        System.out.println("There are " + users.size() + " user(s)");
        User[] returnArr = new User[users.size()];
        returnArr = (User[]) users.toArray(returnArr);
        return returnArr;
    }
    public void saveUser(User user) throws DBException {

        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
