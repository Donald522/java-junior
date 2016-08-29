package com.acme.edu.exceptions;

/**
 * Created by Java_10 on 29.08.2016.
 */
public class DecorateException extends LoggerException {
    public DecorateException() {
        super();
    }

    public DecorateException(String message) {
        super(message);
    }

    public DecorateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DecorateException(Throwable cause) {
        super(cause);
    }

    protected DecorateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
