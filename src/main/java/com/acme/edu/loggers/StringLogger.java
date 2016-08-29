package com.acme.edu.loggers;

import com.acme.edu.constants.Constants;
import com.acme.edu.exceptions.AppendException;
import com.acme.edu.exceptions.DecorateException;
import com.acme.edu.savers.Saver;

/**
 * Created by anton on 25.08.16.
 */
public class StringLogger extends Logger {

    private  String lastLoggedString = null;
    private int counterOfSameSimultaneousStrings = 1;
    private boolean stringsStream = false;

    private static StringLogger itSelf = null;

    private StringLogger() {
        this.loggerType = Constants.STRING;
    }

    public static StringLogger getInstance() {
        if(itSelf == null) {
            itSelf = new StringLogger();
        }
        return itSelf;
    }

    @Override
    public void log(Object message) throws DecorateException, AppendException {
        loggerType = Constants.STRING;
        if(message.equals(lastLoggedString)) {
            counterOfSameSimultaneousStrings++;
        } else {
            if(stringsStream) {
                String decoratedMessage;
                try {
                    decoratedMessage = decorator.decorate(getData());
                } catch (NullPointerException e) {
                    throw new DecorateException("Null pointer to decorator", e);
                }
                for(Saver saver : savers) {
                    try {
                        saver.save(decoratedMessage);
                    } catch (AppendException e) {
                        throw e;
                    }
                }
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