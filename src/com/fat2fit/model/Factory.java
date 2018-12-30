package com.fat2fit.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * The type Factory.
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
     * Gets factory instance.
     *
     * @return the factory instance
     */
    public static Factory getFactoryInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    /**
     * Gets factory.
     *
     * @return the factory
     */
    public SessionFactory getFactory() {
        return factory;
    }
}
