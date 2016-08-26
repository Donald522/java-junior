package com.acme.edu;

import com.acme.edu.decorators.*;
import com.acme.edu.loggers.*;
import com.acme.edu.savers.Saver;

/**
 * Created by anton on 25.08.16.
 * Main class of Logger.
 * It decides on the further processing of the message.
 * It connects decorator, saver and specific logger for each message.
 * Takes messages and save them in special format.
 */
public class LoggerFacad {

    private Logger logger;
    private Saver saver;
    private Decorator decorator;

    private boolean decoratorBydefault = true;

    public LoggerFacad(Saver saver) {
        this.saver = saver;
    }

    /**
     * Use your own decorator before log message.
     * @param decorator - object of type Decorator, which decorate each message.
     */
    public void setDecorator(Decorator decorator) {
        this.decorator = decorator;
        decoratorBydefault = false;
    }

    /**
     * Log message by type.
     *
     * @param message - object of message, which is needed to log.
     *                Input types are: int, byte, String, boolean, Object, char.
     */
    public void log(Object message) {
        switch (message.getClass().getName()) {
            case "java.lang.Integer":
                if((logger != null) && !(logger instanceof IntLogger)) {
                    flush();
                }
                logger = IntLogger.getInstance();
                if(decoratorBydefault) {
                    decorator = IntDecorator.getInstance();
                }
                break;
            case "java.lang.Byte":
                if((logger != null) && !(logger instanceof ByteLogger)) {
                    flush();
                }
                message = ((Byte)message).intValue();
                logger = ByteLogger.getInstance();
                if(decoratorBydefault) {
                    decorator = IntDecorator.getInstance();
                }
                break;
            case "java.lang.Character":
                if((logger != null) && !(logger instanceof CharLogger)) {
                    flush();
                }
                logger = CharLogger.getInstance();
                if(decoratorBydefault) {
                    decorator = CharDecorator.getInstance();
                }
                break;
            case "java.lang.String":
                if((logger != null) && !(logger instanceof StringLogger)) {
                    flush();
                }
                logger = StringLogger.getInstance();
                logger.setSaver(saver);
                if(decoratorBydefault) {
                    decorator = StringDecorator.getInstance();
                }
                logger.setDecorator(decorator);
                break;
            case "java.lang.Boolean":
                if((logger != null) && !(logger instanceof BooleanLogger)) {
                    flush();
                }
                logger = BooleanLogger.getInstance();
                if(decoratorBydefault) {
                    decorator = BooleanDecorator.getInstance();
                }
                break;
            case "java.lang.Object":
                if((logger != null) && !(logger instanceof ObjectLogger)) {
                    flush();
                }
                logger = ObjectLogger.getInstance();
                if(decoratorBydefault) {
                    decorator = ObjectDecorator.getInstance();
                }
                break;
        }
        logger.log(message);
    }

    /**
     * Create an event preceding the end of the association data of the same type into a single stream.
     */
    public void flush() {
        saver.save(decorator.decorate(logger.getData()));
        logger.clear();
    }

}
