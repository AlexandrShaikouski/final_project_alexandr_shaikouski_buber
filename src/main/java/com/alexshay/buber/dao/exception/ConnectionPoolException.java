package com.alexshay.buber.dao.exception;

/**
 * Connection Pool Exception
 */
public class ConnectionPoolException extends Exception {

    private Object[] parameters;

    public ConnectionPoolException(String message, Throwable cause, Object ... objects) {
        super(message, cause);
        parameters = objects;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Object[] getParameters() {
        return parameters;
    }

}
