package com.acme.edu.loggers;

import com.acme.edu.constants.Constants;

/**
 * Class for logging int
 * Created by anton on 25.08.16.
 */
public class IntLogger extends Logger {

    protected int accIntStream = 0;
    private int maxIntCounter = 0;
    private boolean intStreamOn = false;

    int maxValue;
    int minValue;

    public IntLogger() {
        setDefaultDecorator();
        this.loggerType = Constants.INT;
        this.maxValue = Integer.MAX_VALUE;
        this.minValue = Integer.MIN_VALUE;
    }

    protected int computeAccSumm(int message) {
        int accSum = accIntStream + message;
        return accSum;
    }

    @Override
    public void log(Object msg) {
        int message = (int)msg;
        int accSumm = computeAccSumm(message);
        intStreamOn = true;
        if (accSumm <= 0 && accIntStream > 0 && message >= 0) {
            maxIntCounter++;
            accIntStream -= (maxValue - message);
        } else if (accSumm > 0 && accIntStream < 0 && message < 0) {
            maxIntCounter--;
            accIntStream -= (minValue - message);
        } else {
            accIntStream += message;
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
        int resultMax = maxValue;
        if (maxIntCounter < 0) {
            resultMax = minValue;
            maxIntCounter = Math.abs(maxIntCounter);
        }
        if(intStreamOn) {
            if (maxIntCounter > 0) {
                resultString = String.valueOf(accIntStream) + System.lineSeparator();
                while (maxIntCounter != 1) {
                    resultString += String.valueOf(resultMax) + System.lineSeparator();
                    maxIntCounter--;
                }
                resultString += String.valueOf(resultMax);
            } else {
                resultString = String.valueOf(accIntStream);
            }
        }
        return decorator.decorate(resultString);
    }

}
