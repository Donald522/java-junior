package com.acme.edu.loggers;

import com.acme.edu.constants.Constants;
import com.acme.edu.decorators.Decorator;

/**
 * Created by anton on 25.08.16.
 */
public class IntLogger extends Logger {

    protected int accIntStream = 0;
    protected int maxIntCounter = 0;
    protected boolean intStreamOn = false;

    protected int maxVaule;

    public IntLogger() {
        setDefaultDecorator();
        this.loggerType = Constants.INT;
        this.maxVaule = Integer.MAX_VALUE;
    }

    @Override
    protected void setDefaultDecorator() {
        this.decorator = new Decorator("reference: ", "");
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
                while (maxIntCounter != 0) {
                    resultString += String.valueOf(maxVaule) + System.lineSeparator();
                    maxIntCounter--;
                }
            } else {
                resultString = String.valueOf(accIntStream);
            }
        }
        return decorator.decorate(resultString);
    }

}
