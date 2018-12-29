package com.fat2fit.model;

public class DBException extends Exception {
    public DBException(String msg) {
        super(msg);
    }

    public DBException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
