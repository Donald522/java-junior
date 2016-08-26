package com.acme.edu.loggers;

import com.acme.edu.savers.Saver;
import com.acme.edu.decorators.Decorator;

/**
 * Abstract Father of each specific logger.
 * Created by anton on 25.08.16.
 */
public abstract class Logger {

    protected int loggerType;

    protected Decorator decorator;
    protected Saver[] savers;


    public void setSaver(Saver... savers) {
        this.savers = new Saver [savers.length];
        for (int i = 0; i < savers.length; i++) {
            this.savers[i] = savers[i];
        }
    }

    public void setDecorator(Decorator decorator) {
        this.decorator = decorator;
    }

    /**
     * The primary method of processing a message in accordance with the received message type.
     * @param message
     */
    public abstract void log(Object message);

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
