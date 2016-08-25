package com.acme.edu;

/**
 * Created by Java_9 on 25.08.2016.
 */
public class MessageDecorator implements Decorator {
    private final String prefix;
    private final String postfix;

    public MessageDecorator(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    @Override
    public String decorate(String message) {
        return prefix + message + postfix;
    }
}
