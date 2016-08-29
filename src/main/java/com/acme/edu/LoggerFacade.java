package com.acme.edu;

import com.acme.edu.decorators.*;
import com.acme.edu.exceptions.AppendException;
import com.acme.edu.exceptions.DecorateException;
import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.loggers.*;
import com.acme.edu.savers.Saver;

import java.util.ArrayList;

import static com.acme.edu.constants.Constants.*;

/**
 * Created by anton on 25.08.16.
 * Main class of Logger.
 * It decides on the further processing of the message.
 * It connects decorator, saver and specific logger for each message.
 * Takes messages and save them in special format.
 */
public class LoggerFacade {

    private ArrayList<Logger> loggers;
    private Logger logger;
    private Saver[] savers;
    private Decorator decorator;

    private int currentLoggerType;
    private boolean decoratorByDefault = true;

    /**
     * Constructor for LoggerFacade
     * Create the pool with default loggers
     * If error occurs during creation then the pool will not be full which can cause the exception
     * Create an array of Savers
     * @param savers
     */
    public LoggerFacade(Saver... savers) {

        loggers = new ArrayList<>();
        try {
            addLoggers(
                    new IntLogger(),
                    new ByteLogger(),
                    new CharLogger(),
                    new BooleanLogger(),
                    new StringLogger(),
                    new ObjectLogger()
            );
        } catch (LoggerException e) {}

        this.savers = new Saver [savers.length];
        for (int i = 0; i < savers.length; i++) {
            this.savers[i] = savers[i];
        }
    }

    /**
     * Public method to add the passed loggers to the pool
     * If passed to the method loggers have the same type as loggers are already in the pool, they will be replaced.
     * @param loggers
     * @throws LoggerException
     */
    public void addLoggers(Logger... loggers) throws LoggerException {
        boolean attemptToSetNullLogger = false;
        Logger existedLogger;
        for(Logger logger : loggers) {
            if(logger == null) {
                attemptToSetNullLogger = true;
                continue;
            }
            try {
                existedLogger = findLogger(logger.getLoggerType());
                this.loggers.remove(existedLogger);
            }
            catch (LoggerException e) {}
            this.loggers.add(logger);
        }
        if(attemptToSetNullLogger) {
            throw new LoggerException("There was attempt to add null Logger. It was canceled.");
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

    /**
     * Log message by type.
     *
     * @param message - object of message, which is needed to log.
     *                Input types are: int, byte, String, boolean, Object, char.
     */
    public void log(Object message) throws LoggerException {

        if(message instanceof Integer) {
            setAndCheckType(INT);
            logger = findLogger(INT);
            if (decoratorByDefault) {
                decorator = IntDecorator.getInstance();
            }
        } else if(message instanceof Byte) {
            setAndCheckType(BYTE);
            logger = findLogger(BYTE);
            if (decoratorByDefault) {
                decorator = IntDecorator.getInstance();
            }
            message = ((Byte)message).intValue();
        } else if(message instanceof Character) {
            setAndCheckType(CHAR);
            logger = findLogger(CHAR);
            if (decoratorByDefault) {
                decorator = CharDecorator.getInstance();
            }
        } else if(message instanceof Boolean) {
            setAndCheckType(BOOLEAN);
            logger = findLogger(BOOLEAN);
            if (decoratorByDefault) {
                decorator = BooleanDecorator.getInstance();
            }
        } else if(message instanceof String) {
            setAndCheckType(STRING);
            logger = findLogger(STRING);
            if (decoratorByDefault) {
                decorator = StringDecorator.getInstance();
            }
            logger.setDecorator(decorator);
            logger.setSaver(savers);
        } else {
            setAndCheckType(OBJECT);
            logger = findLogger(OBJECT);
            if (decoratorByDefault) {
                decorator = ObjectDecorator.getInstance();
            }
        }
        try {
            logger.log(message);
        } catch (DecorateException | AppendException e) {
            throw new LoggerException("Error in logging message", e);
        }
    }

    /**
     * Set the current type og logger. Handle the type changing and flush if it is.
     * @param type
     * @throws LoggerException
     */
    private void setAndCheckType(int type) throws LoggerException {
        currentLoggerType = type;
        if(logger != null && logger.getLoggerType() != currentLoggerType) {
            flush();
        }
    }

    /**
     * Find necessary logger in the pool loggers. If such doesn't exist then throw LoggerException
     * @param nextType - type of logger need to be found
     * @return - ref to the found logger
     * @throws LoggerException
     */
    private Logger findLogger(int nextType) throws LoggerException {
        Logger currentLogger = null;
        for (Logger logger : loggers) {
            if (logger.getLoggerType() == nextType) {
                currentLogger = logger;
                break;
            }
        }
        if(currentLogger == null) {
            throw new LoggerException("Can't find necessary logger");
        }
        return currentLogger;
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
