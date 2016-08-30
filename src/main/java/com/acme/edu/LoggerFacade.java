package com.acme.edu;

import com.acme.edu.decorators.Decorator;
import com.acme.edu.exceptions.AppendException;
import com.acme.edu.exceptions.DecorateException;
import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.loggers.BooleanLogger;
import com.acme.edu.loggers.ByteLogger;
import com.acme.edu.loggers.CharLogger;
import com.acme.edu.loggers.IntLogger;
import com.acme.edu.loggers.Logger;
import com.acme.edu.loggers.ObjectLogger;
import com.acme.edu.loggers.StringLogger;
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
    private Logger currentLogger;
    private Saver[] savers;

    /**
     * Constructor for LoggerFacade
     * Create the pool with default loggers
     * If error occurs during creation then the pool will not be full which can cause the exception
     * Create an array of Savers
     * @param savers
     */
    public LoggerFacade(Saver... savers) throws LoggerException {

        loggers = new ArrayList<>();
        addLoggers(
                new IntLogger(),
                new ByteLogger(),
                new CharLogger(),
                new BooleanLogger(),
                new StringLogger(),
                new ObjectLogger()
        );

        this.savers = new Saver [savers.length];
        System.arraycopy(savers, 0, this.savers, 0, savers.length);
    }

    /**
     * Public method to add the passed loggers to the pool.
     * If passed to the method loggers have the same type as loggers are already in the pool, they will be replaced.
     * @param loggers
     * @throws LoggerException
     */
    public void addLoggers(Logger... loggers) throws LoggerException {
        if(loggers == null) {
            throw new LoggerException("Null argument instead of loggers.");
        }
        Logger existedLogger;
        for(Logger logger : loggers) {
            if(logger == null) {
                throw new LoggerException("There was attempt to add null Logger. It was canceled.");
            }
            existedLogger = findLogger(logger.getLoggerType());
            if(existedLogger != null) {
                this.loggers.remove(existedLogger);
            }
            this.loggers.add(logger);
        }
    }

    /**
     * Set the current type of logger and logger instance. Handle the type changing and flush if it is.
     * @param type
     * @throws LoggerException
     */
    private void checkTypeAndSetLogger(int type) throws LoggerException {
        if(currentLogger != null && currentLogger.getLoggerType() != type) {
            flush();
        }
        currentLogger = findLogger(type);
    }

    /**
     * Choose current logger according to the type of message.
     * @param message
     */
    private Object setCurrentLogger(Object message) throws LoggerException {
        Object msg = message;
        if(message instanceof Integer) {
            checkTypeAndSetLogger(INT);
        } else if(message instanceof Byte) {
            checkTypeAndSetLogger(BYTE);
            msg = ((Byte)message).intValue();
        } else if(message instanceof Character) {
            checkTypeAndSetLogger(CHAR);
        } else if(message instanceof Boolean) {
            checkTypeAndSetLogger(BOOLEAN);
        } else if(message instanceof String) {
            checkTypeAndSetLogger(STRING);
            if (((StringLogger)currentLogger).getLastLoggedString() != null &&
                    !(((StringLogger)currentLogger).getLastLoggedString().equals(message))) {
                flush();
            }
        } else {
            checkTypeAndSetLogger(OBJECT);
        }
        return msg;
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
        if (currentLogger != null && currentLogger.getDecorator() != null && !(decorator.equals(currentLogger.getDecorator()))) {
            flush();
        }
        if (currentLogger != null) {
            currentLogger.setDecorator(decorator);
        }
        currentLogger.log(typedMessage);
    }
    /**
     * Log message by type.
     *
     * @param message - object of message, which is needed to log.
     *                Input types are: int, byte, String, boolean, Object, char.
     */
    public void log(Object message) throws LoggerException {
        Object typedMessage = setCurrentLogger(message);
        currentLogger.log(typedMessage);
    }

    /**
     * Find necessary logger in the pool loggers. If such doesn't exist then throw LoggerException
     * @param nextType - type of logger need to be found
     * @return - ref to the found logger
     */
    private Logger findLogger(int nextType) {
        Logger foundLogger = null;
        for (Logger logger : loggers) {
            if (logger.getLoggerType() == nextType) {
                foundLogger = logger;
                break;
            }
        }
        return foundLogger;
    }

    /**
     * Create an event preceding the end of the association data of the same type into a single stream.
     */
    public void flush() throws LoggerException {
        String decoratedMessage;
        try {
            decoratedMessage = currentLogger.getData();
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
        currentLogger.clear();
    }
}
