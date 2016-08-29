package com.acme.edu.loggers;

/**
 * Created by Dmitriy on 26.08.2016.
 */
public abstract class SimpleLogger extends Logger {
    protected String stateLogger;

    @Override
    public void clear() {
        stateLogger = "";
    }

    @Override
    public String getData() {
        return decorator.decorate(stateLogger);
    }
}
