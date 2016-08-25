package com.acme.edu.loggers;

/**
 * Created by Dmitriy on 26.08.2016.
 */
public class BooleanLogger extends SimpleLogger {
    private static BooleanLogger itSelf;
    public static BooleanLogger getInstance() {
        if(itSelf == null) {
            itSelf = new BooleanLogger();
        }
        return itSelf;
    }

    @Override
    public void log(Object message) {
        this.stateLogger = message.toString();
    }
}
