package com.acme.edu;

/**
 * Created by anton on 25.08.16.
 */
public abstract class Loger {

    protected int loggerType;

    protected Decorator decorator;
    protected Saver saver;

    public void setSaver(Saver saver) {
        this.saver = saver;
    }

    public void setDecorator(Decorator decorator) {
        this.decorator = decorator;
    }

    public abstract void log(Object message);

    public abstract void clear();

    public abstract String getData();
}
