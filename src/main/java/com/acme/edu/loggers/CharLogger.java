package com.acme.edu.loggers;

/**
 * Created by anton on 25.08.16.
 */
public class CharLogger extends SimpleLogger {
    private static CharLogger itSelf;
    public static CharLogger getInstance() {
        if(itSelf == null) {
            itSelf = new CharLogger();
        }
        return itSelf;
    }

    @Override
    public void log(Object message) {
        this.stateLogger = String.valueOf((char)message);
    }
}
