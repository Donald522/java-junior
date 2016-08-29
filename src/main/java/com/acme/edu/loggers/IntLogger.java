package com.acme.edu.loggers;

import com.acme.edu.constants.Constants;

/**
 * Class for logging int
 * Created by anton on 25.08.16.
 */
public class IntLogger extends Logger {

    private int accIntStream = 0;
    private int maxIntCounter = 0;
    private boolean intStreamOn = false;

    int maxVaule;

    public IntLogger() {
        setDefaultDecorator();
        this.loggerType = Constants.INT;
        this.maxVaule = Integer.MAX_VALUE;
    }

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
                while (maxIntCounter != 1) {
                    resultString += String.valueOf(maxVaule) + System.lineSeparator();
                    maxIntCounter--;
                }
                resultString += String.valueOf(maxVaule);
            } else {
                resultString = String.valueOf(accIntStream);
            }
        }
        return decorator.decorate(resultString);
    }

}
