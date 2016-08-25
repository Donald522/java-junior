package com.acme.edu.decorators;

/**
 * Created by Dmitriy on 26.08.2016.
 */
public class ObjectDecorator implements Decorator {
    private static ObjectDecorator itSelf = null;

    public static ObjectDecorator getInstance() {
        if(itSelf == null) {
            itSelf = new ObjectDecorator();
        }
        return itSelf;
    }
    @Override
    public String decorate(String message) {
        return "reference: " + message + System.lineSeparator();
    }
}
