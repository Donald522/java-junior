package com.acme.edu;

import com.acme.edu.decorators.*;
import com.acme.edu.exceptions.AppendException;
import com.acme.edu.exceptions.DecorateException;
import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.loggers.*;
import com.acme.edu.savers.Saver;

/**
 * Created by anton on 25.08.16.
 * Main class of Logger.
 * It decides on the further processing of the message.
 * It connects decorator, saver and specific logger for each message.
 * Takes messages and save them in special format.
 */
public class LoggerFacade {

    private Logger logger;
    private Saver[] savers;
    private Decorator decorator;

    private boolean decoratorByDefault = true;

    public LoggerFacade(Saver... savers) {
        this.savers = new Saver [savers.length];
        for (int i = 0; i < savers.length; i++) {
            this.savers[i] = savers[i];
        }
    }

    /**
     * Use your own decorator before log message.
     * @param decorator - object of type Decorator, which decorate each message.
     */
    public void setDecorator(Decorator decorator) throws DecorateException {
        if(decorator == null) {
            throw new DecorateException("Attempt to set null decorator");
        }
        this.decorator = decorator;
        decoratorByDefault = false;
    }

    private void defindloggerAndDecorator(Logger nextLogger, Decorator nextDecorator) throws LoggerException {
        if (logger != null && logger.getLoggerType() != nextLogger.getLoggerType()) {
            flush();
        }
        logger = nextLogger;
        if (decoratorByDefault) {
            decorator = nextDecorator;
        }
    }

    /**
     * Log message by type.
     *
     * @param message - object of message, which is needed to log.
     *                Input types are: int, byte, String, boolean, Object, char.
     */
    public void log(Object message) throws LoggerException {

        if(message instanceof Integer) {
            defindloggerAndDecorator(IntLogger.getInstance(), IntDecorator.getInstance());
        } else if(message instanceof Byte) {
            defindloggerAndDecorator(ByteLogger.getInstance(), IntDecorator.getInstance());
            message = ((Byte)message).intValue();
        } else if(message instanceof Character) {
            defindloggerAndDecorator(CharLogger.getInstance(), CharDecorator.getInstance());
        } else if(message instanceof Boolean) {
            defindloggerAndDecorator(BooleanLogger.getInstance(), BooleanDecorator.getInstance());
        } else if(message instanceof String) {
            defindloggerAndDecorator(StringLogger.getInstance(), StringDecorator.getInstance());
            logger.setSaver(this.savers);
            logger.setDecorator(decorator);
        } else {
            defindloggerAndDecorator(ObjectLogger.getInstance(), ObjectDecorator.getInstance());
        }
        try {
            logger.log(message);
        } catch (DecorateException | AppendException e) {
            throw new LoggerException("Error in logging message", e);
        }
    }

    /**
     * Create an event preceding the end of the association data of the same type into a single stream.
     */
    public void flush() throws LoggerException {
        String decoratedMessage;
        try {
            decoratedMessage = decorator.decorate(logger.getData());
        } catch (NullPointerException e) {
            throw new LoggerException("Can't decorate the result. Null pointer to decorator", e);
        }
        for(Saver saver : savers) {
            try {
                saver.save(decoratedMessage);
            } catch (AppendException e) {
                throw new LoggerException("Can't save the result", e);
            }
        }
        logger.clear();
    }

}
