package com.acme.edu.loggers;

import com.acme.edu.exceptions.AppendException;
import com.acme.edu.exceptions.DecorateException;
import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.savers.Saver;
import com.acme.edu.decorators.Decorator;

/**
 * Abstract Father of each specific logger.
 * Created by anton on 25.08.16.
 */
public abstract class Logger {

    protected int loggerType;

    protected Decorator decorator;

    protected void setDefaultDecorator() {
        this.decorator = new Decorator("primitive: ", "");
    }

    public void setDecorator(Decorator decorator) {
        this.decorator = decorator;
    }

    public Decorator getDecorator() { return this.decorator; }

    /**
     * The primary method of processing a message in accordance with the received message type.
     * @param message
     */
    public abstract void log(Object message) throws LoggerException;

    /**
     * Clear state of each specific logger.
     */
    public abstract void clear();

    /**
     * Return result of logging message.
     * @return - String which depends on previous stream of messages.
     */
    public abstract String getData();

    public int getLoggerType() { return loggerType; }
}
