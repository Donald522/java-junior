package com.acme.edu;

/**
 * Created by anton on 25.08.16.
 */
public class StringDecorator implements Decorator{

    private static StringDecorator itSelf = null;

    private StringDecorator() {}

    public static StringDecorator getInstance() {
        if(itSelf == null) {
            itSelf = new StringDecorator();
        }
        return itSelf;
    }

    @Override
    public String decorate(String message) {
        return "string: " + message;
    }
}
