package com.acme.edu.decorators;

/**
 * Created by Dmitriy on 26.08.2016.
 */
public class BooleanDecorator implements Decorator {
    private static BooleanDecorator itSelf = null;

    public static BooleanDecorator getInstance() {
        if(itSelf == null) {
            itSelf = new BooleanDecorator();
        }
        return itSelf;
    }
    @Override
    public String decorate(String message) {
        return "primitive: " + message + System.lineSeparator();
    }
}
