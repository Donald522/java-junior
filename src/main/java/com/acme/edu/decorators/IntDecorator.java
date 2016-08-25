package com.acme.edu.decorators;

/**
 * Created by anton on 25.08.16.
 */
public class IntDecorator implements Decorator {

    private static IntDecorator itSelf = null;

    private IntDecorator() {}

    public static IntDecorator getInstance() {
        if(itSelf == null) {
            itSelf = new IntDecorator();
        }
        return itSelf;
    }

    @Override
    public String decorate(String message) {
        return "primitive: " + message;
    }
}
