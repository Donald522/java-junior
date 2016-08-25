package com.acme.edu;

/**
 * Created by anton on 25.08.16.
 */
public abstract class Loger {

    protected int loggerType;

    public abstract void log(Object message);

    public abstract void clear();

    public abstract String getData();
}
