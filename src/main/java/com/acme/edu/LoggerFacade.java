package com.acme.edu;

import com.acme.edu.decorators.*;
import com.acme.edu.exceptions.AppendException;
import com.acme.edu.exceptions.DecorateException;
import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.loggers.*;
import com.acme.edu.savers.ConsoleSaver;
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
     * Public method to add the passed loggers to the pool.
     * If passed to the method loggers have the same type as loggers are already in the pool, they will be replaced.
     * @param loggers
     * @throws LoggerException
     */
    public void addLoggers(Logger... loggers) throws LoggerException {
        Logger existedLogger;
        for(Logger logger : loggers) {
            if(logger == null) {
                throw new LoggerException("There was attempt to add null Logger. It was canceled.");
            }
            try {
                existedLogger = findLogger(logger.getLoggerType());
                this.loggers.remove(existedLogger);
            }
            catch (LoggerException e) {}
            this.loggers.add(logger);
        }
    }

    /**
     * Set the current type of logger and logger instance. Handle the type changing and flush if it is.
     * @param type
     * @throws LoggerException
     */
    private void checkTypeAndSetLogger(int type) throws LoggerException {
        currentLoggerType = type;
        if(logger != null && logger.getLoggerType() != currentLoggerType) {
            flush();
        }
        logger = findLogger(type);
    }

    /**
     * Choose current logger according to the type of message.
     * @param message
     */
    private Object setCurrentLogger(Object message) throws LoggerException {
        if(message instanceof Integer) {
            checkTypeAndSetLogger(INT);
        } else if(message instanceof Byte) {
            checkTypeAndSetLogger(BYTE);
            message = ((Byte)message).intValue();
        } else if(message instanceof Character) {
            checkTypeAndSetLogger(CHAR);
        } else if(message instanceof Boolean) {
            checkTypeAndSetLogger(BOOLEAN);
        } else if(message instanceof String) {
            checkTypeAndSetLogger(STRING);
            if (((StringLogger)logger).getLastLoggedString() != null &&
                    ((StringLogger)logger).getLastLoggedString() != (String)message) {
                flush();
            }
        } else {
            checkTypeAndSetLogger(OBJECT);
        }
        return message;
    }
    /**
     * Log message by type.
     * Use your own decorator before log message.
     * @param message - object of message, which is needed to log.
     *                Input types are: int, byte, String, boolean, Object, char.
     * @param decorator - object of type Decorator, which decorate each message.
     */
    public void log(Object message, Decorator decorator) throws LoggerException {
        if(decorator == null) {
            throw new DecorateException("Attempt to set null decorator");
        }
        Object typedMessage = setCurrentLogger(message);
        if (!logger.getDecorator().equals(decorator)) {
            flush();
        }
        logger.setDecorator(decorator);
        logWithCurrentLogger(typedMessage);
    }
    /**
     * Log message by type.
     *
     * @param message - object of message, which is needed to log.
     *                Input types are: int, byte, String, boolean, Object, char.
     */
    public void log(Object message) throws LoggerException {
        logWithCurrentLogger(setCurrentLogger(message));
    }

    /**
     * Log message with current logger.
     * @param message
     * @throws LoggerException
     */
    private void logWithCurrentLogger(Object message) throws  LoggerException {
        try {
            logger.log(message);
        } catch (LoggerException e) {
            throw new LoggerException("Error in logging message", e);
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
            decoratedMessage = logger.getData();
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
