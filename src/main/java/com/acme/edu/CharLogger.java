package com.acme.edu;

/**
 * Created by anton on 25.08.16.
 */
public class CharLogger extends Loger{

    private char lastLoggedCharacter;

    private static CharLogger itSelf = null;

    private CharLogger() {}

    public static CharLogger getInstance() {
        if(itSelf == null) {
            itSelf = new CharLogger();
        }
        return itSelf;
    }

    @Override
    public void log(Object message) {
        lastLoggedCharacter = (char)message;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getData() {
        return String.valueOf(lastLoggedCharacter);
    }
}
