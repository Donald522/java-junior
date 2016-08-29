package com.acme.edu.loggers;

import com.acme.edu.constants.Constants;

/**
 * Created by Dmitriy on 26.08.2016.
 */
public class BooleanLogger extends SimpleLogger {
    public BooleanLogger() {
        setDefaultDecorator();
        this.loggerType = Constants.BOOLEAN;
    }

    @Override
    public void log(Object message) {
        this.stateLogger = message.toString();
    }
}
