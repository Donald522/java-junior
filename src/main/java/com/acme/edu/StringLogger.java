package com.acme.edu;

/**
 * Created by anton on 25.08.16.
 */
public class StringLogger extends Loger{

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

    }

    @Override
    public void clear() {

    }

    @Override
    public String getData() {
        return null;
    }
}
