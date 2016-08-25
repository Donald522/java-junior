package com.acme.edu.loggers;

/**
 * Created by Dmitriy on 26.08.2016.
 */
public class ObjectLogger extends SimpleLogger {
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
