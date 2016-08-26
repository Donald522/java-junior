package com.acme.edu.loggers;

import com.acme.edu.constants.Constants;

/**
 * Created by Dmitriy on 26.08.2016.
 */
public class ObjectLogger extends SimpleLogger {
    private ObjectLogger() {
        this.loggerType = Constants.OBJECT;
    }
    private static ObjectLogger itSelf;
    public static ObjectLogger getInstance() {
        if(itSelf == null) {
            itSelf = new ObjectLogger();
        }
        return itSelf;
    }

    @Override
    public void log(Object message) {
        stateLogger = message.toString();
    }
}
