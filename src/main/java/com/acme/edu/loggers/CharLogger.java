package com.acme.edu.loggers;

import com.acme.edu.constants.Constants;
import com.acme.edu.decorators.Decorator;

/**
 * Created by anton on 25.08.16.
 */
public class CharLogger extends SimpleLogger {
    public CharLogger() {
        setDefaultDecorator();
        this.loggerType = Constants.CHAR;
    }

    @Override
    protected void setDefaultDecorator() {
        this.decorator = new Decorator("char: ", "");
    }

    @Override
    public void log(Object message) {
        this.stateLogger = String.valueOf((char)message);
    }
}
