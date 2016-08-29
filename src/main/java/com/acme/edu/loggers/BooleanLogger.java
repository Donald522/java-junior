package com.acme.edu.loggers;

import com.acme.edu.constants.Constants;

import javax.swing.*;

/**
 * Created by Dmitriy on 26.08.2016.
 */
public class BooleanLogger extends SimpleLogger {
    public BooleanLogger() {
        this.loggerType = Constants.BOOLEAN;
    }
//    private static BooleanLogger itSelf;
//    public static BooleanLogger getInstance() {
//        if(itSelf == null) {
//            itSelf = new BooleanLogger();
//        }
//        return itSelf;
//    }

    @Override
    public void log(Object message) {
        this.stateLogger = message.toString();
    }
}
