package com.fat2fit.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.List;

public class HibernateUserDAO implements IUser {
    SessionFactory factory;

    public HibernateUserDAO() {
        factory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    @Override
    public User getUser(String username) throws DBException {

        Session anotherSession = factory.openSession();
        anotherSession.beginTransaction();
        User user = (User) anotherSession.get(User.class, username);
        anotherSession.close();

        return user;
    }

    @Override
    public User[] getUseres() throws DBException {

        Session anotherSession = factory.openSession();
        anotherSession.beginTransaction();
        List users = anotherSession.createQuery("from users").list();// hql
        anotherSession.close();
        System.out.println("There are " + users.size() + " user(s)");
        User[] returnArr = new User[users.size()];
        returnArr = (User[]) users.toArray(returnArr);
        return returnArr;
    }
}
