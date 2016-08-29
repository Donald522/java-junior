package com.acme.edu.exceptions;

/**
 * Created by Java_10 on 29.08.2016.
 */
public class AppendException extends LoggerException {
    public AppendException() {
        super();
    }

    public AppendException(String message) {
        super(message);
    }

    public AppendException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppendException(Throwable cause) {
        super(cause);
    }

    protected AppendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
