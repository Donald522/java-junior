package com.acme.edu.loggers;

import com.acme.edu.constants.Constants;

/**
 * Created by anton on 25.08.16.
 */
public class IntLogger extends Logger {

    protected int accIntStream = 0;
    protected int maxIntCounter = 0;
    protected boolean intStreamOn = false;

//    private static IntLogger itSelf = null;

    protected int maxVaule;

    public IntLogger() {
        this.loggerType = Constants.INT;
        this.maxVaule = Integer.MAX_VALUE;
    }
//
//    public static IntLogger getInstance() {
//        if(itSelf == null) {
//            itSelf = new IntLogger();
//        }
//        return itSelf;
//    }

    @Override
    public void log(Object msg) {
        int message = (int)msg;
        intStreamOn = true;
        if(maxVaule - accIntStream >= message) {
            accIntStream += message;
        } else {
            accIntStream -= (maxVaule - message);
            maxIntCounter++;
        }
    }

    @Override
    public void clear() {
        accIntStream = 0;
        maxIntCounter = 0;
        intStreamOn = false;
    }

    @Override
    public String getData() {
        String resultString = "";
        if(intStreamOn) {
            if (maxIntCounter > 0) {
                resultString = String.valueOf(accIntStream) + System.lineSeparator();
                while (maxIntCounter != 0) {
                    resultString += String.valueOf(maxVaule) + System.lineSeparator();
                    maxIntCounter--;
                }
            } else {
                resultString = String.valueOf(accIntStream);
            }
        }
        return resultString;
    }

}
