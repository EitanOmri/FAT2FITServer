package com.fat2fit.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUserDAO implements IUser {
    SessionFactory factory;

    public HibernateUserDAO() {   factory = new AnnotationConfiguration().configure().buildSessionFactory();

    }
}
