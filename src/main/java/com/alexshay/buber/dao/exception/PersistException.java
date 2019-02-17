package com.alexshay.buber.dao.exception;

/**
 * Persist Exception
 */
public class PersistException extends Exception {

    private Object[] parameters;

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistException(String message) {
        super(message);
    }

    public void setParams(Object[] parameters) {
        this.parameters = parameters;
    }

    public Object[] getParams() {
        return parameters;
    }

}
