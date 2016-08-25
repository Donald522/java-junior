package com.acme.edu;

import java.util.Objects;

/**
 * Created by Java_10 on 25.08.2016.
 */
public abstract class LoggerBaseClass {

    protected int type;

    protected abstract void log(int message);

    protected abstract void log(byte message);

    protected abstract void log(char message);

    protected abstract void log(String message);

    protected abstract void log(boolean message);

    protected abstract void log(Object message);

    protected abstract void flush();

    public int getType() {
        return type;
    }

}
