package com.fat2fit.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class Factory {
    private static Factory factoryInstance; //singleton
    SessionFactory factory;

    private Factory() {
        try {
            factory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {

            System.out.println("Initial SessionFactory creation failed.");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Factory getFactoryInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    public SessionFactory getFactory() {
        return factory;
    }
}
