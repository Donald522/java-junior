package com.acme.edu.loggers;

import com.acme.edu.constants.Constants;
import com.acme.edu.decorators.Decorator;

/**
 * Created by Dmitriy on 26.08.2016.
 */
public class ObjectLogger extends SimpleLogger {
    public ObjectLogger() {
        setDefaultDecorator();
        this.loggerType = Constants.OBJECT;
    }

    @Override
    protected void setDefaultDecorator() {
        this.decorator = new Decorator("reference: ", "");
    }

    @Override
    public void log(Object message) {
        stateLogger = message.toString();
    }
}
