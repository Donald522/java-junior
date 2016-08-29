package com.acme.edu.loggers;

import com.acme.edu.constants.Constants;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.exceptions.AppendException;
import com.acme.edu.exceptions.DecorateException;
import com.acme.edu.savers.Saver;

/**
 * Created by anton on 25.08.16.
 */
public class StringLogger extends Logger {

    private  String lastLoggedString = null;
    private int counterOfSameSimultaneousStrings = 1;
//    private boolean stringsStream = false;

    public StringLogger() {
        setDefaultDecorator();
        this.loggerType = Constants.STRING;
    }

    public String getLastLoggedString() {
        return lastLoggedString;
    }

    @Override
    protected void setDefaultDecorator() {
        this.decorator = new Decorator("string: ", "");
    }

    @Override
    public void log(Object message) throws DecorateException, AppendException {
        if(message.equals(lastLoggedString)) {
            counterOfSameSimultaneousStrings++;
        } else {
            lastLoggedString = message.toString();
//            stringsStream = true;
        }
    }

    @Override
    public void clear() {
        lastLoggedString = null;
//        stringsStream = false;
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
        return decorator.decorate(resultString);
    }
}