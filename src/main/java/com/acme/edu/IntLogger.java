package com.acme.edu;

/**
 * Created by Java_10 on 25.08.2016.
 */
public class IntLogger extends LoggerBaseClass {

    private int accNumbersStream = 0;
    private boolean numberStreamOn = false;
    private int maxIntCounter = 0;

    @Override
    protected void log(int message) {
        type = 1;
        numberStreamOn = true;
        if(Integer.MAX_VALUE - accNumbersStream >= message) {
            accNumbersStream += message;
        } else {
            accNumbersStream -= (Integer.MAX_VALUE - message);
            maxIntCounter++;
        }
    }

    @Override
    protected void log(byte message) {

    }

    @Override
    protected void log(char message) {

    }

    @Override
    protected void log(String message) {

    }

    @Override
    protected void log(boolean message) {

    }

    @Override
    protected void log(Object message) {

    }

    @Override
    protected void flush() {

    }
}
