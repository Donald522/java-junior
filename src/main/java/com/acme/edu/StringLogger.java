package com.acme.edu;

/**
 * Created by anton on 25.08.16.
 */
public class StringLogger extends Loger{

    private  String lastLoggedString = null;
    private int counterOfSameSimultaneousStrings = 1;
    private boolean stringsStream = false;

    private static StringLogger itSelf = null;

    private StringLogger() {}

    public static StringLogger getInstance() {
        if(itSelf == null) {
            itSelf = new StringLogger();
        }
        return itSelf;
    }

    @Override
    public void log(Object message) {
        loggerType = Constants.STRING;
        if(message.equals(lastLoggedString)) {
            counterOfSameSimultaneousStrings++;
        } else {
            if(stringsStream) {
                saver.save(decorator.decorate(getData()));
                clear();
            }
            lastLoggedString = message.toString();
            stringsStream = true;
        }
    }

    @Override
    public void clear() {
        lastLoggedString = null;
        stringsStream = false;
        counterOfSameSimultaneousStrings = 1;
    }

    @Override
    public String getData() {
        String resultString;
        if(counterOfSameSimultaneousStrings > 1) {
            resultString = lastLoggedString + " (x" + counterOfSameSimultaneousStrings + ")";
        } else {
            resultString = lastLoggedString;
        }
        return resultString;
    }
}
