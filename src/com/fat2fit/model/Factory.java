package com.fat2fit.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * The Singleton design. helps to the model classes use with only ONE instance of SessionFactory.
 */
public class Factory {
    private static Factory factoryInstance; //singleton
    /**
     * The Factory.
     */
    SessionFactory factory;

    private Factory() {
        try {
            factory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Initial SessionFactory creation failed.");
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Gets session factory instance.
     *
     * @return the session factory instance
     */
    public static Factory getFactoryInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public SessionFactory getFactory() {
        return factory;
    }
}
