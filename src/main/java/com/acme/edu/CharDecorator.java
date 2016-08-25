package com.acme.edu;

/**
 * Created by anton on 25.08.16.
 */
public class CharDecorator implements Decorator{

    private static CharDecorator itSelf = null;

    private CharDecorator() {}

    public static CharDecorator getInstance() {
        if(itSelf == null) {
            itSelf = new CharDecorator();
        }
        return itSelf;
    }

    @Override
    public String decorate(String message) {
        return "char: " + message;
    }
}
