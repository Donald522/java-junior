package com.acme.edu;

/**
 * Created by anton on 25.08.16.
 */
public class IntLogger extends Loger {

    private int accIntStream = 0;
    private int maxIntCounter = 0;
    private boolean intStreamOn = false;

    private static IntLogger itSelf = null;

    private IntLogger() {}

    public static IntLogger getInstance() {
        if(itSelf == null) {
            itSelf = new IntLogger();
        }
        return itSelf;
    }

    @Override
    public void log(Object msg) {
        int message = (int)msg;
        loggerType = Constants.INT;
        intStreamOn = true;
        if(Integer.MAX_VALUE - accIntStream >= message) {
            accIntStream += message;
        } else {
            accIntStream -= (Integer.MAX_VALUE - message);
            maxIntCounter++;
        }
    }
    public void clear() {
        accIntStream = 0;
        maxIntCounter = 0;
        intStreamOn = false;
    }

    @Override
    public String getData() {
        String resultString = "";
        if(maxIntCounter > 0) {
            resultString = String.valueOf(accIntStream) + System.lineSeparator();
            while(maxIntCounter != 0) {
                resultString += String.valueOf(Integer.MAX_VALUE) + System.lineSeparator();
                maxIntCounter--;
            }
        } else {
            resultString = String.valueOf(accIntStream);
        }
        return resultString;
    }

}
