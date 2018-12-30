package com.fat2fit.model;

/**
 * The type Db exception.
 */
public class DBException extends Exception {
    /**
     * Instantiates a new Db exception.
     *
     * @param msg the msg
     */
    public DBException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new Db exception.
     *
     * @param msg       the msg
     * @param throwable the throwable
     */
    public DBException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
